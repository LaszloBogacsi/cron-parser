package parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static parser.ParserType.MINUTE;

class MinuteParserTest {
    private Parser parser;

    @BeforeEach
    void setUp() {
        PatternHandler listPatternHandler = new ListPatterHandler();
        parser = new MinuteParser(listPatternHandler);

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

    @Disabled
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
}