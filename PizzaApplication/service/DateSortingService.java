package com.pizza.shop.PizzaApplication.service;

import com.pizza.shop.PizzaApplication.model.Item;

import java.util.Comparator;

/**
 * Created by sangharatna.davane on 2/4/2019.
 */
public class DateSortingService implements Comparator<Item> {
    @Override
    public int compare(Item o1, Item o2) {
        return o1.getTime().compareTo(o2.getTime());
    }
}
