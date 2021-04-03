package parser;

import java.util.Map;

public class ResultFormatter {
    private final char padding = ' ';
    private int firstColumnWidth;
    private static Map<ParserType, String> fieldNames = Map.of(
            ParserType.MINUTE, "minute",
            ParserType.HOUR, "hour",
            ParserType.DAY_OF_MONTH, "day of month",
            ParserType.MONTH, "month",
            ParserType.DAY_OF_WEEK, "day of week",
            ParserType.COMMAND, "command"
    );

    public ResultFormatter(int firstColumnWidth) {
        this.firstColumnWidth = firstColumnWidth;
    }

    public String format(ParserResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append(fieldNames.getOrDefault(result.getType(), "default"));
        while (sb.length() < firstColumnWidth) {
            sb.append(padding);
        }
        sb.append(result.getValue());
        return sb.toString();
    }
}
