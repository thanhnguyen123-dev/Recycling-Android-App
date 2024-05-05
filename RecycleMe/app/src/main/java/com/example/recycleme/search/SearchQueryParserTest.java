package com.example.recycleme.search;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SearchQueryParserTest {
    private SearchQueryParser parser;
    @Before
    public void setup() {
        Tokenizer tokenizer = new Tokenizer("");
        parser = new SearchQueryParser(tokenizer);
    }

    @Test
    public void testParseSearchQueryValid() {
        String[] queries = {
                "itemName and #ABC and @Abc",
                "itemName and #ABC and #BCD",
                "itemName and @Abc and @Bcd and #ABCD"
        };

        String[] expectedItems = {
                "itemName",
                "itemName",
                "itemName"
        };

        List<HashSet<String>> expectedBrands = new ArrayList<HashSet<String>>() {{
            add(new HashSet<>(Arrays.asList("ABC")));
            add(new HashSet<>(Arrays.asList("ABC", "BCD")));
            add(new HashSet<>(Arrays.asList("ABCD")));
        }};

        List<HashSet<String>> expectedMaterials = new ArrayList<HashSet<String>>() {{
            add(new HashSet<>(Arrays.asList("Abc")));
            add(new HashSet<>());
            add(new HashSet<>(Arrays.asList("Abc", "Bcd")));
        }};

        for(int i = 0; i < queries.length; i++) {
            String query = queries[i];
            parser.tokenizer = new Tokenizer(query);
            try {
                SearchExp result = parser.parseSearchQuery();
                assertNotNull(result);
                assertEquals(expectedItems[i], result.getItem());
                assertEquals(expectedBrands.get(i), result.getBrand());
                assertEquals(expectedMaterials.get(i), result.getMaterial());
            } catch(Exception e) {
                fail("Exception should not have been thrown");
            }
        }
    }

    @Test
    public void testParseSearchQueryInvalid() {
        String[] queries = {
                "itemName and #BrandName and",
                "itemName and #Brand1 #Brand2",
                "itemName and #Brand @Material",
                "itemName and #Brand and and"
        };

        for(String query : queries) {
            parser.tokenizer = new Tokenizer(query);
            try {
                parser.parseSearchQuery();
                fail("InvalidQueryException should have been thrown");
            } catch(SearchQueryParser.InvalidQueryException e) {
                // Expected exception
            }
        }
    }
}
