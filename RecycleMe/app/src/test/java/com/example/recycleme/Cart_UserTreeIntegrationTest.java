package com.example.recycleme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.recycleme.cart.Cart;
import com.example.recycleme.cart.UserTree;
import com.example.recycleme.model.RecycledItem;
import com.example.recycleme.model.User;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Integration test to see how Cart interacts with UserTree
 * @author Julius Liem
 */
public class Cart_UserTreeIntegrationTest {
    private Cart cart;
    private UserTree userTree;

    @Before
    public void setUp() {
        this.cart = Cart.getInstance();
        cart.clear();

        this.userTree = UserTree.getInstance();
        userTree.clear();

    }

    @Test
    public void testAddItemsToCartAndUserTreeEmpty() {
        LocalDateTime currentTime = LocalDateTime.now();

        List<RecycledItem> currentItem = cart.getItems();
        userTree.addItems(currentTime, currentItem);

        List<RecycledItem> userTreeItem = userTree.searchItem(currentTime);
        assertTrue(userTreeItem.isEmpty());
    }

    @Test
    public void testAddItemsToCartAndUserTreeDuplicate() {
        RecycledItem recycledItem = new RecycledItem(1, "Bottle", "Coca", "Plastic", 10.0);
        RecycledItem recycledItem2 = new RecycledItem(1, "Bottle", "Coca", "Plastic", 10.0);

        LocalDateTime currentTime = LocalDateTime.now();
        cart.addItem(recycledItem);
        cart.addItem(recycledItem2);

        List<RecycledItem> cartItem = cart.getItems();
        userTree.addItems(currentTime, cartItem);

        // verify that duplicate items are added to usertree
        List<RecycledItem> userTreeItems = userTree.searchItem(currentTime);
        assertEquals(cartItem.size(), userTreeItems.size());
    }

    @Test
    public void testRemoveItemsFromCartAndUserTreeNonExistentItem() {
        RecycledItem recycledItem = new RecycledItem(1, "Bottle", "Coca", "Plastic", 10.0);

        cart.addItem(recycledItem);
        LocalDateTime time = LocalDateTime.now();

        userTree.addItems(time, cart.getItems());

        // remove the item from the cart
        cart.removeItem(recycledItem);

        // verify that the item is still in the userTree
        List<RecycledItem> treeList = userTree.searchItem(time);

        assertEquals(treeList.get(0), recycledItem);
    }

}
