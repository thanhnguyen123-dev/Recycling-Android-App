package com.example.recycleme.search;

public class Tokenizer {

    private String searchQuery;
    private Token currentToken;

    public Tokenizer(String query) {
        this.searchQuery = query;
        this.extractNextToken();
    }

    public void extractNextToken() {
        searchQuery = searchQuery.trim(); // remove whitespace

        if (searchQuery.isEmpty()) {
            currentToken = null;
            return;
        }

        char currentChar = searchQuery.charAt(0);

        if (currentChar == '#') {
            currentToken = scanTag(Token.TokenType.BRAND);
            searchQuery = searchQuery.substring(1);
        } else if (currentChar == '@') {
            currentToken = scanTag(Token.TokenType.MATERIAL);
            searchQuery = searchQuery.substring(1);
        } else if (checkAnd()) {
            currentToken = new Token(Token.TokenType.AND, "and");
        } else if (checkOr()) {
            currentToken = new Token(Token.TokenType.OR, "or");
        } else if (Character.isLetterOrDigit(currentChar)) {
            currentToken = scanString();
        } else {
            throw new Token.IllegalTokenException("Invalid character in search query!");
        }
        searchQuery = searchQuery.substring(currentToken.getToken().length());
    }

    private boolean checkAnd() {
        int remainingChars = searchQuery.length();
        return remainingChars >= 3 && searchQuery.startsWith("and");
    }

    private boolean checkOr() {
        int remainingChars = searchQuery.length();
        return remainingChars >= 3 && searchQuery.startsWith("or");
    }

    private Token scanString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int idx = 0; idx < searchQuery.length() && Character.isLetter(searchQuery.charAt(idx)); idx++) {
            stringBuilder.append(searchQuery.charAt(idx));
        }
        return new Token(Token.TokenType.ITEM, stringBuilder.toString());
    }

    private Token scanTag(Token.TokenType tokenType) {
        StringBuilder stringBuilder = new StringBuilder();

        // Skip the '@' or '#'
        for (int idx = 1; idx < searchQuery.length() && Character.isLetterOrDigit(searchQuery.charAt(idx)); idx++) {
            stringBuilder.append(searchQuery.charAt(idx));
        }
        return new Token(tokenType, stringBuilder.toString());
    }

    public Token getCurrentToken() {
        return currentToken;
    }

    public boolean hasNext() {
        return currentToken != null;
    }
}