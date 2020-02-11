package uk.co.vibe.viva.shared.dto.events;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.co.vibe.viva.shared.dto.customer.CustomerDTO;
import uk.co.vibe.viva.shared.dto.pbx.SimplePBXEvent;

import java.time.Instant;
import java.util.*;

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

    private GroupData groupData;

    public EventsGroup(VivaEvent vivaEvent) {
        this.customer = vivaEvent.getCustomerDTO();
        this.cid = vivaEvent.getParentId();
        this.events = new ArrayList<>();
        this.created = Instant.now();
        this.pbxIds = new HashSet<>();
        this.pbxEvents = new ArrayList<>();
        this.forceCompleted = false;

        if (customer.getType().equals("viva-d")) {
            groupData = new DrachtioGroupData();
        } else {
            groupData = new RestcommGroupData();
        }
    }

    public GroupData getGroupData() {
        return groupData;
    }

    public List<VivaSimpleEvent> getEvents() {
        return events;
    }

    public Instant getCompleted() {
        return completed;
    }

    public void complete() {
        if (completed == null)
            completed = Instant.now();
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
        return groupData.isCompleted(events);
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

    public GroupData getData() {
        return groupData;
    }

    public int getDialLegCount() {
        return groupData.getDialLegCount(events);
    }

    public int getIvrLegCount() {
        return groupData.getIvrLegCount(events);
    }

    public int getApiLegCount() {
        return groupData.getApiLegCount(events);
    }

    public long getRecordingDuration(String cid) {
        return groupData.getRecordingDuration(cid, events);
    }

    public long getRecordingDuration() {
        return groupData.getRecordingDuration(events);
    }

    public long getDialDuration(String cid) {
        return groupData.getDialDuration(cid, events);
    }

    public long getRingDuration(String cid) {
        return groupData.getRingDuration(cid, events);
    }

    public long getIvrDuration() {
        return groupData.getIvrDuration(completed, created, events);
    }

    public String getTermFrom(String cid) {
        return groupData.getTermFrom(cid, events);
    }

    public String getTermTo(String cid) {
        return groupData.getTermTo(cid, events);
    }

    public Instant getCompleted(String cid) {
        return groupData.getCompleted(cid, events);
    }

    public Instant getCreated(String cid) {
        return groupData.getCreated(cid, events);
    }

    public String getTerminationType(String cid) {
        return groupData.getTerminationType(
                groupData.getTermTo(cid, events),
                customer);
    }

    public String getOrigFrom() {
        return groupData.getOrigFrom(events);
    }

    public String getOrigTo() {
        return groupData.getOrigTo(events);
    }

    public long getTTSCount() {
        return groupData.getTTSCount(events);
    }

    public long getASRCount() {
        return groupData.getASRCount(events);
    }

    public String getOriginationType(List<String> vendorIps) {
        return groupData.getOriginationType(vendorIps, events);
    }

    public VivaSimpleEvent getLatestDialLeg(String cid) {
        return groupData.getLatestDialLeg(cid, events);
    }

    public Map<String, List<VivaSimpleEvent>> getDialLegs() {
        return groupData.getDialLegs(events);
    }

    public Set<String> getRecordedDialCIDs() {
        return groupData.getRecordedDialCIDs(events);
    }

}
