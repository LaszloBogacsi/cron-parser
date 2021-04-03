package patternhandler;

import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class NumberPatternHandler implements PatternHandler {
    Pattern pattern = Pattern.compile("^\\d+$");
    private final Range range;

    public NumberPatternHandler(Range range) {
        this.range = range;
    }

    @Override
    public boolean canHandle(String value) {
        return pattern.matcher(value).matches() && isInValidRange(value);
    }

    @Override
    public String handle(String value) {
        return value;
    }

    private boolean isInValidRange(String value) {
        int number = parseInt(value);
        return range.getLowerLimit() <= number && number <= range.getUpperLimit();
    }
}
