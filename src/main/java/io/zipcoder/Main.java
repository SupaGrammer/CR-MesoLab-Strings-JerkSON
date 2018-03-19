package io.zipcoder;

import org.apache.commons.io.IOUtils;

import java.util.*;

public class Main {

    private String readRawDataToString() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        return IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
    }

    public static List<Item> getItems() {
        String output = null;
        try {
            output = (new Main()).readRawDataToString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ItemParser parser = new ItemParser();

        ArrayList<String> dataStringArray = parser.parseRawDataIntoStringArray(output);

        ArrayList<Item> items = new ArrayList<>();
        for (String item : dataStringArray) {
            try {
                items.add(parser.parseStringIntoItem(item));
            } catch (ItemParseException e) {
                e.printStackTrace();
            }
        }
        return items;
    }

    public static void main(String[] args) throws Exception {
        List<Item> items = getItems();
        String itemsAsString = Arrays.toString(items.toArray());
        System.out.println(itemsAsString);
    }


    public static void myTest() {
        List<Item> items = getItems();
        int errorCounter = 0;
        Integer milkCounter = 0;


        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if ("N/A".equals(item.getName()) || item.getPrice() == 0.0) {
                errorCounter++;
                iterator.remove();
            }
        }

        HashMap<String, Integer> names = getNameMap(items);

        for (Map.Entry<String, Integer> name : names.entrySet()) {
            HashMap<String, HashMap<Double, Integer>> namePriceMap = getNamePriceMap(items, name.getKey());
            System.out.println("==============================");
            System.out.println("Name: " + name.getKey() + " Appears: " + name.getValue() + " times");
            System.out.println("------------------------------");
            for (Map.Entry<String, HashMap<Double, Integer>> namePrice : namePriceMap.entrySet()) {
                String key = namePrice.getKey();
                for (Map.Entry<Double, Integer> priceMap : namePrice.getValue().entrySet()) {
                    System.out.println("Price: " + priceMap.getKey() + " Appears: " + priceMap.getValue());
                }
            }
        }
        System.out.println("------------------------------");
        System.out.println("Errors: " + errorCounter);
    }


    private static HashMap<String, HashMap<Double, Integer>> getNamePriceMap(List<Item> items, String itemName) {
        HashMap<String, HashMap<Double, Integer>> namePriceMap = new HashMap<>();
        ArrayList<Item> nameList = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                nameList.add(item);
            }
        }
        namePriceMap.put(itemName, getPriceMap(nameList));
        return namePriceMap;
    }

    private static HashMap<Double, Integer> getPriceMap(List<Item> items) {
        HashMap<Double, Integer> map = new HashMap<>();
        for (Item item : items) {
            if (map.containsKey(item.getPrice())) {
                Integer newItemCount = map.get(item.getPrice());
                newItemCount++;
                map.put(item.getPrice(), newItemCount);
            } else {
                map.put(item.getPrice(), 1);
            }
        }
        return map;
    }

    private static HashMap<String, Integer> getNameMap(List<Item> items) {
        HashMap<String, Integer> map = new HashMap<>();
        for (Item item : items) {
            if (map.containsKey(item.getName())) {
                Integer newItemCount = map.get(item.getName());
                newItemCount++;
                map.put(item.getName(), newItemCount);
            } else {
                map.put(item.getName(), 1);
            }
        }
        return map;
    }
}