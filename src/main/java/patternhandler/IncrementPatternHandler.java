package patternhandler;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class IncrementPatternHandler implements PatternHandler {
    Pattern pattern = Pattern.compile("^(\\d+|[*])[/]\\d+$");
    private final Range range;

    public IncrementPatternHandler(Range range) {
        this.range = range;
    }

    @Override
    public boolean canHandle(String value) {
        return pattern.matcher(value).matches() && hasValidStartingValue(value.split("/")[0]);
    }

    private boolean hasValidStartingValue(String value) {
        if (value.equals("*")) return true;
        int startValue = parseInt(value);
        return startValue >= range.getLowerLimit() && startValue <= range.getUpperLimit();
    }

    @Override
    public String handle(String value) {
        String[] values = value.split("/");
        int startValue = values[0].equals("*") ? 0 : parseInt(values[0]);
        int increment = parseInt(values[1]);
        return Stream.iterate(startValue, i -> i <= range.getUpperLimit(), i -> i + increment)
                .map(Objects::toString)
                .collect(Collectors.joining(" "));
    }
}
