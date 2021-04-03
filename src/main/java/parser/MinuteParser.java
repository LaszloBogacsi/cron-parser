package parser;


public class MinuteParser implements Parser {
    private final ParserType type = ParserType.MINUTE;
    @Override
    public ParserResult parse(String value) {
       return new ParserResult(type, value);
    }

}
