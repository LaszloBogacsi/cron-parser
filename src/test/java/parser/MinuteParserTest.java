package parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import patternhandler.ListPatterHandler;
import patternhandler.NumberPatterHandler;
import patternhandler.PatternHandler;
import patternhandler.RangePatterHandler;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.List.of;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static parser.ParserType.MINUTE;

class MinuteParserTest {
    private Parser parser;

    @BeforeEach
    void setUp() {
        PatternHandler numberPatternHandler = new NumberPatterHandler();
        PatternHandler listPatternHandler = new ListPatterHandler();
        PatternHandler rangePatternHandler = new RangePatterHandler();
        parser = new MinuteParser(of(numberPatternHandler, listPatternHandler, rangePatternHandler));

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

    @Disabled
    @Test
    void canParseAnIncrementRangeValueWithStartValue() {
        ParserResult parserResult = parser.parse("1/10");
        assertThat(parserResult, equalTo(new ParserResult(MINUTE, "1 11 21 31 41 51")));
    }

    @Disabled
    @Test
    void canParseAnIncrementRangeValueWithoutStartValue() {
        ParserResult parserResult = parser.parse("*/10");
        assertThat(parserResult, equalTo(new ParserResult(MINUTE, "0 10 20 30 40 50")));
    }

    @Disabled
    @Test
    void canParseAnAllValue() {
        ParserResult parserResult = parser.parse("*");
        String range0_59 = IntStream.rangeClosed(0, 59).boxed().map(Object::toString).collect(Collectors.joining(" "));
        assertThat(parserResult, equalTo(new ParserResult(MINUTE, range0_59)));
    }

    @Test
    void returnsTheOriginalValueWhenNoSuitableHandlerFound() {
        ParserResult parserResult = parser.parse("1-,5");
        assertThat(parserResult, equalTo(new ParserResult(MINUTE, "No Handler Found For: 1-,5")));
    }
}