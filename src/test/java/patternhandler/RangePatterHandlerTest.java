package patternhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patternhandler.PatternHandler;
import patternhandler.RangePatterHandler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class RangePatterHandlerTest {
    private PatternHandler handler;

    @BeforeEach
    void setUp() {
        handler = new RangePatterHandler();
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
    void returnsFalseForMoreThanTwoDigitsOnBothEnds() {
        String expressionPart = "111-112";
        assertThat(handler.canHandle(expressionPart), is(false));
    }

    @Test
    void returnsFalseForMoreThanTwoDigitsOnTheStartEnd() {
        String expressionPart = "111-10";
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