package patternhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class RangePatternHandlerTest {
    private PatternHandler handler;

    @BeforeEach
    void setUp() {
        handler = new RangePatternHandler(0, 59);
    }

    @Test
    void returnsTrueWhenThePatternMatches() {
        String expressionPart = "1-2";
        assertThat(handler.canHandle(expressionPart), is(true));
    }

    @Test
    void returnsFalseWhenThePatterDoesNotMatches() {
        String expressionPart = "1";
        assertThat(handler.canHandle(expressionPart), is(false));
    }

    @Test
    void returnsFalseForStartValueLessThanTheLowerLimit() {
        handler = new RangePatternHandler(10, 59);
        String expressionPart = "0-59";
        assertThat(handler.canHandle(expressionPart), is(false));
    }

    @Test
    void returnsFalseForEndValueGreaterThanTheUpperLimit() {
        handler = new RangePatternHandler(0, 59);
        String expressionPart = "0-60";
        assertThat(handler.canHandle(expressionPart), is(false));
    }

    @Test
    void returnsFalseForMoreThanTwoDigitsOnTheToEnd() {
        String expressionPart = "10-100";
        assertThat(handler.canHandle(expressionPart), is(false));
    }

    @Test
    void returnsFalseWhenTheFromIsGreaterThanTheTo() {
        String expressionPart = "10-1";
        assertThat(handler.canHandle(expressionPart), is(false));
    }

    @Test
    void returnsTheHandledStringFormattedWithWhiteSpace() {
        String expressionPart = "1-2";
        assertThat(handler.handle(expressionPart), equalTo("1 2"));
    }

    @Test
    void returnsTheHandledStringFormattedWithWhiteSpaceForALongList() {
        String expressionPart = "1-5";
        assertThat(handler.handle(expressionPart), equalTo("1 2 3 4 5"));
    }

    @Test
    void returnsTheHandledStringWhenTheRangeHasOneElement() {
        String expressionPart = "1-1";
        assertThat(handler.handle(expressionPart), equalTo("1"));
    }
}