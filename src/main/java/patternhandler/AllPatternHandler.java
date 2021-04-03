package patternhandler;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;

public class AllPatternHandler implements PatternHandler {
    Pattern pattern = Pattern.compile("^[*]$");

    private final int lowerLimit;
    private final int upperLimit;

    public AllPatternHandler(int lowerLimit, int upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    @Override
    public boolean canHandle(String value) {
        return pattern.matcher(value).matches();
    }

    @Override
    public String handle(String value) {
        return IntStream.rangeClosed(lowerLimit, upperLimit)
                .boxed()
                .map(Objects::toString)
                .collect(Collectors.joining(" "));
    }
}
