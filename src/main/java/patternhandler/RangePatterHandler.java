package patternhandler;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;

public class RangePatterHandler implements PatternHandler {
    Pattern pattern = Pattern.compile("^\\d{1,2}[-]\\d{1,2}$");

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
        return parseInt(rangeLimits[0]) <= parseInt(rangeLimits[1]);
    }
}
