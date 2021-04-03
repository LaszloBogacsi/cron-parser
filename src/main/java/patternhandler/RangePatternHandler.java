package patternhandler;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;

public class RangePatternHandler implements PatternHandler {
    Pattern pattern = Pattern.compile("^\\d+[-]\\d+$");
    private final Range range;

    public RangePatternHandler(Range range) {
        this.range = range;
    }

    @Override
    public boolean canHandle(String value) {
        return pattern.matcher(value).matches() && isValidRange(value);
    }

    @Override
    public String handle(String value) {
        String[] range = value.split("-");
        return IntStream.rangeClosed(parseInt(range[0]), parseInt(range[1]))
                .boxed()
                .map(Objects::toString)
                .collect(Collectors.joining(" "));
    }

    private boolean isValidRange(String values) {
        String[] rangeLimits = values.split("-");
        int lowerValue = parseInt(rangeLimits[0]);
        int upperValue = parseInt(rangeLimits[1]);
        return lowerValue <= upperValue && range.getLowerLimit() <= lowerValue && upperValue <= range.getUpperLimit();
    }
}
