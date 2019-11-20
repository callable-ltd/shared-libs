package uk.co.vibe.viva.shared.dto.mail;

public enum MailFrom {

    NO_REPLY("noreply@viva-eu.net", "VIVA");

    private final String email;
    private final String name;

    private MailFrom(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

}