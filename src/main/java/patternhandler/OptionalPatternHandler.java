package patternhandler;

import java.util.regex.Pattern;

public class OptionalPatternHandler implements PatternHandler {
    Pattern pattern = Pattern.compile("^[?]$");


    @Override
    public boolean canHandle(String value) {
        return pattern.matcher(value).matches();
    }

    @Override
    public String handle(String value) {
        return value;
    }
}
