package uk.co.vibe.viva.shared.dto.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.commons.lang3.StringUtils;
import uk.co.vibe.viva.shared.dto.customer.CustomerDTO;
import uk.co.vibe.viva.shared.dto.ivr.ivr.*;
import uk.co.vibe.viva.shared.enums.RecordingStatus;
import uk.co.vibe.viva.shared.enums.VivaEventStatus;
import uk.co.vibe.viva.shared.enums.VivaEventType;
import uk.co.vibe.viva.shared.util.ASRUtils;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = DrachtioGroupData.class, name = "drachtio"),
        @JsonSubTypes.Type(value = RestcommGroupData.class, name = "restcomm")
})
public interface GroupData {


    default String getTerminationType(String to, CustomerDTO customer) {
        if (customer.getDdis().stream().anyMatch(ddiDTO -> ddiDTO.getName().equals(to)))
            return "customer";
        if (customer.getDevices().stream().anyMatch(deviceDTO -> deviceDTO.getExtension().equals(to)))
            return "customer";
        if (customer.getDevices().stream().anyMatch(deviceDTO -> to.equals(deviceDTO.getClientUsername())))
            return "customer";
        return "vendor";
    }

    default String getOriginationType(List<String> vendorIps, List<VivaSimpleEvent> events) {
        String ip = getIpAddress(events);
        if (vendorIps.contains(ip))
            return "vendor";
        return "customer";
    }

    default VivaSimpleEvent getLatestDialLeg(String cid, List<VivaSimpleEvent> events) {
        return getDialLegs(events).get(cid).stream()
                .reduce((first, second) -> second)
                .orElse(null);
    }

    default Map<String, List<VivaSimpleEvent>> groupedDialLegs(List<VivaSimpleEvent> events) {
        return events.stream()
                .filter(this::isDialStatus)
                .collect(Collectors.groupingBy(VivaSimpleEvent::getCid));
    }

    default boolean isCompleted(List<VivaSimpleEvent> events) {

        boolean completedInbound = events.stream()
                .filter(simpleEvent -> simpleEvent.getType().equals(VivaEventType.IVR_RESPONSE.getType()))
                .filter(simpleEvent -> simpleEvent.getIvrResponse()
                        .getItems()
                        .stream()
                        .anyMatch(responseVerb -> responseVerb instanceof IvrHangupVerb))
                .anyMatch(simpleEvent -> simpleEvent.getStatus().equals(VivaEventStatus.COMPLETED.getStatus()));

        boolean completedOutboundAPI = events.stream()
                .filter(this::isOutboundAPI)
                .anyMatch(simpleEvent -> simpleEvent.getStatus().equals(VivaEventStatus.COMPLETED.getStatus()));


        return completedInbound || completedOutboundAPI;

    }

    default boolean isCompleted(boolean forceCompleted, List<VivaSimpleEvent> events) {

        if (forceCompleted)
            return true;

        boolean completedInbound = events.stream()
                .filter(simpleEvent -> simpleEvent.getType().equals(VivaEventType.IVR_RESPONSE.getType()))
                .filter(simpleEvent -> simpleEvent.getIvrResponse()
                        .getItems()
                        .stream()
                        .anyMatch(responseVerb -> responseVerb instanceof IvrHangupVerb))
                .anyMatch(simpleEvent -> simpleEvent.getStatus().equals(VivaEventStatus.COMPLETED.getStatus()));

        boolean completedOutboundAPI = events.stream()
                .filter(this::isOutboundAPI)
                .anyMatch(simpleEvent -> simpleEvent.getStatus().equals(VivaEventStatus.COMPLETED.getStatus()));


        return completedInbound || completedOutboundAPI;

    }

    default int getApiLegCount(List<VivaSimpleEvent> events) {
        Map<String, List<VivaSimpleEvent>> legs = getApiLegs(events);
        return legs.values().size();
    }

    default int getDialLegCount(List<VivaSimpleEvent> events) {
        Map<String, List<VivaSimpleEvent>> legs = getDialLegs(events);
        return legs.values().size();
    }

    default int getIvrLegCount(List<VivaSimpleEvent> events) {
        Map<String, List<VivaSimpleEvent>> legs = getIVRLegs(events);
        return legs.values().size();
    }

    default Map<String, List<VivaSimpleEvent>> getApiLegs(List<VivaSimpleEvent> events) {
        return events.stream()
                .filter(vivaEvent -> vivaEvent.getEvent().equals("api"))
                .collect(Collectors.groupingBy(VivaSimpleEvent::getCid));
    }

    default Map<String, List<VivaSimpleEvent>> getDialLegs(List<VivaSimpleEvent> events) {
        return events.stream()
                .filter(vivaEvent -> vivaEvent.getType().equals(VivaEventType.DIAL_STATUS.getType()))
                .collect(Collectors.groupingBy(VivaSimpleEvent::getCid));
    }

    default Map<String, List<VivaSimpleEvent>> getIVRLegs(List<VivaSimpleEvent> events) {
        return events.stream()
                .filter(vivaEvent -> vivaEvent.getType().startsWith("ivr"))
                .collect(Collectors.groupingBy(VivaSimpleEvent::getCid));
    }

    default List<String> getRecordingIds(List<VivaSimpleEvent> events) {
        return events.stream()
                .filter(this::isRecordingEvent)
                .map(VivaSimpleEvent::getDialPublicRecordingId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    default long getRecordingDuration(List<VivaSimpleEvent> events) {
        return getRecordedDialCIDs(events)
                .stream()
                .mapToLong(s -> getRecordingDuration(s, events))
                .sum();
    }

    default long getRecordingDuration(String cid, List<VivaSimpleEvent> events) {

        long duration = 0;

        if (isCompleted(events)) {

            Set<String> recordedCalls = getRecordedDialCIDs(events);

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

    default Set<String> getRecordedDialCIDs(List<VivaSimpleEvent> events) {
        return events.stream()
                .filter(this::isRecordingStatus)
                .map(VivaSimpleEvent::getDialCid)
                .collect(Collectors.toSet());
    }


    default long getDialDuration(String cid, List<VivaSimpleEvent> events) {

        long duration = 0;

        for (List<VivaSimpleEvent> value : getDialLegs(events).values()) {

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


    default long getIvrDuration(Instant completed, Instant created, List<VivaSimpleEvent> events) {
        Instant now = isCompleted(events) ? completed : Instant.now();
        return now.minusMillis(created.toEpochMilli())
                .toEpochMilli();
    }

    default long getRingDuration(String cid, List<VivaSimpleEvent> events) {

        long duration = 0;

        for (List<VivaSimpleEvent> value : getDialLegs(events).values()) {

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

    default long getTTSCount(List<VivaSimpleEvent> events) {
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

    default long getASRCount(List<VivaSimpleEvent> events) {
        return events.stream()
                .filter(this::isOrigination)
                .filter(this::hasSpeech)
                .map(VivaSimpleEvent::getSpeech)
                .mapToLong(ASRUtils::sum)
                .sum();
    }

    default String getOrigFrom(List<VivaSimpleEvent> events) {
        return events.stream()
                .filter(this::isOrigination)
                .findFirst()
                .map(VivaSimpleEvent::getFrom)
                .orElse("");
    }

    default String getOrigTo(List<VivaSimpleEvent> events) {
        return events.stream()
                .filter(this::isOrigination)
                .findFirst()
                .map(VivaSimpleEvent::getTo)
                .orElse("");
    }

    default String getTermFrom(String cid, List<VivaSimpleEvent> events) {
        return events.stream()
                .filter(vivaEvent -> isCid(cid, vivaEvent))
                .filter(this::isDialStatus)
                .findFirst()
                .map(VivaSimpleEvent::getFrom)
                .orElse("");
    }

    default String getTermTo(String cid, List<VivaSimpleEvent> events) {
        return events.stream()
                .filter(vivaEvent -> isCid(cid, vivaEvent))
                .filter(this::isDialStatus)
                .findFirst()
                .map(VivaSimpleEvent::getTo)
                .orElse("");
    }

    default Instant getCreated(String cid, List<VivaSimpleEvent> events) {
        return events.stream()
                .filter(vivaEvent -> vivaEvent.getCid().equals(cid))
                .filter(vivaEvent -> vivaEvent.getStatus().equals(VivaEventStatus.INITIATED.getStatus()))
                .findFirst()
                .map(vivaEvent -> Instant.ofEpochMilli(vivaEvent.getTimestamp()))
                .orElseThrow(IllegalStateException::new);
    }

    default Instant getCompleted(String cid, List<VivaSimpleEvent> events) {
        return events.stream()
                .filter(vivaEvent -> vivaEvent.getCid().equals(cid))
                .filter(this::isCompleted)
                .findFirst()
                .map(vivaEvent -> Instant.ofEpochMilli(vivaEvent.getTimestamp()))
                .orElseThrow(IllegalStateException::new);
    }

    default String getIpAddress(List<VivaSimpleEvent> events) {
        return events.stream()
                .filter(vivaEvent -> vivaEvent.getIp() != null)
                .map(VivaSimpleEvent::getIp)
                .findFirst()
                .orElse("");
    }


    default String getCid(List<VivaSimpleEvent> dialLegs) {
        return dialLegs.get(0).getCid();
    }

    default Optional<List<VivaSimpleEvent>> matches(MonitoredTrunkAttachRequest request, List<VivaSimpleEvent> events) {
        return groupedDialLegs(events).values()
                .stream()
                .filter(this::isDialLegActive)
                .filter(legs -> matches(request, legs.get(0)))
                .findFirst();
    }

    default boolean matches(MonitoredTrunkAttachRequest request, VivaSimpleEvent event) {
        return request.getCaller().equals(event.getFrom()) &&
                request.getCalled().equals(event.getTo());
    }

    default boolean isDialLegActive(List<VivaSimpleEvent> events) {

        VivaSimpleEvent event = events.stream()
                .max(Comparator.comparing(VivaSimpleEvent::getTimestamp))
                .orElseThrow(IllegalStateException::new);

        if (isCompleted(event))
            return false;

        return isRinging(event) || isAnswered(event);
    }

    default boolean isEnded(VivaSimpleEvent vivaEvent) {
        return isCompleted(vivaEvent) ||
                vivaEvent.getStatus().equals(VivaEventStatus.FAILED.getStatus()) ||
                vivaEvent.getStatus().equals(VivaEventStatus.BUSY.getStatus());
    }

    default boolean isRinging(VivaSimpleEvent vivaEvent) {
        return vivaEvent.getStatus().equals(VivaEventStatus.RINGING.getStatus());
    }

    default boolean isAnswered(VivaSimpleEvent vivaEvent) {
        return vivaEvent.getStatus().equals(VivaEventStatus.ANSWERED.getStatus());
    }

    default boolean hasSpeech(VivaSimpleEvent vivaEvent) {
        return StringUtils.isNotBlank(vivaEvent.getSpeech());
    }

    default boolean isCid(String cid, VivaSimpleEvent vivaEvent) {
        return vivaEvent.getCid().equals(cid);
    }


    default boolean isRecordingEvent(VivaSimpleEvent vivaEvent) {
        return vivaEvent.getType().equals(VivaEventType.RECORD_EVENT.getType())
                && vivaEvent.getStatus().equals(RecordingStatus.UPLOADED.getStatus());
    }

    default boolean isDialStatus(VivaSimpleEvent vivaEvent) {
        return vivaEvent.getType().equals(VivaEventType.DIAL_STATUS.getType());
    }

    default boolean isCidNullable(String cid, VivaSimpleEvent vivaEvent) {
        return cid == null || isCid(cid, vivaEvent);
    }

    default boolean isCompleted(VivaSimpleEvent vivaEvent) {
        return vivaEvent.getStatus().equals(VivaEventStatus.COMPLETED.getStatus());
    }

    default boolean isOrigination(VivaSimpleEvent vivaEvent) {
        return vivaEvent.getType().equals(VivaEventType.IVR_RESPONSE.getType()) || this.isOutboundAPI(vivaEvent);
    }

    default boolean isOutboundAPI(VivaSimpleEvent vivaEvent) {
        return vivaEvent.getType().equals(VivaEventType.IVR_STATUS.getType()) &&
                vivaEvent.getEvent().equals("api");
    }

    default boolean hasDial(List<VivaSimpleEvent> events) {
        return getDialLegCount(events) > 0;
    }

    default boolean hasRecordings(List<VivaSimpleEvent> events) {
        return getRecordingIds(events).size() > 0;
    }

    default boolean isRecordingStatus(VivaSimpleEvent simpleEvent) {
        return Optional.ofNullable(simpleEvent.getDialRecordingUrl())
                .isPresent();
    }
}
