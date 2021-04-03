package patternhandler;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;

public class RangePatternHandler implements PatternHandler {
    private final int lowerLimit;
    private final int upperLimit;
    Pattern pattern = Pattern.compile("^\\d+[-]\\d+$");

    public RangePatternHandler(int lowerLimit, int upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
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

    private boolean isValidRange(String range) {
        String[] rangeLimits = range.split("-");
        int lowerValue = parseInt(rangeLimits[0]);
        int upperValue = parseInt(rangeLimits[1]);
        return lowerValue <= upperValue && lowerLimit <= lowerValue && upperValue <= upperLimit;
    }
}
