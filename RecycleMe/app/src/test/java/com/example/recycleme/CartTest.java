package com.example.recycleme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.recycleme.cart.Cart;
import com.example.recycleme.model.RecycledItem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Adam Basheer
 */
@RunWith(Parameterized.class)
public class CartTest {
    private Cart cart;

    private RecycledItem itemToAdd;
    private RecycledItem itemToRemove;

    public CartTest(RecycledItem itemToAdd, RecycledItem itemToRemove) {
        this.itemToAdd = itemToAdd;
        this.itemToRemove = itemToRemove;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {new RecycledItem(1, "Item 1", "Brand 1", "Material 1", 10.0),
             new RecycledItem(2, "Item 2", "Brand 2", "Material 2", 20.0)},
            {new RecycledItem(3, "Item 3", "Brand 3", "Material 3", 30.0),
             new RecycledItem(4, "Item 4", "Brand 4", "Material 4", 40.0)}
        });
    }

    @Before
    public void setUp() {
        cart = Cart.getInstance();
        cart.clear();
    }

    @Test
    public void testSingleton() {
        Cart cart1 = Cart.getInstance();
        Cart cart2 = Cart.getInstance();
        assertEquals(cart1, cart2);
    }

    @Test
    public void testAddItem() {
        cart.addItem(itemToAdd);
        assertEquals(1, cart.getItems().size());
        assertTrue(cart.getItems().contains(itemToAdd));
    }

    @Test
    public void testRemoveItem() {
        cart.addItem(itemToAdd);
        cart.addItem(itemToRemove);

        cart.removeItem(itemToRemove);

        assertEquals(1, cart.getItems().size());
        assertFalse(cart.getItems().contains(itemToRemove));
        assertTrue(cart.getItems().contains(itemToAdd));
    }
}
