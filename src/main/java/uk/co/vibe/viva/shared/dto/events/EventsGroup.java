package uk.co.vibe.viva.shared.dto.events;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import uk.co.vibe.viva.shared.dto.customer.CustomerDTO;
import uk.co.vibe.viva.shared.dto.ivr.ivr.*;
import uk.co.vibe.viva.shared.dto.pbx.SimplePBXEvent;
import uk.co.vibe.viva.shared.util.ASRUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class EventsGroup {

    private CustomerDTO customer;
    private String cid;
    private List<VivaSimpleEvent> events;
    private List<SimplePBXEvent> pbxEvents;

    private Instant created;
    private Instant completed;

    private Set<String> pbxIds;

    private boolean forceCompleted;

    public EventsGroup(VivaEvent vivaEvent) {
        this.customer = vivaEvent.getCustomerDTO();
        this.cid = vivaEvent.getParentId();
        this.events = new ArrayList<>();
        this.created = Instant.now();
        this.pbxIds = new HashSet<>();
        this.pbxEvents = new ArrayList<>();
        this.forceCompleted = false;
    }

    public Set<String> getPbxIds() {
        return pbxIds;
    }

    public List<SimplePBXEvent> getPbxEvents() {
        return pbxEvents;
    }

    public void add(VivaSimpleEvent vivaEvent) {
        this.events.add(vivaEvent);
    }

    public void add(SimplePBXEvent pbxEvent) {
        pbxIds.add(pbxEvent.getId());
    }

    public void forceComplete() {
        this.forceCompleted = true;
    }

    public boolean isCompleted() {

        if(forceCompleted)
            return true;

        boolean completedInbound = events.stream()
                .filter(simpleEvent -> simpleEvent.getType().equals("ivr-response"))
                .filter(simpleEvent -> simpleEvent.getIvrResponse()
                        .getItems()
                        .stream()
                        .anyMatch(responseVerb -> responseVerb instanceof IvrHangupVerb))
                .anyMatch(simpleEvent -> simpleEvent.getStatus().equals("completed"));

        boolean completedOutboundAPI = events.stream()
                .filter(this::isOutboundAPI)
                .anyMatch(simpleEvent -> simpleEvent.getStatus().equals("completed"));

        return completedInbound || completedOutboundAPI;

//        long completedEvents = events.stream()
//                .filter(vivaEvent -> isCompleted(vivaEvent) ||
//                        vivaEvent.getStatus().equals("failed") ||
//                        vivaEvent.getStatus().equals("uploaded") ||
//                        String.valueOf(vivaEvent.getDialCallStatus()).equals("busy") ||
//                        String.valueOf(vivaEvent.getDialCallStatus()).equals("failed"))
//                .count();
//
//        long recordingEvents = events.stream()
//                .filter(this::isOrigination)
//                .filter(vivaEvent -> StringUtils.isNotBlank(vivaEvent.getDialRecordingUrl()))
//                .count();
//
//        log.info("dialLegs={}, recordingEvents={}, expected={}, actual={}",
//                getDialLegCount(),
//                recordingEvents,
//                completedEvents,
//                (getDialLegCount() + recordingEvents + 1));
//
//        return completedEvents == (getDialLegCount() + recordingEvents + 1);
    }

    public boolean hasDial() {
        return getDialLegCount() > 0;
    }

    public boolean hasRecordings() {
        return getRecordingIds().size() > 0;
    }

    public int getApiLegCount() {
        Map<String, List<VivaSimpleEvent>> legs = getApiLegs();
        return legs.values().size();
    }

    public int getDialLegCount() {
        Map<String, List<VivaSimpleEvent>> legs = getDialLegs();
        return legs.values().size();
    }

    public int getIvrLegCount() {
        Map<String, List<VivaSimpleEvent>> legs = getIVRLegs();
        return legs.values().size();
    }

    public Map<String, List<VivaSimpleEvent>> getApiLegs() {
        return events.stream()
                .filter(vivaEvent -> vivaEvent.getEvent().equals("api"))
                .collect(Collectors.groupingBy(VivaSimpleEvent::getCid));
    }

    public Map<String, List<VivaSimpleEvent>> getDialLegs() {
        return events.stream()
                .filter(vivaEvent -> vivaEvent.getType().equals("dial-status"))
                .collect(Collectors.groupingBy(VivaSimpleEvent::getCid));
    }

    public Map<String, List<VivaSimpleEvent>> getIVRLegs() {
        return events.stream()
                .filter(vivaEvent -> vivaEvent.getType().startsWith("ivr"))
                .collect(Collectors.groupingBy(VivaSimpleEvent::getCid));
    }

    public List<String> getRecordingIds() {
        return events.stream()
                .filter(this::isRecordingEvent)
                .map(VivaSimpleEvent::getDialPublicRecordingId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public boolean isRecordingStatus(VivaSimpleEvent simpleEvent) {
        return Optional.ofNullable(simpleEvent.getDialRecordingUrl())
                .isPresent();
    }

    public long getRecordingDuration(String cid) {

        long duration = 0;

        if (isCompleted()) {

            Set<String> recordedCalls = getRecordedDialCIDs();

            for (String recordedCall : recordedCalls) {

                List<VivaSimpleEvent> value = events.stream()
                        .filter(vivaEvent -> isCidNullable(cid, vivaEvent))
                        .filter(vivaEvent -> isCid(recordedCall, vivaEvent))
                        .collect(Collectors.toList());

                Optional<VivaSimpleEvent> start = value.stream()
                        .filter(this::isDialStatus)
                        .filter(this::isAnswered)
                        .findFirst();

                Optional<VivaSimpleEvent> end = value.stream()
                        .filter(this::isDialStatus)
                        .filter(this::isCompleted)
                        .findFirst();

                if (start.isPresent()) {
                    Instant startInstant = Instant.ofEpochMilli(start.get().getTimestamp());
                    Instant endInstant = end.isPresent() ? Instant.ofEpochMilli(end.get().getTimestamp()) : Instant.now();
                    duration += endInstant.minusMillis(startInstant.toEpochMilli()).toEpochMilli();
                }


            }
        }

        return duration;
    }

    public Set<String> getRecordedDialCIDs() {
        return events.stream()
                .filter(this::isRecordingStatus)
                .map(VivaSimpleEvent::getDialCid)
                .collect(Collectors.toSet());
    }


    public long getDialDuration(String cid) {

        long duration = 0;

        for (List<VivaSimpleEvent> value : getDialLegs().values()) {

            Optional<VivaSimpleEvent> start = value.stream()
                    .filter(vivaEvent -> isCidNullable(cid, vivaEvent))
                    .filter(this::isDialStatus)
                    .filter(this::isAnswered)
                    .findFirst();

            Optional<VivaSimpleEvent> end = value.stream()
                    .filter(vivaEvent -> isCidNullable(cid, vivaEvent))
                    .filter(this::isDialStatus)
                    .filter(this::isCompleted)
                    .findFirst();

            if (start.isPresent()) {
                Instant startInstant = Instant.ofEpochMilli(start.get().getTimestamp());
                Instant endInstant = end.isPresent() ? Instant.ofEpochMilli(end.get().getTimestamp()) : Instant.now();
                duration += endInstant.minusMillis(startInstant.toEpochMilli()).toEpochMilli();
            }

        }

        return duration;
    }


    public long getIvrDuration() {
        Instant now = isCompleted() ? completed : Instant.now();
        return now.minusMillis(created.toEpochMilli())
                .toEpochMilli();
    }

    public long getRingDuration(String cid) {

        long duration = 0;

        for (List<VivaSimpleEvent> value : getDialLegs().values()) {

            Optional<VivaSimpleEvent> ringing = value.stream()
                    .filter(vivaEvent -> isCidNullable(cid, vivaEvent))
                    .filter(this::isDialStatus)
                    .filter(this::isRinging)
                    .findFirst();

            Optional<VivaSimpleEvent> answered = value.stream()
                    .filter(vivaEvent -> isCidNullable(cid, vivaEvent))
                    .filter(this::isDialStatus)
                    .filter(this::isAnswered)
                    .findFirst();

            Optional<VivaSimpleEvent> ended = value.stream()
                    .filter(vivaEvent -> isCidNullable(cid, vivaEvent))
                    .filter(this::isDialStatus)
                    .filter(this::isCompleted)
                    .findFirst();

            if (ringing.isPresent() && answered.isPresent()) {
                Instant startInstant = Instant.ofEpochMilli(ringing.get().getTimestamp());
                Instant endInstant = Instant.ofEpochMilli(answered.get().getTimestamp());
                duration += endInstant.minusMillis(startInstant.toEpochMilli()).toEpochMilli();
            } else if (ringing.isPresent() && ended.isPresent()) {
                Instant startInstant = Instant.ofEpochMilli(ringing.get().getTimestamp());
                Instant endInstant = Instant.ofEpochMilli(ended.get().getTimestamp());
                duration += endInstant.minusMillis(startInstant.toEpochMilli()).toEpochMilli();
            }

            return duration;
        }

        return duration;
    }

    public long getTTSCount() {
        return events.stream()
                .filter(this::isOrigination)
                .filter(vivaEvent -> vivaEvent.getIvrResponse() != null)
                .mapToLong(value -> {
                    int tts = 0;
                    for (IvrResponseVerb verb : value.getIvrResponse().getItems()) {
                        if (verb instanceof IvrGatherVerb) {
                            for (IvrPlaySayResponse cVerb : ((IvrGatherVerb) verb).getVerbs()) {
                                if (cVerb instanceof IvrSayVerb) {
                                    tts += ((IvrSayVerb) cVerb).getText().length();
                                }
                            }
                        } else if (verb instanceof IvrSayVerb) {
                            tts += ((IvrSayVerb) verb).getText().length();
                        }
                    }
                    return tts;
                })
                .sum();
    }

    public long getASRCount() {
        return events.stream()
                .filter(this::isOrigination)
                .filter(this::hasSpeech)
                .map(VivaSimpleEvent::getSpeech)
                .mapToLong(ASRUtils::sum)
                .sum();
    }

    public String getOrigFrom() {
        return events.stream()
                .filter(this::isOrigination)
                .findFirst()
                .map(VivaSimpleEvent::getFrom)
                .orElse("");
    }

    public String getOrigTo() {
        return events.stream()
                .filter(this::isOrigination)
                .findFirst()
                .map(VivaSimpleEvent::getTo)
                .orElse("");
    }

    public String getTermFrom(String cid) {
        return events.stream()
                .filter(vivaEvent -> isCid(cid, vivaEvent))
                .filter(this::isDialStatus)
                .findFirst()
                .map(VivaSimpleEvent::getFrom)
                .orElse("");
    }

    public String getTermTo(String cid) {
        return events.stream()
                .filter(vivaEvent -> isCid(cid, vivaEvent))
                .filter(this::isDialStatus)
                .findFirst()
                .map(VivaSimpleEvent::getTo)
                .orElse("");
    }

    private boolean isCid(String cid, VivaSimpleEvent vivaEvent) {
        return vivaEvent.getCid().equals(cid);
    }

    private boolean isDialStatus(VivaSimpleEvent vivaEvent) {
        return vivaEvent.getType().equals("dial-status");
    }


    private boolean isRecordingEvent(VivaSimpleEvent vivaEvent) {
        return vivaEvent.getType().equals("recording-event")
                && vivaEvent.getStatus().equals("uploaded");
    }

    public String getCustomerId() {
        return customer.getId();
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public String getCid() {
        return cid;
    }


    public Instant getCreated() {
        return created;
    }

    public Instant getCreated(String cid) {
        return events.stream()
                .filter(vivaEvent -> vivaEvent.getCid().equals(cid))
                .filter(vivaEvent -> vivaEvent.getStatus().equals("initiated"))
                .findFirst()
                .map(vivaEvent -> Instant.ofEpochMilli(vivaEvent.getTimestamp()))
                .orElseThrow(IllegalStateException::new);
    }

    public Instant getCompleted(String cid) {
        return events.stream()
                .filter(vivaEvent -> vivaEvent.getCid().equals(cid))
                .filter(this::isCompleted)
                .findFirst()
                .map(vivaEvent -> Instant.ofEpochMilli(vivaEvent.getTimestamp()))
                .orElseThrow(IllegalStateException::new);
    }

    public String getIpAddress() {
        return events.stream()
                .filter(vivaEvent -> vivaEvent.getIp() != null)
                .map(VivaSimpleEvent::getIp)
                .findFirst()
                .orElse("");
    }

    public Instant getCompleted() {
        return completed;
    }

    public void complete() {
        if (completed == null)
            completed = Instant.now();
    }

    private boolean isOrigination(VivaSimpleEvent vivaEvent) {
        return vivaEvent.getType().equals("ivr-response") ||
                isOutboundAPI(vivaEvent);
    }

    private boolean isOutboundAPI(VivaSimpleEvent vivaEvent) {
        return vivaEvent.getType().equals("ivr-status") &&
                vivaEvent.getEvent().equals("api");
    }

    private boolean isEnded(VivaSimpleEvent vivaEvent) {
        return isCompleted(vivaEvent) ||
                vivaEvent.getStatus().equals("failed") ||
                vivaEvent.getStatus().equals("busy");
    }

    private boolean isRinging(VivaSimpleEvent vivaEvent) {
        return vivaEvent.getStatus().equals("ringing");
    }

    private boolean isCidNullable(String cid, VivaSimpleEvent vivaEvent) {
        return cid == null || isCid(cid, vivaEvent);
    }

    private boolean hasSpeech(VivaSimpleEvent vivaEvent) {
        return StringUtils.isNotBlank(vivaEvent.getSpeech());
    }

    private boolean isAnswered(VivaSimpleEvent vivaEvent) {
        return vivaEvent.getStatus().equals("answered");
    }

    private boolean isCompleted(VivaSimpleEvent vivaEvent) {
        return vivaEvent.getStatus().equals("completed");
    }

    public String getTerminationType(String cid) {
        String to = getTermTo(cid);
        if (customer.getDdis().stream().anyMatch(ddiDTO -> ddiDTO.getName().equals(to)))
            return "customer";
        if (customer.getDevices().stream().anyMatch(deviceDTO -> deviceDTO.getExtension().equals(to)))
            return "customer";
        if (customer.getDevices().stream().anyMatch(deviceDTO -> to.equals(deviceDTO.getClientUsername())))
            return "customer";
        return "vendor";
    }

    public String getOriginationType(List<String> vendorIps) {
        String ip = getIpAddress();
        if (vendorIps.contains(ip))
            return "vendor";
        return "customer";
    }

//    public VivaSimpleEvent attach(MonitoredTrunkAttachRequest request) {
//
//        if (!request.getCustomerId().equals(customer.getId()))
//            return null;
//
//        Optional<List<VivaSimpleEvent>> dialLegs = matches(request);
//
//        if (dialLegs.isPresent()) {
//            String callId = getCid(dialLegs.get());
//            if (pbxEventsHasCallId(callId)) {
//                PbxEventDTO event = new PbxEventDTO(callId, request);
//                this.pbxEvents.add(event);
//                return dialLegs.get().get(0);
//            }
//        }
//
//        return null;
//    }

//    private boolean pbxEventsHasCallId(String callId) {
//        return pbxEvents.stream().anyMatch(pbxEvent -> pbxEvent.getCallId().equals(callId));
//    }

    private String getCid(List<VivaSimpleEvent> dialLegs) {
        return dialLegs.get(0).getCid();
    }

    private Optional<List<VivaSimpleEvent>> matches(MonitoredTrunkAttachRequest request) {
        return groupedDialLegs().values()
                .stream()
                .filter(this::isDialLegActive)
                .filter(events -> matches(request, events.get(0)))
                .findFirst();
    }

    private boolean matches(MonitoredTrunkAttachRequest request, VivaSimpleEvent event) {
        return request.getCaller().equals(event.getFrom()) &&
                request.getCalled().equals(event.getTo());
    }

    public Map<String, List<VivaSimpleEvent>> groupedDialLegs() {
        return events.stream()
                .filter(this::isDialStatus)
                .collect(Collectors.groupingBy(VivaSimpleEvent::getCid));
    }

    public boolean isDialLegActive(List<VivaSimpleEvent> events) {
        VivaSimpleEvent event = events.stream().max(Comparator.comparing(VivaSimpleEvent::getTimestamp)).orElseThrow(IllegalStateException::new);
        if (isCompleted(event))
            return false;
        return isRinging(event) || isAnswered(event);
    }

    public List<VivaSimpleEvent> getEvents() {
        return events;
    }


}
