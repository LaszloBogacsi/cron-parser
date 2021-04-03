import parser.CronParser;
import parser.FieldParser;
import parser.Parser;
import parser.ParserResult;
import patternhandler.*;

import java.util.Arrays;
import java.util.List;

import static java.util.List.of;
import static parser.ParserType.*;

public class CronParserApplication {
    public static void main(String[] args) {
        CronParser cronParser = makeParser();
        List<ParserResult> parserResults = cronParser.parse(args);
        for (ParserResult parserResult : parserResults) {
            System.out.println("parserResult = " + parserResult);
        }
    }

    private static CronParser makeParser() {
        Range minuteRange = new Range(0, 59);
        Parser minuteParser = new FieldParser(MINUTE, of(
                new NumberPatternHandler(minuteRange),
                new ListPatternHandler(minuteRange),
                new RangePatternHandler(minuteRange),
                new IncrementPatternHandler(minuteRange),
                new AllPatternHandler(minuteRange))
        );

        Range hourRange = new Range(0, 59);

        Parser hourParser = new FieldParser(HOUR, of(
                new NumberPatternHandler(hourRange),
                new ListPatternHandler(hourRange),
                new RangePatternHandler(hourRange),
                new IncrementPatternHandler(hourRange),
                new AllPatternHandler(hourRange))
        );

        Range dayOfMonthRange = new Range(1, 31);

        Parser dayOfMonthParser = new FieldParser(DAY_OF_MONTH, of(
                new NumberPatternHandler(dayOfMonthRange),
                new ListPatternHandler(dayOfMonthRange),
                new RangePatternHandler(dayOfMonthRange),
                new IncrementPatternHandler(dayOfMonthRange),
                new AllPatternHandler(dayOfMonthRange),
                new OptionalPatternHandler())
        );
        Range monthRange = new Range(1, 12);

        Parser monthParser = new FieldParser(MONTH, of(
                new NumberPatternHandler(monthRange),
                new ListPatternHandler(monthRange),
                new RangePatternHandler(monthRange),
                new IncrementPatternHandler(monthRange),
                new AllPatternHandler(monthRange))
        );

        Range dayOfWeekRange = new Range(1, 7);

        Parser dayOfWeekParser = new FieldParser(DAY_OF_WEEK, of(
                new NumberPatternHandler(dayOfWeekRange),
                new ListPatternHandler(dayOfWeekRange),
                new RangePatternHandler(dayOfWeekRange),
                new IncrementPatternHandler(dayOfWeekRange),
                new AllPatternHandler(dayOfWeekRange),
                new OptionalPatternHandler())
        );

        Parser commandParser = new FieldParser(COMMAND, of(new CommandPatternHandler()));

        List<Parser> parsers = Arrays.asList(minuteParser, hourParser, dayOfMonthParser, monthParser, dayOfWeekParser, commandParser);
        return new CronParser(parsers);
    }
}
