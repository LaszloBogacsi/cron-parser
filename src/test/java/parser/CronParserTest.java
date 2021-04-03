package parser;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static parser.ParserType.*;


public class CronParserTest {
    @Test
    void canParseASimpleExpression() {
        List<Parser> parsers = Arrays.asList(new MinuteParser());
        CronParser parser = new CronParser(parsers);
        List<ParserResult> results = parser.parse(new String[]{"0", "0", "?", "1", "0"});

        assertThat(results, containsInAnyOrder(
                new ParserResult(MINUTE, "0"),
                new ParserResult(HOUR, "0"),
                new ParserResult(DAY_OF_MONTH, "?"),
                new ParserResult(MONTH, "1"),
                new ParserResult(DAY_OF_WEEK, "0")));
    }

    @Test
    void canParseAComplexExpressionWithIncrementListAndRange() {
        List<Parser> parsers = Arrays.asList(new MinuteParser());
        CronParser parser = new CronParser(parsers);
        List<ParserResult> results = parser.parse(new String[]{"*/15", "0", "1,15", "*", "1-5"});

        assertThat(results, containsInAnyOrder(
                new ParserResult(MINUTE, "0 15 30 45"),
                new ParserResult(HOUR, "0"),
                new ParserResult(DAY_OF_MONTH, "1 15"),
                new ParserResult(MONTH, "1 2 3 4 5 6 7 8 9 10 11 12"),
                new ParserResult(DAY_OF_WEEK, "1 2 3 4 5")));
    }
}
