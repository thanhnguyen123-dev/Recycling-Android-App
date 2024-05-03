package com.example.recycleme;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.recycleme.dao.RecycledItemDAOJsonImp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class RecycledItemDAOJsonImpTest {

    private RecycledItemDAOJsonImp dao;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        dao = new RecycledItemDAOJsonImp("test.json", context);
    }

    @Test
    public void testAddRecycledItem() {
        RecycledItem item = new RecycledItem(1, "Plastic Bottle", "ABC", "Plastic", 10.0);
        dao.addRecycledItem(item);

        List<RecycledItem> items = dao.getAllRecycledItems();
        assertNotNull(items);
        assertEquals(1, items.size());
        assertEquals(item, items.get(0));
    }

    @Test
    public void testUpdateRecycledItem() {
        RecycledItem item = new RecycledItem(1, "Plastic Bottle", "ABC", "Plastic", 10.0);
        dao.addRecycledItem(item);

        RecycledItem updatedItem = new RecycledItem(1, "Paper Bottle", "ADC",  "Paper", 20.0);
        dao.updateRecycledItem(updatedItem);

        List<RecycledItem> items = dao.getAllRecycledItems();
        assertNotNull(items);
        assertEquals(1, items.size());
        assertEquals(updatedItem, items.get(0));
    }

    @Test
    public void testDeleteRecycledItem() {
        RecycledItem item1 = new RecycledItem(1, "1", "Plastic Bottle", "ABC Company", 10.0);
        RecycledItem item2 = new RecycledItem(2, "2", "Paper Bottle", "ABCD Company",  50.0);
        dao.addRecycledItem(item1);
        dao.addRecycledItem(item2);

        dao.deleteRecycledItem(item1.getId());

        List<RecycledItem> items = dao.getAllRecycledItems();
        assertNotNull(items);
        assertEquals(1, items.size());
        assertEquals(item2, items.get(0));
    }
}
