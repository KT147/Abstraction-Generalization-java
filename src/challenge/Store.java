package challenge;

import java.util.ArrayList;

public class Store {
    private static ArrayList<ProductForSale> storeProducts = new ArrayList<>();

    public static void main(String[] args) {
        storeProducts.add(new ArtObject("Oil Painting", 1350, "Impressive"));
        storeProducts.add(new ArtObject("Sculpture", 2000, "Bronze work"));

        storeProducts.add(new Furniture("Desk", 500, "blabla"));

        listProducts();
        System.out.println("Order 1.");
        var order1 = new ArrayList<OrderItem>();
        addItemToOrder(order1, 1, 2);
        addItemToOrder(order1, 0, 1);
        printOrder(order1);

        System.out.println("Order 2.");
        var order2 = new ArrayList<OrderItem>();
        addItemToOrder(order2, 2, 5);
        addItemToOrder(order2, 0, 1);
        printOrder(order2);

    }

    public static void listProducts() {

        for(var item: storeProducts) {
            System.out.println("-".repeat(30));
            item.showDetails();
        }
    }

    public static void addItemToOrder (ArrayList<OrderItem> order, int orderIndex, int quantity) {
        order.add(new OrderItem(quantity, storeProducts.get(orderIndex)));
    }

    public static void printOrder(ArrayList<OrderItem> order) {

        double salesTotal = 0;

        for (var item: order) {
            item.product().printPricedItem(item.quantity());
            salesTotal += item.product().getSalesPrice(item.quantity());
        }

        System.out.println(salesTotal);
    }
}

abstract class ProductForSale {
    protected String type;
    protected double price;
    protected String description;

    public ProductForSale(String type, double price, String description) {
        this.type = type;
        this.price = price;
        this.description = description;
    }

    public double getSalesPrice(int quantity) {
        return quantity * price;
    }

    public void printPricedItem(int quantity) {
        System.out.println(price + " "  + type + " " + description);
    }

    public abstract void showDetails();
}

record OrderItem(int quantity, ProductForSale product){}

class ArtObject extends ProductForSale {

    public ArtObject(String type, double price, String description) {
        super(type, price, description);
    }

    @Override
    public void showDetails() {
        System.out.println(type);
        System.out.println(price);
        System.out.println(description);
    }

}

class Furniture extends ProductForSale {

    public Furniture(String type, double price, String description) {
        super(type, price, description);
    }

    @Override
    public void showDetails() {
        System.out.println(type);
        System.out.println(price);
        System.out.println(description);
    }

}
