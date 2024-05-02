package com.example.recycleme.search;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    private String input;
    private int position;

    public Tokenizer(String input) {
        this.input = input;
        this.position = 0;
    }

    public List<Token> tokenize() throws IllegalArgumentException {
        List<Token> tokens = new ArrayList<>();

        while (position < input.length()) {
            char currentChar = input.charAt(position);

            if (Character.isWhitespace(currentChar)) {
                position++;
            } else if (currentChar == '#') {
                tokens.add(scanTag(true));
            } else if (currentChar == '@') {
                tokens.add(scanTag(false));
            } else if (currentChar == 'a' && checkAnd()) {
                tokens.add(new Token(TokenType.AND, "and"));
            } else if (currentChar == 'o' && checkOr()) {
                tokens.add(new Token(TokenType.OR, "or"));
            } else if (Character.isLetterOrDigit(currentChar)) {
                tokens.add(scanString());
            } else {
                throw new IllegalArgumentException("Illegal token at position " + position);
            }
        }

        return tokens;
    }

    private boolean checkAnd() {
        int remainingChars = input.length() - position;
        if (remainingChars >= 3 && input.substring(position, position + 3).equals("and")) {
            // Check if the next character after "and" is whitespace or if "and" is at the
            // end of the string
            if (remainingChars == 3 || Character.isWhitespace(input.charAt(position + 3))) {
                position += 3; // Increment position to skip "and"
                return true;
            }
        }
        return false;
    }

    private boolean checkOr() {
        int remainingChars = input.length() - position;
        if (remainingChars >= 2 && input.substring(position, position + 2).equals("or")) {
            // Check if the next character after "or" is whitespace or if "or" is at the end
            // of the string
            if (remainingChars == 2 || Character.isWhitespace(input.charAt(position + 2))) {
                position += 2; // Increment position to skip "or"
                return true;
            }
        }
        return false;
    }

    private Token scanString() {
        StringBuilder stringBuilder = new StringBuilder();

        while (position < input.length()
                && (Character.isLetterOrDigit(input.charAt(position)) || input.charAt(position) == '_')) {
            stringBuilder.append(input.charAt(position));
            position++;
        }

        return new Token(TokenType.ITEM, stringBuilder.toString());
    }

    private Token scanTag(boolean isHashTag) {
        position++; // Skip the '@' or '#'
        StringBuilder stringBuilder = new StringBuilder();

        while (position < input.length()
                && (Character.isLetterOrDigit(input.charAt(position)) || input.charAt(position) == '_')) {
            stringBuilder.append(input.charAt(position));
            position++;
        }

        return new Token(isHashTag ? TokenType.HASH_TAG : TokenType.AT_TAG, stringBuilder.toString());
    }
}

class Token {
    private TokenType type;
    private String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}

enum TokenType {
    ITEM,
    HASH_TAG,
    AT_TAG,
    AND,
    OR
}