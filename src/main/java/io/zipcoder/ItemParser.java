package io.zipcoder;

import java.util.ArrayList;
import java.util.Arrays;

//each item has its own index
public class ItemParser {
    private int count = 0;

    public ArrayList<String> parseRawDataIntoStringArray(String rawData){
        String stringPattern = "##";
        return splitStringWithRegexPattern(stringPattern , rawData);
    }

    public Item parseStringIntoItem(String rawItem) throws ItemParseException {
        ArrayList<String> allItems = findKeyValuePairsInRawItemData(rawItem);

        String itemName = allItems.get(0);
        String nameRx = "[n][a][m][e]";
        itemName = itemName.replaceAll(nameRx,"");
        itemName = formatString(itemName);

        String price = allItems.get(1);
        String priceRx = "[p][r][i][c][e]";
        price = price.replaceAll(priceRx, "");
        double actualPrice = formatPrice(price);

        String type = allItems.get(2);
        String typeRx = "[t][y][p][e]";
        type = type.replaceAll(typeRx, "");
        type = formatString(type);

        String expiration = allItems.get(3);
        String expirationRx = "[e][x][p][i][r][a][t][i][o][n]";
        expiration = expiration.replaceAll(expirationRx, "");
        expiration = formatString(expiration);

        return new Item(itemName,actualPrice,type,expiration);
    }

    private double formatPrice(String price){
        price = price.replaceAll(":", "");
        if (price.equals("")){
            return 0.0;
        }else{
            return Double.parseDouble(price);
        }
    }

    public String formatString(String item){
        if (item.equals("")){
            count++;
            return "This segment is ok";
        } if (item.startsWith("C|c")){
            item.replaceAll("0","o");
        }
        return item.substring(0,1).toUpperCase() + item.substring(1);
    }

    public ArrayList<String> findKeyValuePairsInRawItemData(String rawItem){
        String stringPattern = "[`|!|@|$|%|&|*|;|^]";
        return splitStringWithRegexPattern(stringPattern , rawItem);
    }

    private ArrayList<String> splitStringWithRegexPattern(String stringPattern, String inputString){
        return new ArrayList<String>(Arrays.asList(inputString.split(stringPattern)));
    }
}