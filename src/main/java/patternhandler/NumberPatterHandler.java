package patternhandler;

import java.util.regex.Pattern;

public class NumberPatterHandler implements PatternHandler {
    Pattern pattern = Pattern.compile("^\\d{1,2}$");

    @Override
    public boolean canHandle(String value) {
        return pattern.matcher(value).matches();

    }

    @Override
    public String handle(String value) {
        return value;
    }
}
