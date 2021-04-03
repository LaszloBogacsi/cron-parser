package patternhandler;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class IncrementPatterHandler implements PatternHandler {
    private final int lowerLimit;
    private final int upperLimit;
    Pattern pattern = Pattern.compile("^[\\d{1,2}*][/]\\d{1,2}$");

    public IncrementPatterHandler(int lowerLimit, int upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    @Override
    public boolean canHandle(String value) {
        return pattern.matcher(value).matches() && hasValidStartingValue(value.split("/")[0]);
    }

    private boolean hasValidStartingValue(String value) {
        if (value.equals("*")) return true;
        int startValue = parseInt(value);
        return startValue >= this.lowerLimit && startValue <= upperLimit;
    }

    @Override
    public String handle(String value) {
        String[] values = value.split("/");
        int startValue = values[0].equals("*") ? 0 : parseInt(values[0]);
        int increment = parseInt(values[1]);
        return Stream.iterate(startValue, i -> i <= this.upperLimit, i -> i + increment)
                .map(Objects::toString)
                .collect(Collectors.joining(" "));
    }
}
