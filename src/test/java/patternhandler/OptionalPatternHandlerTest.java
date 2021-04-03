package patternhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class OptionalPatternHandlerTest {
    private PatternHandler handler;

    @BeforeEach
    void setUp() {
        handler = new OptionalPatternHandler();
    }

    @Test
    void returnsTrueWhenThePatternMatches() {
        String expressionPart = "?";
        assertThat(handler.canHandle(expressionPart), is(true));
    }

    @Test
    void returnsFalseOnMalformedPattern() {
        String expressionPart = "??";
        assertThat(handler.canHandle(expressionPart), is(false));
    }

    @Test
    void returnsFalseWhenThePatternDoesNotMatch() {
        String expressionPart = "1";
        assertThat(handler.canHandle(expressionPart), is(false));
    }

    @Test
    void returnsTheHandledStringFormattedWithWhiteSpace() {
        String expressionPart = "?";
        assertThat(handler.handle(expressionPart), equalTo("?"));
    }
}