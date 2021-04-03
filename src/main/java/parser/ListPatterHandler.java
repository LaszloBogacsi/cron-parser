package parser;

public class ListPatterHandler implements PatternHandler {
    @Override
    public boolean canHandle(String value) {
        return false;
    }

    @Override
    public String handle(String value) {
        return null;
    }
}
