package patternhandler;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AllPatternHandler implements PatternHandler {
    Pattern pattern = Pattern.compile("^[*]$");
    private final Range range;

    public AllPatternHandler(Range range) {
        this.range = range;
    }

    @Override
    public boolean canHandle(String value) {
        return pattern.matcher(value).matches();
    }

    @Override
    public String handle(String value) {
        return IntStream.rangeClosed(range.getLowerLimit(), range.getUpperLimit())
                .boxed()
                .map(Objects::toString)
                .collect(Collectors.joining(" "));
    }
}
