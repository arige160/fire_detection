package com.fireDetection.cot.security;
import java.util.IllformedLocaleException;
import java.util.function.Supplier;
import java.util.stream.Stream;

public enum GrantType implements Supplier<String> {
    PASSWORD("password"),
    REFRESH_TOKEN("Refresh_token");
    public final String value;
    GrantType(String value) {
        this.value = value;
    }
    @Override
    public String get() {
        return value;
    }
    static GrantType parse(String value) {
        return Stream.of(GrantType.values())
                .filter(g -> g.get().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllformedLocaleException("There's no Grant of type " + value));
    }
}