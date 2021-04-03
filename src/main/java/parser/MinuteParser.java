package parser;


import java.util.List;
import java.util.Optional;

public class MinuteParser implements Parser {
    private final ParserType type = ParserType.MINUTE;
    private final List<PatternHandler> handlers;

    public MinuteParser(List<PatternHandler> patternHandlers) {
        handlers = patternHandlers;
    }

    @Override
    public ParserResult parse(String value) {
        Optional<PatternHandler> maybeHandler = handlers.stream()
                .filter(handler -> handler.canHandle(value))
                .findFirst();
        return maybeHandler
                .map(patternHandler -> new ParserResult(type, patternHandler.handle(value)))
                .orElseGet(() -> new ParserResult(type, "No Handler Found For: " + value)); // No handler found return original value

    }

}
