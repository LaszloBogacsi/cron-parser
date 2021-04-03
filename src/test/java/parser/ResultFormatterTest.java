package parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ResultFormatterTest {
    ResultFormatter formatter;

    @BeforeEach
    void setUp() {
        int firstColumnWidth = 14;
        formatter = new ResultFormatter(firstColumnWidth);
    }

    @Test
    void aFieldNameColumnIsFourteenCharactersWide() {
        ParserResult result = new ParserResult(ParserType.MINUTE, "");
        String output = formatter.format(result);
        assertThat(output, equalTo("minute        "));
        assertThat(output.length(), is(14));
    }

    @Test
    void canFormatAMinuteResult() {
        ParserResult result = new ParserResult(ParserType.MINUTE, "0 15 30 45");
        String output = formatter.format(result);
        assertThat(output, equalTo("minute        0 15 30 45"));
    }

    @Test
    void canFormatAnHourResult() {
        ParserResult result = new ParserResult(ParserType.HOUR, "0 15 30 45");
        String output = formatter.format(result);
        assertThat(output, equalTo("hour          0 15 30 45"));
    }

    @Test
    void canFormatADayOfMonthResult() {
        ParserResult result = new ParserResult(ParserType.DAY_OF_MONTH, "1 2 3");
        String output = formatter.format(result);
        assertThat(output, equalTo("day of month  1 2 3"));
    }

    @Test
    void canFormatAMonthResult() {
        ParserResult result = new ParserResult(ParserType.MONTH, "1 2 3");
        String output = formatter.format(result);
        assertThat(output, equalTo("month         1 2 3"));
    }

    @Test
    void canFormatADayOfWeekResult() {
        ParserResult result = new ParserResult(ParserType.DAY_OF_WEEK, "1 2 3");
        String output = formatter.format(result);
        assertThat(output, equalTo("day of week   1 2 3"));
    }

    @Test
    void canFormatACommandResult() {
        ParserResult result = new ParserResult(ParserType.COMMAND, "/some/test/command");
        String output = formatter.format(result);
        assertThat(output, equalTo("command       /some/test/command"));
    }

}

