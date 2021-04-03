package patternhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patternhandler.ListPatterHandler;
import patternhandler.PatternHandler;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
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