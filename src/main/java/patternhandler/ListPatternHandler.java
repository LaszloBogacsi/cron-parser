package patternhandler;

import java.util.Arrays;
import java.util.regex.Pattern;

public class ListPatternHandler implements PatternHandler {
    Pattern pattern = Pattern.compile("^(\\d*[,])+\\d+$");
    private final Range range;

    public ListPatternHandler(Range range) {
        this.range = range;
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
                .allMatch(item -> range.getLowerLimit() <= item && item <= range.getUpperLimit());
    }
}
