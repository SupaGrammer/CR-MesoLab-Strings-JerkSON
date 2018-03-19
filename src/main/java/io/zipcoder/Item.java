package io.zipcoder;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class Item implements Comparator <Item>{
    private String name;
    private Double price;
    private String type;
    private String expiration;

    /**
     * Item should not be created unless you have all of the elements, which is why you are forcing
     * it to be set in the constructor. In ItemParser, if you do not find all the elements of a Item,
     * you should throw an Custom Exception.
     * @param name
     * @param price
     * @param type
     * @param expiration
     */
    public Item(String name, Double price, String type, String expiration){
        this.name = name;
        this.price = price;
        this.type = type;
        this.expiration = expiration;
    }

    public static Comparator<Item> priceComparator = Comparator.comparing(Item::getPrice);


    public String getName() {
        return name;
    }


    public Double getPrice() {
        return price;
    }


    public String getType() {
        return type;
    }


    public String getExpiration() {
        return expiration;
    }

    @Override
    public String toString(){
        return "name:" + name + " price:" + price + " type:" + type + " expiration:" + expiration;
    }

    @Override
    public int compare(Item o1, Item o2) {
        return 0;
    }

    @Override
    public Comparator<Item> reversed() {
        return null;
    }

    @Override
    public Comparator<Item> thenComparing(Comparator<? super Item> other) {
        return null;
    }

    @Override
    public <U> Comparator<Item> thenComparing(Function<? super Item, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return null;
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<Item> thenComparing(Function<? super Item, ? extends U> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<Item> thenComparingInt(ToIntFunction<? super Item> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<Item> thenComparingLong(ToLongFunction<? super Item> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<Item> thenComparingDouble(ToDoubleFunction<? super Item> keyExtractor) {
        return null;
    }
}
