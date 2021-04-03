package parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import patternhandler.ListPatternHandler;
import patternhandler.PatternHandler;

import java.util.Arrays;
import java.util.List;

import static java.util.List.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static parser.ParserType.*;


public class CronParserTest {
    private CronParser parser;

    @BeforeEach
    void setUp() {
        PatternHandler listPatterHandler = new ListPatternHandler();
        List<Parser> parsers = Arrays.asList(new MinuteParser(of(listPatterHandler)));
        parser = new CronParser(parsers);
    }

    @Disabled
    @Test
    void canParseASimpleExpression() {
        List<ParserResult> results = parser.parse(new String[]{"0", "0", "?", "1", "0"});

        assertThat(results, containsInAnyOrder(
                new ParserResult(MINUTE, "0"),
                new ParserResult(HOUR, "0"),
                new ParserResult(DAY_OF_MONTH, "?"),
                new ParserResult(MONTH, "1"),
                new ParserResult(DAY_OF_WEEK, "0")));
    }

    @Disabled
    @Test
    void canParseAComplexExpressionWithIncrementListAndRange() {
        List<ParserResult> results = parser.parse(new String[]{"*/15", "0", "1,15", "*", "1-5"});

        assertThat(results, containsInAnyOrder(
                new ParserResult(MINUTE, "0 15 30 45"),
                new ParserResult(HOUR, "0"),
                new ParserResult(DAY_OF_MONTH, "1 15"),
                new ParserResult(MONTH, "1 2 3 4 5 6 7 8 9 10 11 12"),
                new ParserResult(DAY_OF_WEEK, "1 2 3 4 5")));
    }
}
