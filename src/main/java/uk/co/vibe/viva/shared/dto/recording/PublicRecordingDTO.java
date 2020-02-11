package uk.co.vibe.viva.shared.dto.recording;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicRecordingDTO {
    private String id;
    private String pid;
    private String cid;
    private String originalFrom;
    private String originalTo;
    private String from;
    private String to;
    private String customerId;
    private String customerName;
    private String retention;
    private String name;
    private String status;
    private Date created;
    private Date expires;
    private Date uploaded;
    private Date archive;
    private long ringDuration;
    private long callDuration;
    private HashSet<String> tags;
    private String ip;
    private String direction;
    private Set<String> extensions;
    private Set<String> pbxIds;
    private String publicURL;
}
