package parser;


public class MinuteParser implements Parser {
    private final ParserType type = ParserType.MINUTE;
    private PatternHandler listPatternHandler;

    public MinuteParser(PatternHandler listPatternHandler) {

        this.listPatternHandler = listPatternHandler;
    }

    @Override
    public ParserResult parse(String value) {

        return new ParserResult(type, value);
    }

}
