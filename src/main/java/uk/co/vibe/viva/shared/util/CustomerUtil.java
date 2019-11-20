package uk.co.vibe.viva.shared.util;


import uk.co.vibe.viva.shared.dto.customer.CustomerDTO;
import uk.co.vibe.viva.shared.dto.customer.DdiDTO;
import uk.co.vibe.viva.shared.dto.customer.SettingsDTO;

import java.util.Optional;
import java.util.stream.Stream;

import static uk.co.vibe.viva.shared.util.CLIUtil.e164NoPlus;

public class CustomerUtil {

    public static Optional<String> getCustomerSetting(CustomerDTO customerDTO, String key, String defaultValue) {
        Optional<String> settingA = customerDTO.getSettings().stream()
                .filter(settingsDTO -> settingsDTO.getKey().equals(key))
                .findFirst()
                .map(SettingsDTO::getValue);

        if (settingA.isPresent())
            return settingA;
        return Optional.ofNullable(defaultValue);
    }

    public static Boolean getRecord(Boolean record, DdiDTO ddiDTO, Boolean defaultRecord) {
        return Stream.of(
                Optional.ofNullable(record),
                Optional.ofNullable(ddiDTO.getRecord()),
                Optional.ofNullable(defaultRecord))
                .filter(Optional::isPresent)
                .findFirst()
                .map(Optional::get)
                .orElse(Boolean.FALSE);
    }

    public static Boolean getRecord(Boolean record, CustomerDTO customerDTO, Boolean defaultRecord) {
        return Stream.of(
                Optional.ofNullable(record),
                getCustomerSetting(customerDTO, "dial.record").map(Boolean::parseBoolean))
                .filter(Optional::isPresent)
                .findFirst()
                .map(Optional::get)
                .orElse(defaultRecord);
    }

    public static String getAction(String action, String defaultAction) {
        return Optional.ofNullable(action)
                .orElse(defaultAction);
    }

    public static Integer getTimeout(Integer timeout, CustomerDTO customerDTO, Integer defaultTimeout) {
        return Stream.of(
                Optional.ofNullable(timeout),
                getCustomerSetting(customerDTO, "dial.timeout").map(Integer::parseInt))
                .filter(Optional::isPresent)
                .findFirst()
                .map(Optional::get)
                .orElseGet(() -> defaultTimeout);
    }

    public static Integer getTimeLimit(Integer timeLimit, CustomerDTO customerDTO, int defaultTimeLimit) {
        return Stream.of(
                Optional.ofNullable(timeLimit),
                getCustomerSetting(customerDTO, "dial.timeLimit").map(Integer::parseInt))
                .filter(Optional::isPresent)
                .findFirst()
                .map(Optional::get)
                .orElse(defaultTimeLimit);
    }

    public static Optional<String> getCustomerSetting(CustomerDTO customerDTO, String key) {
        return customerDTO.getSettings().stream().filter(settingsDTO -> settingsDTO.getKey().equals(key))
                .findFirst()
                .map(SettingsDTO::getValue);
    }

    public static String getCallerId(String from) {
        String callerId = Optional.ofNullable(from).orElse("anonymous");
        return e164NoPlus(callerId);
    }

    public static String getCallerId(String from, CustomerDTO customerDTO) {
        String callerId = Optional.ofNullable(from).orElse("anonymous");
        String e164CallerId = e164NoPlus(callerId);
        boolean validCallerId = isValidCallerId(customerDTO, e164CallerId);
        return validCallerId ? e164CallerId : customerDTO.getMbn();
    }


    public static boolean isValidCallerId(CustomerDTO customerDTO, final String e164CallerId) {
        return e164NoPlus(customerDTO.getMbn()).equals(e164CallerId) ||
                e164CallerId.equals("anonymous") ||
                customerDTO.getDdis().stream().anyMatch(ddiDTO -> e164NoPlus(ddiDTO.getName()).equals(e164CallerId));
    }

    public static String getASRLanguage(String language, CustomerDTO customerDTO, String defaultLanguage) {
        return Stream.of(
                Optional.ofNullable(language),
                getCustomerSetting(customerDTO, "voice.asr"))
                .filter(Optional::isPresent)
                .findFirst()
                .map(Optional::get)
                .orElseGet(() -> defaultLanguage);
    }

    public static String getLanguage(String language, CustomerDTO customerDTO, String defaultLanguage) {
        return Stream.of(
                Optional.ofNullable(language),
                getCustomerSetting(customerDTO, "voice.language"))
                .filter(Optional::isPresent)
                .findFirst()
                .map(Optional::get)
                .orElseGet(() -> defaultLanguage);
    }

    public static String getVoice(String language, CustomerDTO customerDTO, String defaultVoice) {
        return Stream.of(
                Optional.ofNullable(language),
                getCustomerSetting(customerDTO, "voice.gender"))
                .filter(Optional::isPresent)
                .findFirst()
                .map(Optional::get)
                .orElseGet(() -> defaultVoice);
    }
}
