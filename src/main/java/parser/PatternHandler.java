package parser;

public interface PatternHandler {
    boolean canHandle(String value);
    String handle(String value);
}
