package com.example.recycleme.search;


/**
 * This is a class that tokenizes the search query read from the searchbar in the Main activity
 * It extracts valid tokens sequentially based on the grammar and
 * flags invalid characters in the search query
 * This class was implemented by referring to some of the code I wrote for Lab 6
 * which was on Tokenizers and Parsers (<a href="https://gitlab.cecs.anu.edu.au/u7761531/comp2100-lab-06/-/blob/main/src/Task1/Tokenizer.java">Tokenizer</a>)
 * @author Devansu Yadav  
 * @author Adam Ahammed Basheer - u7628910
 */
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
        } else if (Character.isLetterOrDigit(currentChar)) {
            currentToken = scanItem();
        } else {
            throw new Token.IllegalTokenException("Invalid character in search query!");
        }
        searchQuery = searchQuery.substring(currentToken.getToken().length());
    }

    private boolean checkAnd() {
        int remainingChars = searchQuery.length();
        return remainingChars >= 3 && searchQuery.startsWith("and");
    }

    private boolean nextCharsFormAnd(int startIndex) {
        if (startIndex + 3 > searchQuery.length()) {
            return false;
        }
        return searchQuery.substring(startIndex, startIndex + 3).equalsIgnoreCase("and");
    }

    private Token scanItem() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int idx = 0; idx < searchQuery.length(); idx++) {
            char currentChar = searchQuery.charAt(idx);
            if(Character.isLetter(searchQuery.charAt(idx))) {
                stringBuilder.append(currentChar);
            } else if (currentChar == ' ' && !nextCharsFormAnd(idx + 1)) {
                stringBuilder.append(currentChar);
            } else {
                break;
            }
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
