package patternhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patternhandler.NumberPatterHandler;
import patternhandler.PatternHandler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class NumberPatterHandlerTest {
    private PatternHandler handler;

    @BeforeEach
    void setUp() {
        handler = new NumberPatterHandler();
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
    void returnsFalseForMoreThanTwoDigits() {
        String expressionPart = "111";
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