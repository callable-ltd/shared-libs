package uk.co.vibe.viva.shared.util;

public class CLIUtil {

    public static final String E164 = "e164";
    public static final String E164PLUS = "e164p";
    public static final String NATIONAL = "national";

    public static String national(String str) {
        str = str.replaceAll(" ", "");
        if (str.toLowerCase().equals("anonymous")) return str;
        if (str.toLowerCase().equals("unavailable")) return "anonymous";
        if (str.toLowerCase().startsWith("+")) str = str.substring(1);
        if (str.startsWith("0044")) str = "0" + str.substring(4);
        if (str.startsWith("00")) return str;
        if (str.startsWith("44")) return "0" + str.substring(2);
        if (str.startsWith("0")) return str;
        if (str.length() > 6) return "00" + str;
        return str;
    }

    public static String e164NoPlus(String s) {

        if (s == null) {
            return "anonymous";
        }

        s = s.split("@")[0];

        if (s.toLowerCase().equals("anonymous")) return s;
        if (s.toLowerCase().equals("unavailable")) return "anonymous";
        if (s.toLowerCase().equals("restricted")) return "anonymous";

        if (s.startsWith("+")) {
            s = s.substring(1);
        }

        if (s.startsWith("00")) {
            s = s.substring(2);
        }

        if (s.startsWith("0")) {
            s = "44" + s.substring(1);
        }

        return s.replaceAll(" ", "");
    }

    public static String e164Plus(String str) {

        if (CLIUtil.e164NoPlus(str) == null)
            return str;

        if (CLIUtil.e164NoPlus(str).equals("anonymous"))
            return str;

        return "+" + CLIUtil.e164NoPlus(str);
    }

    public static String format(String str, String format) {
        if (format.equals(E164)) {
            return CLIUtil.e164NoPlus(str);
        } else if (format.equals(E164PLUS)) {
            return CLIUtil.e164Plus(str);
        } else if (format.equals(NATIONAL)) {
            return CLIUtil.national(str);
        }
        throw new AssertionError("unknown format");
    }
}
