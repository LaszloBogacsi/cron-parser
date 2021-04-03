package patternhandler;

import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class NumberPatternHandler implements PatternHandler {
    private final int lowerLimit;
    private final int upperLimit;
    Pattern pattern = Pattern.compile("^\\d+$");

    public NumberPatternHandler(int lowerLimit, int upperLimit) {

        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
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

        return lowerLimit <= parseInt(value) && parseInt(value) <= upperLimit;
    }
}
