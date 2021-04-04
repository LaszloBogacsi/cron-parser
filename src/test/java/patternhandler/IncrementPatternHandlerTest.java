package patternhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class IncrementPatternHandlerTest {
    private PatternHandler handler;

    @BeforeEach
    void setUp() {
        Range range = new Range(0, 59);
        handler = new IncrementPatternHandler(range);
    }

    @Test
    void returnsTrueWhenThePatternMatches() {
        String expressionPart = "0/20";
        assertThat(handler.canHandle(expressionPart), is(true));
    }

    @Test
    void returnsTrueWhenThePatternMatchesWithAsteriskAsStartValue() {
        String expressionPart = "*/20";
        assertThat(handler.canHandle(expressionPart), is(true));
    }

    @Test
    void returnsFalseWhenThePatterDoesNotMatches() {
        String expressionPart = "1";
        assertThat(handler.canHandle(expressionPart), is(false));
    }

    @Test
    void returnsFalseForMalformedValue() {
        String expressionPart = "1*/20";
        assertThat(handler.canHandle(expressionPart), is(false));
    }

    @Test
    void returnsFalseWhenStartValueIsLessThanTheLowerLimit() {
        handler = new IncrementPatternHandler(new Range(10, 59));
        String expressionPart = "9/20";
        assertThat(handler.canHandle(expressionPart), is(false));
    }

    @Test
    void returnsFalseWhenStartValueIsGreaterThanTheUpperLimit() {
        String expressionPart = "90/20";
        assertThat(handler.canHandle(expressionPart), is(false));
    }

    @Test
    void returnsTheHandledStringFormattedWithWhiteSpace() {
        String expressionPart = "1/20";
        assertThat(handler.handle(expressionPart), equalTo("1 21 41"));
    }

    @Test
    void returnsTheHandledStringWhenTheIncrementValueIsGreaterThanTheUpperLimit() {
        String expressionPart = "1/60";
        assertThat(handler.handle(expressionPart), equalTo("1"));
    }

    @Test
    void returnsTheHandledStringWhenTheFirstIncrementValueWouldBEGreaterThanTheUpperLimit() {
        handler = new IncrementPatternHandler(new Range(10, 59));
        String expressionPart = "50/20";
        assertThat(handler.handle(expressionPart), equalTo("50"));
    }

    @Test
    void returnsTheHandledStringWhenTheStartValueIsAnAsterisk() {
        handler = new IncrementPatternHandler(new Range(10, 59));
        String expressionPart = "*/10";
        assertThat(handler.handle(expressionPart), equalTo("10 20 30 40 50"));
    }

    @Test
    void returnsTheHandledStringWhenLastValueIsTheUpperLimit() {
        handler = new IncrementPatternHandler(new Range(10, 59));
        String expressionPart = "9/10";
        assertThat(handler.handle(expressionPart), equalTo("9 19 29 39 49 59"));
    }
}