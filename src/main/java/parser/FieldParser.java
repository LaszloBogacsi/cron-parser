package parser;


import patternhandler.PatternHandler;

import java.util.List;
import java.util.Optional;

public class FieldParser implements Parser {
    private final ParserType type;
    private final List<PatternHandler> handlers;

    public FieldParser(ParserType type, List<PatternHandler> patternHandlers) {
        this.type = type;
        this.handlers = patternHandlers;
    }

    @Override
    public ParserResult parse(String value) {
        Optional<PatternHandler> maybeHandler = handlers.stream()
                .filter(handler -> handler.canHandle(value))
                .findFirst();
        return maybeHandler
                .map(patternHandler -> new ParserResult(type, patternHandler.handle(value)))
                .orElseGet(() -> new ParserResult(type, "No Handler Found For: " + value));

    }

}
