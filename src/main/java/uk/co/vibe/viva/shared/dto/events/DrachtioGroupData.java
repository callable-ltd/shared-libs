package uk.co.vibe.viva.shared.dto.events;

import uk.co.vibe.viva.shared.enums.RecordingStatus;
import uk.co.vibe.viva.shared.enums.VivaEventStatus;
import uk.co.vibe.viva.shared.enums.VivaEventType;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class DrachtioGroupData implements GroupData {

    @Override
    public List<String> getRecordingIds(List<VivaSimpleEvent> events) {
        return events.stream()
                .filter(this::isRecordingEvent)
                .map(VivaSimpleEvent::getCid)
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> getRecordedDialCIDs(List<VivaSimpleEvent> events) {
        return new HashSet<>(getRecordingIds(events));
    }

    @Override
    public boolean isRecordingEvent(VivaSimpleEvent vivaEvent) {
        return vivaEvent.getType().equals(VivaEventType.RECORD.getType());
    }

    public boolean isRecordingStart(VivaSimpleEvent vivaEvent) {
        return isRecordingEvent(vivaEvent) && vivaEvent.getStatus().equals(RecordingStatus.RECORDING.getStatus());
    }

    public boolean isRecordingComplete(VivaSimpleEvent vivaEvent) {
        return isRecordingEvent(vivaEvent) && vivaEvent.getStatus().equals(RecordingStatus.COMPLETED.getStatus());
    }

    @Override
    public long getRecordingDuration(String cid, List<VivaSimpleEvent> events) {
        long duration = 0;

        Optional<VivaSimpleEvent> start = events.stream()
                .filter(vivaEvent -> isCid(cid, vivaEvent))
                .filter(this::isRecordingStart)
                .findFirst();

        Optional<VivaSimpleEvent> end = events.stream()
                .filter(vivaEvent -> isCid(cid, vivaEvent))
                .filter(this::isRecordingComplete)
                .findFirst();

        if (start.isPresent()) {
            Instant startInstant = Instant.ofEpochMilli(start.get().getTimestamp());
            Instant endInstant = end.isPresent() ? Instant.ofEpochMilli(end.get().getTimestamp()) : Instant.now();
            duration += endInstant.minusMillis(startInstant.toEpochMilli()).toEpochMilli();
        }


        return duration;
    }


    @Override
    public boolean isCompleted(List<VivaSimpleEvent> events) {
        return events.stream()
                .filter(simpleEvent -> simpleEvent.getType().equals(VivaEventType.IVR_STATUS.getType()))
                .anyMatch(simpleEvent -> simpleEvent.getStatus().equals(VivaEventStatus.COMPLETED.getStatus()));
    }

    @Override
    public boolean isEnded(VivaSimpleEvent vivaEvent) {
        return isCompleted(vivaEvent) ||
                vivaEvent.getStatus().equals(VivaEventStatus.FAILED.getStatus()) ||
                vivaEvent.getStatus().equals(VivaEventStatus.BUSY.getStatus());
    }

    @Override
    public boolean isRinging(VivaSimpleEvent vivaEvent) {
        return vivaEvent.getStatus().equals(VivaEventStatus.RINGING.getStatus()) ||
                vivaEvent.getStatus().equals(VivaEventStatus.EARLY_MEDIA.getStatus());
    }

    @Override
    public boolean isAnswered(VivaSimpleEvent vivaEvent) {
        return vivaEvent.getStatus().equals(VivaEventStatus.IN_PROGRESS.getStatus());
    }

    @Override
    public Instant getCreated(String cid, List<VivaSimpleEvent> events) {
        return events.stream()
                .filter(vivaEvent -> vivaEvent.getCid().equals(cid))
                .filter(vivaEvent -> vivaEvent.getStatus().equals(VivaEventStatus.TRYING.getStatus()))
                .findFirst()
                .map(vivaEvent -> Instant.ofEpochMilli(vivaEvent.getTimestamp()))
                .orElseThrow(IllegalStateException::new);
    }
}
