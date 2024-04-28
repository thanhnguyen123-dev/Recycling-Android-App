package com.example.recycleme.dao;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import com.example.recycleme.RecycledItem;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RecycledItemDAOJsonImpTest {

    private RecycledItemDAOJsonImp dao;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        dao = new RecycledItemDAOJsonImp("test.json", context);
    }

    @Test
    public void testAddRecycledItem() {
        RecycledItem item = new RecycledItem(1, "Plastic Bottle", "ABC Company", "Plastic", 10);
        dao.addRecycledItem(item);

        List<RecycledItem> items = dao.getAllRecycledItems();
        assertNotNull(items);
        assertEquals(1, items.size());
        assertEquals(item, items.get(0));
    }

    @Test
    public void testUpdateRecycledItem() {
        RecycledItem item = new RecycledItem(1, "Plastic Bottle", "ABC Company", "Plastic", 10);
        dao.addRecycledItem(item);

        RecycledItem updatedItem = new RecycledItem(1, "Paper Bottle", "ADC Company", "Paper", 20);
        dao.updateRecycledItem(updatedItem);

        List<RecycledItem> items = dao.getAllRecycledItems();
        assertNotNull(items);
        assertEquals(1, items.size());
        assertEquals(updatedItem, items.get(0));
    }

    @Test
    public void testDeleteRecycledItem() {
        RecycledItem item1 = new RecycledItem(1, "Plastic Bottle", "ABC Company", "Plastic", 10);
        RecycledItem item2 = new RecycledItem(2, "Paper Bottle", "ABCD Company", "Paper", 50);
        dao.addRecycledItem(item1);
        dao.addRecycledItem(item2);

        dao.deleteRecycledItem(item1.getId());

        List<RecycledItem> items = dao.getAllRecycledItems();
        assertNotNull(items);
        assertEquals(1, items.size());
        assertEquals(item2, items.get(0));
    }
}
