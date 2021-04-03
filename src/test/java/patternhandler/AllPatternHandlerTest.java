package patternhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class AllPatternHandlerTest {
    private PatternHandler handler;

    @BeforeEach
    void setUp() {
        handler = new AllPatternHandler(0, 59);
    }

    @Test
    void returnsTrueWhenThePatternMatches() {
        String expressionPart = "*";
        assertThat(handler.canHandle(expressionPart), is(true));
    }

    @Test
    void returnsFalseOnMalformedPattern() {
        String expressionPart = "**";
        assertThat(handler.canHandle(expressionPart), is(false));
    }

    @Test
    void returnsFalseWhenThePatternDoesNotMatch() {
        String expressionPart = "1";
        assertThat(handler.canHandle(expressionPart), is(false));
    }


    @Test
    void returnsTheHandledStringFormattedWithWhiteSpace() {
        handler = new AllPatternHandler(0, 10);
        String expressionPart = "*";
        assertThat(handler.handle(expressionPart), equalTo("0 1 2 3 4 5 6 7 8 9 10"));
    }
}