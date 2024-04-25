package com.example.recycleme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.example.recycleme.cart.Cart;
import com.example.recycleme.interfaces.Iterator;

import org.junit.Before;
import org.junit.Test;

public class CartTest {
    private Cart cart;

    @Before
    public void setUp() {
        cart = Cart.getInstance();
        cart.clear();
    }

    @Test
    public void testSingleton() {
        Cart cart1 = Cart.getInstance();
        Cart cart2 = Cart.getInstance();
        assertSame(cart1, cart2);
    }

    @Test
    public void testAddItem() {
        RecycledItem item1 = new RecycledItem("Item 1", "Brand 1", "Material 1", 10.0);
        RecycledItem item2 = new RecycledItem("Item 2", "Brand 2", "Material 2", 20.0);

        cart.addItem(item1);
        cart.addItem(item2);

        assertEquals(2, cart.getItems().size());
        assertTrue(cart.getItems().contains(item1));
        assertTrue(cart.getItems().contains(item2));
    }

    @Test
    public void testRemoveItem() {
        RecycledItem item1 = new RecycledItem("Item 1", "Brand 1", "Material 1", 10.0);
        RecycledItem item2 = new RecycledItem("Item 2", "Brand 2", "Material 2", 20.0);

        cart.addItem(item1);
        cart.addItem(item2);

        cart.removeItem(item1);

        assertEquals(1, cart.getItems().size());
        assertFalse(cart.getItems().contains(item1));
        assertTrue(cart.getItems().contains(item2));
    }

    @Test
    public void testClear() {
        RecycledItem item1 = new RecycledItem("Item 1", "Brand 1", "Material 1", 10.0);
        RecycledItem item2 = new RecycledItem("Item 2", "Brand 2", "Material 2", 20.0);

        cart.addItem(item1);
        cart.addItem(item2);

        cart.clear();

        assertEquals(0, cart.getItems().size());
    }

    @Test
    public void testIterator() {
        RecycledItem item1 = new RecycledItem("Item 1", "Brand 1", "Material 1", 10.0);
        RecycledItem item2 = new RecycledItem("Item 2", "Brand 2", "Material 2", 20.0);
        RecycledItem item3 = new RecycledItem("Item 3", "Brand 3", "Material 3", 30.0);

        cart.addItem(item1);
        cart.addItem(item2);
        cart.addItem(item3);

        Iterator iterator = cart.createIterator();

        assertTrue(iterator.hasNext());
        assertEquals(item1, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(item2, iterator.next());

        assertTrue(iterator.hasNext());
        assertEquals(item3, iterator.next());
    }

    @Test
    public void testIteratorEmpty() {
        Iterator iterator = cart.createIterator();

        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }
}
