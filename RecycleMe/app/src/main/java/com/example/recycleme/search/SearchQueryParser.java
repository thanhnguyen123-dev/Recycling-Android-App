package com.example.recycleme.search;

import java.util.HashSet;

public class SearchQueryParser {
    public static class InvalidQueryException extends IllegalArgumentException {
        public InvalidQueryException(String errorMessage) {
            super(errorMessage);
        }
    }

    Tokenizer tokenizer;

    public SearchQueryParser(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    /**
     * Adheres to the grammar rule:
     * <S>    ::= <E><L><F> | <E> | <F>
     *
     * @return type: SearchExp.
     */
    public SearchExp parseSearchQuery() throws InvalidQueryException {
        if(tokenizer.hasNext()) {
            Token current = tokenizer.getCurrentToken();
            switch(current.getType()) {
                case ITEM: {
                    return parseItem();
                }
                case BRAND:
                case MATERIAL: {
                    return parseBrandAndMaterial();
                }
                default: {
                    throw new InvalidQueryException("Unexpected Token!");
                }
            }
        } else {
            throw new InvalidQueryException("Empty query!");
        }
    }

    /**
     * Adheres to the grammar rule:
     * <S>    ::= <E><L><F>
     *
     * @return type: SearchExp.
     */
    private SearchExp parseItem() throws InvalidQueryException {
        String item = parseName();
        SearchExp brandAndMaterial = parseBrandAndMaterial();

        return new SearchExp(item, brandAndMaterial.getBrand(), brandAndMaterial.getMaterial());
    }

    /**
     * Adheres to the grammar rule:
     * <L>    ::= and
     */
    private void parseAnd() throws InvalidQueryException {
        if (tokenizer.hasNext() && tokenizer.getCurrentToken().getType() == Token.TokenType.AND) {
            tokenizer.extractNextToken();
        } else {
            throw new InvalidQueryException("Invalid Query: Expected 'and'!");
        }
    }

    /**
     * Adheres to the grammar rule:
     * <N>    ::= "<Z>"
     * <Z>    ::= <C> | <C><Z>
     * <C>    ::= a | b | c | d | ....... | z
     *
     * @return type: String.
     */
    private String parseName() throws InvalidQueryException {
        if (tokenizer.hasNext() && tokenizer.getCurrentToken().getType() != Token.TokenType.AND) {
            String result = tokenizer.getCurrentToken().getToken();
            tokenizer.extractNextToken();
            return result;
        } else {
            throw new InvalidQueryException("Invalid Query: Expected item name, brand name, or material!");
        }
    }

    /**
     * Adheres to the grammar rule:
     * <F>    ::= #<N> | @<N> | <F><L><F>
     *
     * @return type: SearchExp.
     */
    private SearchExp parseBrandAndMaterial() throws InvalidQueryException {
        String item = "";
        HashSet<String> brand = new HashSet<>();
        HashSet<String> material = new HashSet<>();

        while (tokenizer.hasNext()) {
            Token current = tokenizer.getCurrentToken();
            switch (current.getType()) {
                case BRAND: {
                    brand.add(parseName());
                    if (tokenizer.hasNext() && tokenizer.getCurrentToken().getType() != Token.TokenType.AND) {
                        throw new InvalidQueryException("Invalid Query: Expected 'and' between brand and material!");
                    }
                    break;
                }
                case MATERIAL: {
                    material.add(parseName());
                    if (tokenizer.hasNext() && tokenizer.getCurrentToken().getType() != Token.TokenType.AND) {
                        throw new InvalidQueryException("Invalid Query: Expected 'and' between brand and material!");
                    }
                    break;
                }
                case AND: {
                    parseAnd();
                    if (!tokenizer.hasNext() || tokenizer.getCurrentToken().getType() == Token.TokenType.AND) {
                        throw new InvalidQueryException("Invalid Query: Expected brand name or material after 'and'!");
                    }
                    break;
                }
                default: {
                    throw new InvalidQueryException("Invalid Query: Expected brand name or material!");
                }
            }
        }
        return new SearchExp(item, brand, material);
    }
}
