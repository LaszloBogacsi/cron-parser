package parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patternhandler.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.List.of;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static parser.ParserType.MINUTE;

class FieldParserTest {
    private Parser parser;

    @BeforeEach
    void setUp() {
        Range minuteRange = new Range(0, 59);
        PatternHandler numberPatternHandler = new NumberPatternHandler(minuteRange);
        PatternHandler listPatternHandler = new ListPatternHandler(minuteRange);
        PatternHandler rangePatternHandler = new RangePatternHandler(minuteRange);
        PatternHandler incrementPatternHandler = new IncrementPatternHandler(minuteRange);
        PatternHandler allPatternHandler = new AllPatternHandler(minuteRange);
        PatternHandler optionalPatternHandler = new OptionalPatternHandler();
        PatternHandler anyPatternHandler = new CommandPatternHandler();
        parser = new FieldParser(MINUTE, of(
                numberPatternHandler,
                listPatternHandler,
                rangePatternHandler,
                incrementPatternHandler,
                allPatternHandler,
                optionalPatternHandler,
                anyPatternHandler)
        );

    }

    @Test
    void canParseANumberValue() {
        ParserResult parserResult = parser.parse("1");
        assertThat(parserResult, equalTo(new ParserResult(MINUTE, "1")));
    }

    @Test
    void canParseAListValue() {
        ParserResult parserResult = parser.parse("1,2");
        assertThat(parserResult, equalTo(new ParserResult(MINUTE, "1 2")));
    }

    @Test
    void canParseARangeValue() {
        ParserResult parserResult = parser.parse("1-5");
        assertThat(parserResult, equalTo(new ParserResult(MINUTE, "1 2 3 4 5")));
    }

    @Test
    void canParseAnIncrementRangeValue() {
        ParserResult parserResult = parser.parse("1/10");
        assertThat(parserResult, equalTo(new ParserResult(MINUTE, "1 11 21 31 41 51")));
    }

    @Test
    void canParseAnAllValue() {
        ParserResult parserResult = parser.parse("*");
        String range0_59 = IntStream.rangeClosed(0, 59).boxed().map(Object::toString).collect(Collectors.joining(" "));
        assertThat(parserResult, equalTo(new ParserResult(MINUTE, range0_59)));
    }

    @Test
    void canParseAnOptionalValue() {
        ParserResult parserResult = parser.parse("?");
        assertThat(parserResult, equalTo(new ParserResult(MINUTE, "?")));
    }

    @Test
    void returnsTheOriginalValueWhenNoSuitableHandlerFound() {
        ParserResult parserResult = parser.parse("1-,5");
        assertThat(parserResult, equalTo(new ParserResult(MINUTE, "No Handler Found For: 1-,5")));
    }


    @Test
    void canParseACommandValue() {
        ParserResult parserResult = parser.parse("/some/test/command");
        assertThat(parserResult, equalTo(new ParserResult(MINUTE, "/some/test/command")));
    }

}