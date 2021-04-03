package patternhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class ListPatternHandlerTest {
    private PatternHandler handler;

    @BeforeEach
    void setUp() {
        Range range = new Range(0, 59);
        handler = new ListPatternHandler(range);
    }

    @Test
    void returnsTrueWhenThePatternMatches() {
        String expressionPart = "1,2";
        assertThat(handler.canHandle(expressionPart), is(true));
    }

    @Test
    void returnsTrueForAListOfThree() {
        String expressionPart = "1,2,3";
        assertThat(handler.canHandle(expressionPart), is(true));
    }

    @Test
    void returnsFalseWhenThePatterDoesNotMatch() {
        String expressionPart = "1";
        assertThat(handler.canHandle(expressionPart), is(false));
    }

    @Test
    void returnsFalseWhenAnyListValueIsLessThanTheLowerLimit() {
        handler = new ListPatternHandler(new Range(10, 59));
        String expressionPart = "1,2,10,20";
        assertThat(handler.canHandle(expressionPart), is(false));
    }

    @Test
    void returnsFalseWhenAnyListValueIsGreaterThanTheUpperLimit() {
        String expressionPart = "1,2,10,59,65,70";
        assertThat(handler.canHandle(expressionPart), is(false));
    }

    @Test
    void returnsTheHandledStringFormattedWithWhiteSpace() {
        String expressionPart = "1,2";
        assertThat(handler.handle(expressionPart), equalTo("1 2"));
    }

    @Test
    void returnsTheHandledStringFormattedWithWhiteSpaceForALongList() {
        String expressionPart = "1,2,3,4,5";
        assertThat(handler.handle(expressionPart), equalTo("1 2 3 4 5"));
    }
}