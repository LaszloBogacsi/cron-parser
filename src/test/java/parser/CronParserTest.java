package parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patternhandler.*;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.List.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static parser.ParserType.*;


public class CronParserTest {
    private CronParser parser;

    @BeforeEach
    void setUp() {
        Range minuteRange = new Range(0, 59);
        Parser minuteParser = new FieldParser(MINUTE, of(
                new NumberPatternHandler(minuteRange),
                new ListPatternHandler(minuteRange),
                new RangePatternHandler(minuteRange),
                new IncrementPatternHandler(minuteRange),
                new AllPatternHandler(minuteRange))
        );

        Range hourRange = new Range(0, 59);

        Parser hourParser = new FieldParser(HOUR, of(
                new NumberPatternHandler(hourRange),
                new ListPatternHandler(hourRange),
                new RangePatternHandler(hourRange),
                new IncrementPatternHandler(hourRange),
                new AllPatternHandler(hourRange))
        );

        Range dayOfMonthRange = new Range(1, 31);

        Parser dayOfMonthParser = new FieldParser(DAY_OF_MONTH, of(
                new NumberPatternHandler(dayOfMonthRange),
                new ListPatternHandler(dayOfMonthRange),
                new RangePatternHandler(dayOfMonthRange),
                new IncrementPatternHandler(dayOfMonthRange),
                new AllPatternHandler(dayOfMonthRange),
                new OptionalPatternHandler())
        );
        Range monthRange = new Range(1, 12);

        Parser monthParser = new FieldParser(MONTH, of(
                new NumberPatternHandler(monthRange),
                new ListPatternHandler(monthRange),
                new RangePatternHandler(monthRange),
                new IncrementPatternHandler(monthRange),
                new AllPatternHandler(monthRange))
        );

        Range dayOfWeekRange = new Range(1, 7);

        Parser dayOfWeekParser = new FieldParser(DAY_OF_WEEK, of(
                new NumberPatternHandler(dayOfWeekRange),
                new ListPatternHandler(dayOfWeekRange),
                new RangePatternHandler(dayOfWeekRange),
                new IncrementPatternHandler(dayOfWeekRange),
                new AllPatternHandler(dayOfWeekRange),
                new OptionalPatternHandler())
        );
        Parser commandParser = new FieldParser(COMMAND, of(new CommandPatternHandler()));

        List<Parser> parsers = Arrays.asList(minuteParser, hourParser, dayOfMonthParser, monthParser, dayOfWeekParser, commandParser);
        parser = new CronParser(parsers);
    }

    @Test
    void canParseASimpleExpressionWhereDayOfMonthOptional() {
        List<ParserResult> results = parser.parse(new String[]{"0", "0", "?", "1", "1"});

        assertThat(results, containsInAnyOrder(
                new ParserResult(MINUTE, "0"),
                new ParserResult(HOUR, "0"),
                new ParserResult(DAY_OF_MONTH, "?"),
                new ParserResult(MONTH, "1"),
                new ParserResult(DAY_OF_WEEK, "1"))
        );
    }

    @Test
    void canParseASimpleExpressionWithDayOfWeekOptional() {
        List<ParserResult> results = parser.parse(new String[]{"0", "0", "1", "1", "?"});

        assertThat(results, containsInAnyOrder(
                new ParserResult(MINUTE, "0"),
                new ParserResult(HOUR, "0"),
                new ParserResult(DAY_OF_MONTH, "1"),
                new ParserResult(MONTH, "1"),
                new ParserResult(DAY_OF_WEEK, "?"))
        );
    }

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


    @Test
    void canParseASimpleExpressionWithCommand() {
        List<ParserResult> results = parser.parse(new String[]{"0", "0", "1", "1", "?", "/test/command"});

        assertThat(results, containsInAnyOrder(
                new ParserResult(MINUTE, "0"),
                new ParserResult(HOUR, "0"),
                new ParserResult(DAY_OF_MONTH, "1"),
                new ParserResult(MONTH, "1"),
                new ParserResult(DAY_OF_WEEK, "?"),
                new ParserResult(COMMAND, "/test/command"))
        );
    }

    @Test
    void canParseAsManyFieldAsManyParsersItHas() {
        List<ParserResult> results = parser.parse(new String[]{"0", "0", "1", "1", "?", "./test/command", "extrafield"});

        assertThat(results, containsInAnyOrder(
                new ParserResult(MINUTE, "0"),
                new ParserResult(HOUR, "0"),
                new ParserResult(DAY_OF_MONTH, "1"),
                new ParserResult(MONTH, "1"),
                new ParserResult(DAY_OF_WEEK, "?"),
                new ParserResult(COMMAND, "./test/command"))
        );
    }

    @Test
    void canParseLessFieldsThenParsersItHas() {
        List<ParserResult> results = parser.parse(new String[]{"0", "0", "1", "1"});

        assertThat(results, containsInAnyOrder(
                new ParserResult(MINUTE, "0"),
                new ParserResult(HOUR, "0"),
                new ParserResult(DAY_OF_MONTH, "1"),
                new ParserResult(MONTH, "1"))
        );
    }

    @Test
    void canParseEmptyInput() {
        List<ParserResult> results = parser.parse(new String[]{});
        assertThat(results, is(emptyList()));
    }
}
