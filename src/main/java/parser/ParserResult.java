package parser;

import java.util.Objects;

public class ParserResult {
    private final ParserType type;
    private final String value;

    ParserResult(ParserType type, String value ){
        this.type = type;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParserResult that = (ParserResult) o;
        return type == that.type && value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    @Override
    public String toString() {
        return "ParserResult{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }
}
