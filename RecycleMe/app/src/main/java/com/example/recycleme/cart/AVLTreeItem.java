package com.example.recycleme.cart;

import com.example.recycleme.RecycledItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AVLTreeItem extends AVLTree<RecycledItem> {

    // purpose of the END is to create an upper bound for the search range
    // e.g. when we search for "ap", we want the result to be everything that begins with "ap"
    // but not something that begins with "aq"
    // so when we determine the ceiling to be "ap" + Char.MAX_VALUE, it will return
    // everything that is prefixed with "ap"
    private String END = String.valueOf(Character.MAX_VALUE);
    public List<RecycledItem> findItems(String item, String brand, String material) {
        RecycledItem recycledItem = new RecycledItem(0, item, brand, material, 0.0);

        RecycledItem floor = ceiling(recycledItem);
        RecycledItem ceiling = floor(new RecycledItem(0, item + END, brand + END, material + END, 0.0));

        List<RecycledItem> returnedList = findBetween(floor, ceiling);

        // this approach uses the character of AVL Tree to minimize the time operation
        // however, we can only achieve O(log n) if item is provided
        // if item is not provided, then we will have to manually traverse the tree


        // Cleaning up. If all three are provided, then the number of returned
        // item is low. However, if one field is not provided, then the number
        // of returned item = size of tree

        Iterator<RecycledItem> iterator = returnedList.iterator();
        while (iterator.hasNext()) {
            RecycledItem currentRecycledItem = iterator.next();
            String currentRecycledItemName = currentRecycledItem.getItem().toLowerCase();
            String currentRecycledBrand = currentRecycledItem.getBrandName().toLowerCase();
            String currentRecycledMaterial = currentRecycledItem.getMaterial().toLowerCase();

            if (!currentRecycledItemName.contains(item.toLowerCase()) || !currentRecycledBrand.contains(brand.toLowerCase()) ||
                    !currentRecycledMaterial.contains(material.toLowerCase())) {
                iterator.remove();
            }
        }

        return returnedList;
    }
}
