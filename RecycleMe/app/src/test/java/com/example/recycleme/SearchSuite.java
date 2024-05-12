package com.example.recycleme;

import com.example.recycleme.SearchQueryParserTest;
import com.example.recycleme.TokenizerTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author Adam Basheer
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TokenizerTest.class,
        SearchQueryParserTest.class
})
public class SearchSuite {
}
