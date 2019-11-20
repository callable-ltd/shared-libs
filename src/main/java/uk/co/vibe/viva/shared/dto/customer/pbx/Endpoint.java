package uk.co.vibe.viva.shared.dto.customer.pbx;

public interface Endpoint {
    Integer getRingTime();
    Integer getMaxCallTime();
    Boolean getRecord();
    String getPresent();
}
