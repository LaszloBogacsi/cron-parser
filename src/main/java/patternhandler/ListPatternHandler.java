package patternhandler;

import java.util.Arrays;
import java.util.regex.Pattern;

public class ListPatternHandler implements PatternHandler {
    private final int lowerLimit;
    private final int upperLimit;
    Pattern pattern = Pattern.compile("^(\\d*[,])+\\d+$");

    public ListPatternHandler(int lowerLimit, int upperLimit) {

        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    @Override
    public boolean canHandle(String value) {
        return pattern.matcher(value).matches() && isInValidRange(value);
    }

    @Override
    public String handle(String value) {
        return value.replaceAll(",", " ");
    }

    private boolean isInValidRange(String value) {
        return Arrays.stream(value.split(","))
                .mapToInt(Integer::parseInt)
                .allMatch(item -> lowerLimit <= item && item <= upperLimit);
    }
}
