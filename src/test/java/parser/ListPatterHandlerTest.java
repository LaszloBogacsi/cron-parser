package parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ListPatterHandlerTest {
    private PatternHandler handler;

    @BeforeEach
    void setUp() {
        handler = new ListPatterHandler();
    }

    @Test
    void returnsTrueWhenThePatternMatches() {
        String expressionPart = "1,2";
        assertThat(handler.canHandle(expressionPart), is(true));
    }

    @Test
    void returnsFalseWhenThePatterDoesNotMatches() {
        String expressionPart = "1";
        assertThat(handler.canHandle(expressionPart), is(false));
    }
}