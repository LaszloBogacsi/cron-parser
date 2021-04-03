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

class MinuteParserTest {
    private Parser parser;

    @BeforeEach
    void setUp() {
        PatternHandler numberPatternHandler = new NumberPatternHandler(0, 59);
        PatternHandler listPatternHandler = new ListPatternHandler();
        PatternHandler rangePatternHandler = new RangePatternHandler();
        PatternHandler incrementPatternHandler = new IncrementPatternHandler(0, 59);
        PatternHandler allPatternHandler = new AllPatternHandler(0, 59);
        parser = new MinuteParser(of(
                numberPatternHandler,
                listPatternHandler,
                rangePatternHandler,
                incrementPatternHandler,
                allPatternHandler)
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
    void returnsTheOriginalValueWhenNoSuitableHandlerFound() {
        ParserResult parserResult = parser.parse("1-,5");
        assertThat(parserResult, equalTo(new ParserResult(MINUTE, "No Handler Found For: 1-,5")));
    }
}