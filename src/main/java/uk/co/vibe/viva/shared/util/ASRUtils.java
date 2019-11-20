package uk.co.vibe.viva.shared.util;

public class ASRUtils {

    public static long sum(String speech) {
        int wCount = speech.split(" ").length;
        double asrCount = wCount / 20D;
        Double minASRValue = Math.max(Math.ceil(asrCount), 1);
        return minASRValue.longValue();
    }
}
