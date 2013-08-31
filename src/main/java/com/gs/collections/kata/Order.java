/*
 * Copyright 2011 Goldman Sachs.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gs.collections.kata;

import com.gs.collections.api.bag.MutableBag;
import com.gs.collections.api.block.function.Function;
import com.gs.collections.api.block.predicate.Predicate;
import com.gs.collections.api.block.procedure.Procedure;
import com.gs.collections.api.list.MutableList;
import com.gs.collections.impl.bag.mutable.HashBag;
import com.gs.collections.impl.block.function.AddFunction;

import java.util.List;

/**
 * Has a number, a {@link Customer}, a {@link List} of {@link LineItem}s, and a boolean that states whether or not the order
 * has been delivered.  There is a class variable that contains the next order number.
 */
public class Order {
    public static final Function<Order, Double> TO_VALUE = (Function<Order, Double>) Order::getValue;

    public static final Predicate<Order> IS_DELIVERED = order -> order.isDelivered;

    public static final Function<Order, Iterable<LineItem>> TO_LINE_ITEMS = order -> order.lineItems;
    public static final Procedure<Order> DELIVER = (Procedure<Order>) Order::deliver;

    private static int nextOrderNumber = 1;

    private final int orderNumber;
    private final MutableBag<LineItem> lineItems = HashBag.newBag();
    private boolean isDelivered;

    public Order() {
        this.orderNumber = nextOrderNumber;
        nextOrderNumber += 1;
    }

    public static void resetNextOrderNumber() {
        nextOrderNumber = 1;
    }

    public void deliver() {
        this.isDelivered = true;
    }

    public boolean isDelivered() {
        return this.isDelivered;
    }

    public void addLineItem(LineItem aLineItem) {
        this.lineItems.add(aLineItem);
    }

    public MutableList<LineItem> getLineItems() {
        return this.lineItems.toList();
    }

    @Override
    public String toString() {
        return "order " + this.orderNumber + " items: " + this.lineItems.size();
    }

    public double getValue() {
        return this.lineItems.collect(LineItem::getValue).injectInto(0.0, AddFunction.DOUBLE_TO_DOUBLE);
    }

    public void addLineItems(LineItem cup, int amount) {
        this.lineItems.addOccurrences(cup, amount);
    }
}
