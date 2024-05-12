package com.example.recycleme;


import static org.junit.Assert.assertEquals;

import com.example.recycleme.model.RecycledItem;
import com.example.recycleme.search.SearchExp;
import com.example.recycleme.search.SearchQueryParser;
import com.example.recycleme.search.Tokenizer;
import com.example.recycleme.util.tree.AVLTreeItem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Integration test class which tests the integration between AVLTreeItem and SearchQueryParser
 * @author Julius Liem
 */
@RunWith(Parameterized.class)
public class AVLTreeItemSearchIntegrationTest {
    private AVLTreeItem avlTreeItem;
    private SearchQueryParser searchQueryParser;
    private String query;
    private int expectedSize;
    private String expectedItem;
    private String expectedBrand;
    private String expectedMaterial;


    public AVLTreeItemSearchIntegrationTest(String query, int expectedSize, String expectedItem, String expectedBrand, String expectedMaterial) {
        this.query = query;
        this.expectedSize = expectedSize;
        this.expectedItem = expectedItem;
        this.expectedBrand = expectedBrand;
        this.expectedMaterial = expectedMaterial;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "dehumidifier and #Philips and @Plastic", 1, "Dehumidifier", "Philips", "Plastic" },
                { "Speaker and #Sennheiser and @Aluminum", 1, "Speaker", "Sennheiser", "Aluminum" },
                { "Green Bean and #Sennheiser and @Organic", 1, "Green Bean", "Sennheiser", "Organic" },
                { "Dehumidifier and @Plastic", 2, "Dehumidifier", "Philips", "Plastic" },
                { "#sennheiser", 2, "Green Bean", "Sennheiser", "Organic"},
                { "error and #error and @error", 0, null, null, null }
        });
    }

    @Before
    public void setUp() {
        avlTreeItem = new AVLTreeItem();

        avlTreeItem.insert(new RecycledItem(1, "Dehumidifier", "Philips", "Plastic", 100.0));
        avlTreeItem.insert(new RecycledItem(2, "Speaker", "Sennheiser", "Aluminum", 50.0));
        avlTreeItem.insert(new RecycledItem(3, "Green Bean", "Sennheiser", "Organic", 50.0));
        avlTreeItem.insert(new RecycledItem(4, "dehumidifier", "Shilips", "Plastic", 100.0));
    }

    @Test
    public void testSearch() throws SearchQueryParser.InvalidQueryException {
        searchQueryParser = new SearchQueryParser(new Tokenizer(this.query));

        SearchExp searchExp = searchQueryParser.parseSearchQuery();
        List<RecycledItem> result = searchExp.evaluateSearchExp(this.avlTreeItem);

        assertEquals(expectedSize, result.size());

        if (!result.isEmpty()) {
            RecycledItem firstItem = result.get(0);
            assertEquals(expectedItem, firstItem.getItem());
            assertEquals(expectedBrand, firstItem.getBrandName());
            assertEquals(expectedMaterial, firstItem.getMaterial());
        }
    }
}
