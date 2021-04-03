package patternhandler;

import java.util.regex.Pattern;

public class ListPatternHandler implements PatternHandler {
    Pattern pattern = Pattern.compile("\\d+,\\d+");
    @Override
    public boolean canHandle(String value) {
        return pattern.matcher(value).matches();
    }

    @Override
    public String handle(String value) {
        return value.replaceAll(",", " ");
    }
}
