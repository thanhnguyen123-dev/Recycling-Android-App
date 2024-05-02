package com.example.recycleme.search;

import java.util.Objects;

public class Token {

    public enum TokenType {
        ITEM,
        BRAND,
        MATERIAL,
        AND,
        OR
    }

    private TokenType type;
    private String token;

    public Token(TokenType type, String token) {
        this.type = type;
        this.token = token;
    }

    public static class IllegalTokenException extends IllegalArgumentException {
        public IllegalTokenException(String errorMessage) {
            super(errorMessage);
        }
    }

    public TokenType getType() {
        return type;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return getToken();
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Token)) return false;
        return this.type == ((Token) other).getType() && this.token.equals(((Token) other).getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, type);
    }
}
