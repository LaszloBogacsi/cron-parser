package parser;

import java.util.ArrayList;
import java.util.List;

public class CronParser {
    private final List<Parser> parsers;

    public CronParser(List<Parser> parsers) {
        this.parsers = parsers;
    }

    public List<ParserResult> parse(String[] strings) {
        List<ParserResult> results = new ArrayList<>();
        for (int i = 0; i < parsers.size(); i++) {
            if (i < strings.length) {
                results.add(parsers.get(i).parse(strings[i]));
            }
        }
        return results;
    }
}
