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

import com.gs.collections.api.list.MutableList;
import org.junit.Before;

public abstract class CompanyDomainForKata
{
    public static final String CITY_LONDON = "London";
    public static final String CITY_LIPHOOK = "Liphook";

    public static final String CUSTOMER_FRED = "Fred";
    public static final String CUSTOMER_MARY = "Mary";
    public static final String CUSTOMER_BILL = "Bill";
    public static final String ITEM_SANDWICH_TOASTER = "sandwich toaster";
    protected final Company company = new Company("Bloggs Shed Supplies");

    @Before
    public void setUp()
    {
        Order.resetNextOrderNumber();
        this.setUpCustomersAndOrders();
        this.setUpSuppliers();
    }

    private void setUpSuppliers()
    {
        this.company.addSupplier(new Supplier("Shedtastic", new String[]{"shed", "big shed", "huge shed"}));
        this.company.addSupplier(new Supplier("Splendid Crocks", new String[]{"cup", "saucer", "bowl"}));
        this.company.addSupplier(new Supplier("Annoying Pets", new String[]{"dog", "cat", "goldfish"}));
        this.company.addSupplier(new Supplier("Gnomes 'R' Us", new String[]{"gnome"}));
        this.company.addSupplier(new Supplier("Furniture Hamlet", new String[]{"table", "sofa", "chair"}));
        this.company.addSupplier(new Supplier("SFD", new String[]{"sofa", "chair"}));
        this.company.addSupplier(new Supplier("Doxins", new String[]{"kettle", "plasma screen", ITEM_SANDWICH_TOASTER}));
    }

    private void setUpCustomersAndOrders()
    {
        Order fredOrder = new Order();
        fredOrder.addLineItem(new LineItem("Shed", 50.0));

        /**
         * TODO 8: Refactor Order and its API so this repetition is not necessary.
         */
        // TODO 8: Add 3 cups at 1.5 each to the order
        fredOrder.addLineItem(new LineItem("cup", 1.5));
        fredOrder.addLineItem(new LineItem("cup", 1.5));
        fredOrder.addLineItem(new LineItem("cup", 1.5));

        // TODO 8: Add 3 saucers at 1.0 each to the order
        fredOrder.addLineItem(new LineItem("saucer", 1.0));
        fredOrder.addLineItem(new LineItem("saucer", 1.0));
        fredOrder.addLineItem(new LineItem("saucer", 1.0));

        fredOrder.addLineItem(new LineItem("chair", 12.50));
        fredOrder.addLineItem(new LineItem("table", 1.0));

        Customer fred = new Customer(CUSTOMER_FRED, CITY_LONDON);
        fred.addOrder(fredOrder);
        this.company.addCustomer(fred);

        Order maryOrder = new Order();
        maryOrder.addLineItem(new LineItem("cat", 150.0));
        maryOrder.addLineItem(new LineItem("big shed", 500.0));

        // TODO 8: Add 4 cups at 1.50 each to the order
        maryOrder.addLineItem(new LineItem("cup", 1.5));
        maryOrder.addLineItem(new LineItem("cup", 1.5));
        maryOrder.addLineItem(new LineItem("cup", 1.5));
        maryOrder.addLineItem(new LineItem("cup", 1.5));

        // TODO 8: Add 4 saucers at 1.50 each to the order
        maryOrder.addLineItem(new LineItem("saucer", 1.5));
        maryOrder.addLineItem(new LineItem("saucer", 1.5));
        maryOrder.addLineItem(new LineItem("saucer", 1.5));
        maryOrder.addLineItem(new LineItem("saucer", 1.5));

        maryOrder.addLineItem(new LineItem("sofa", 120.0));
        maryOrder.addLineItem(new LineItem("dog", 75.0));

        Customer mary = new Customer(CUSTOMER_MARY, CITY_LIPHOOK);
        mary.addOrder(maryOrder);
        this.company.addCustomer(mary);

        Order billOrder1 = new Order();
        billOrder1.addLineItem(new LineItem("shed", 50.0));

        // TODO 8: Add 43 gnomes at 7.50 each to the order
        for (int i = 0; i < 43; i++)
        {
            billOrder1.addLineItem(new LineItem("gnome", 7.50));
        }

        Order billOrder2 = new Order();
        billOrder2.addLineItem(new LineItem("bowl", 1.25));
        billOrder2.addLineItem(new LineItem("goldfish", 0.50));

        Order billOrder3 = new Order();
        billOrder3.addLineItem(new LineItem("table", 1.0));

        Customer bill = new Customer(CUSTOMER_BILL, CITY_LONDON);
        bill.addOrder(billOrder1);
        bill.addOrder(billOrder2);
        bill.addOrder(billOrder3);

        this.company.addCustomer(bill);
    }

    protected MutableList<Customer> getCompanyCustomers() {
        return this.company.getCustomers();
    }

    protected MutableList<Order> getCompanyOrders() {
        return this.company.getOrders();
    }

    protected Supplier[] getCompanySuppliers() {
        return this.company.getSuppliers();
    }
}
