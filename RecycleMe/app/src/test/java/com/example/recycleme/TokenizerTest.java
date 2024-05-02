package com.example.recycleme;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.recycleme.search.Token;
import com.example.recycleme.search.Tokenizer;

public class TokenizerTest {
    @Test
    public void testTokenizerWithEmptyString() {
        Tokenizer tokenizer = new Tokenizer("");
        assertNull(tokenizer.getCurrentToken());
    }

    @Test
    public void testTokenizerWithWhitespaceString() {
        Tokenizer tokenizer = new Tokenizer("    ");
        assertNull(tokenizer.getCurrentToken());
    }

    @Test(expected = Token.IllegalTokenException.class)
    public void testTokenizerInvalid1() {
        Tokenizer tokenizer = new Tokenizer("*headphones and #Sony");
    }

    @Test
    public void testTokenizerInvalid2() {
        Tokenizer tokenizer = new Tokenizer("speakers and* #Bose");
        assertEquals(new Token(Token.TokenType.ITEM, "speakers"), tokenizer.getCurrentToken());
        tokenizer.extractNextToken();
        assertEquals(new Token(Token.TokenType.AND, "and"), tokenizer.getCurrentToken());
        assertThrows(Token.IllegalTokenException.class, tokenizer::extractNextToken);
    }
    @Test
    public void testTokenizerValid1() {
        Tokenizer tokenizer = new Tokenizer("tv and #Samsung");
        assertEquals(new Token(Token.TokenType.ITEM, "tv"), tokenizer.getCurrentToken());
        tokenizer.extractNextToken();
        assertEquals(new Token(Token.TokenType.AND, "and"), tokenizer.getCurrentToken());
        tokenizer.extractNextToken();
        assertEquals(new Token(Token.TokenType.BRAND, "Samsung"), tokenizer.getCurrentToken());
    }

    @Test
    public void testTokenizerValid2() {
        Tokenizer tokenizer = new Tokenizer("tv and #Sony or #Samsung");
        assertEquals(new Token(Token.TokenType.ITEM, "tv"), tokenizer.getCurrentToken());
        tokenizer.extractNextToken();
        assertEquals(new Token(Token.TokenType.AND, "and"), tokenizer.getCurrentToken());
        tokenizer.extractNextToken();
        assertEquals(new Token(Token.TokenType.BRAND, "Sony"), tokenizer.getCurrentToken());
        tokenizer.extractNextToken();
        assertEquals(new Token(Token.TokenType.OR, "or"), tokenizer.getCurrentToken());
        tokenizer.extractNextToken();
        assertEquals(new Token(Token.TokenType.BRAND, "Samsung"), tokenizer.getCurrentToken());
    }
}
