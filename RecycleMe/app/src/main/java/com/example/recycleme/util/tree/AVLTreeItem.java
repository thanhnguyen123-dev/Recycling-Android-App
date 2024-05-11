package com.example.recycleme.util.tree;

import com.example.recycleme.model.RecycledItem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Extension of the AVLTree class that specifically handles RecycledItem
 * Provides the ability to search for items based on their item, brand, and material
 *
 * @author Julius Liem
 */
public class AVLTreeItem extends AVLTree<RecycledItem> {

    // purpose of the END is to create an upper bound for the search range
    // e.g. when we search for "ap", we want the result to be everything that begins with "ap"
    // but not something that begins with "aq"
    // so when we determine the ceiling to be "ap" + Char.MAX_VALUE, it will return
    // everything that is prefixed with "ap"
    private String END = String.valueOf(Character.MAX_VALUE);

    /**
     * Finds recycled items in the AVL tree based on the provided item name, brand, and material.
     * The search is performed using the ceiling and floor operations of the AVL tree.
     * If all three parameters are provided, the search is optimized to minimize the time complexity.
     * If any of the parameters are not provided, the method manually traverses the tree to find matching items.
     *
     * @param item     the item name to search for (case-insensitive)
     * @param brand    the brand name to search for (case-insensitive)
     * @param material the material to search for (case-insensitive)
     * @return a list of recycled items that match the provided criteria
     */
    public List<RecycledItem> findItems(String item, String brand, String material) {

        if (this.size() == 0) {
            return new ArrayList<>();
        }
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
