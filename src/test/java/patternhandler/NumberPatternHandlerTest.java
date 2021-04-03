package patternhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class NumberPatternHandlerTest {
    private PatternHandler handler;

    @BeforeEach
    void setUp() {
        Range range = new Range(0, 59);
        handler = new NumberPatternHandler(range);
    }

    @Test
    void returnsTrueWhenThePatternMatchesANumber() {
        String expressionPart = "1";
        assertThat(handler.canHandle(expressionPart), is(true));
    }

    @Test
    void returnsTrueWhenThePatternMatchesATwoDigitNumber() {
        String expressionPart = "12";
        assertThat(handler.canHandle(expressionPart), is(true));
    }

    @Test
    void returnsTrueWhenThePatternMatchesATheLowerLimitNumber() {
        String expressionPart = "0";
        assertThat(handler.canHandle(expressionPart), is(true));
    }

    @Test
    void returnsTrueWhenThePatternMatchesATheUpperLimitNumber() {
        String expressionPart = "59";
        assertThat(handler.canHandle(expressionPart), is(true));
    }

    @Test
    void returnsFalseWhenTheNumberIsLessThanTheLowerLimit() {
        handler = new NumberPatternHandler(new Range(10, 59));
        String expressionPart = "1";
        assertThat(handler.canHandle(expressionPart), is(false));
    }

    @Test
    void returnsFalseWhenTheNumberIsGreaterThanTheUpperLimit() {
        String expressionPart = "60";
        assertThat(handler.canHandle(expressionPart), is(false));
    }

    @Test
    void returnsTheHandledString() {
        String expressionPart = "1";
        assertThat(handler.handle(expressionPart), equalTo("1"));
    }

    @Test
    void returnsTheTwoDigitHandledString() {
        String expressionPart = "12";
        assertThat(handler.handle(expressionPart), equalTo("12"));
    }
}