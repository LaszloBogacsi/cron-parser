package parser;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static parser.ParserType.MINUTE;

class MinuteParserTest {
    @Test
    void canParseANumberValue() {
        Parser parser = new MinuteParser();
        ParserResult parserResult = parser.parse("1");
        assertThat(parserResult, equalTo(new ParserResult(MINUTE, "1")));
    }
}