package by.epam.inner.runner;

import by.epam.inner.checker.EntryChecker;
import by.epam.inner.date.WrapperDate;
import by.epam.inner.factory.PurchasesFactory;
import by.epam.inner.myexceptions.*;
import by.epam.inner.productsandpurchases.*;
import org.apache.logging.log4j.*;
import by.epam.inner.date.DaysOfWeek;

import java.io.*;
import java.util.*;


public class Runner {

    public static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File(args[0]))) {
            Map<Purchase, WrapperDate> firstMap = new HashMap<>();
            Map<Purchase, WrapperDate> secondMap = new HashMap<>();
            Map<DaysOfWeek, List<Purchase>> enumMap = new EnumMap<>(DaysOfWeek.class);
            List<PriceDiscountPurchase> discountPurchases = new ArrayList<>();
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String content = line.split(";", 2)[1];
                try {
                    WrapperDate date = new WrapperDate(line.split(";", 2)[0]);
                    DaysOfWeek dayOfWeek = date.dayOfWeek();
                    Purchase purchase = PurchasesFactory.getPurchaseFromFactory(content);
                    List<Purchase> l = enumMap.get(dayOfWeek);
                    if (l == null) {
                        enumMap.put(dayOfWeek, l = new ArrayList<>());
                    }
                    l.add(purchase);             //3

                    firstMap.put(purchase, date);          //1
                    if (!secondMap.containsKey(purchase)) {
                        secondMap.put(purchase, date);     //2
                    }
                    if (purchase instanceof PriceDiscountPurchase) {
                        discountPurchases.add((PriceDiscountPurchase) purchase);            //4
                    }
                } catch (CsvLineException e) {
                    Runner.logger.error(e);
                    System.err.println("You have a problem with this element:");
                    System.err.println(e);
                }
            }
            printMap(firstMap, "Printing first map");       //5
            printMap(secondMap, "Printing second map");     //5
            printMap(enumMap, "Printing enum map");         //5
            printValue(secondMap
                    , new Purchase(new Product("bread", 1, 55), 1));     //6
            printValue(firstMap
                    , new Purchase(new Product("bread", 1, 70), 1));     //7
            printValue(enumMap, DaysOfWeek.Saturday);      //8

            if (enumMap.entrySet().isEmpty()) {
                System.out.println("enumMap is empty");
            } else {
                for (Map.Entry<DaysOfWeek, List<Purchase>> entry : enumMap.entrySet()) {
                    System.out.println(String.format("Total cost of all purchases in %s => %s Byn"
                            , entry.getKey(), getTotalCost(entry.getValue())));    //9
                }
            }
            System.out.println(String.format("Total cost of all discount purchases => %s Byn"
                    , getTotalCost(discountPurchases)));    //10

            removeEntries(firstMap, new EntryChecker<Purchase, WrapperDate>() {   //11
                @Override
                public boolean check(Map.Entry<Purchase, WrapperDate> entry) {
                    return entry.getKey().getProduct().getName().equals("meat");
                }
            });

            removeEntries(secondMap, new EntryChecker<Purchase, WrapperDate>() {   //12
                @Override
                public boolean check(Map.Entry<Purchase, WrapperDate> entry) {
                    return entry.getValue().dayOfWeek().equals(DaysOfWeek.Friday);
                }
            });
            removeEntries(secondMap, new EntryChecker<Purchase, WrapperDate>() {   //13
                @Override
                public boolean check(Map.Entry<Purchase, WrapperDate> entry) {
                    return entry.getValue().getYear() == 2018
                            && entry.getKey().getCost().compareTo(new Byn("10.00")) < 0;
                }
            });
            removeEntries(enumMap, new EntryChecker<DaysOfWeek, List<Purchase>>() {      //14
                @Override
                public boolean check(Map.Entry<DaysOfWeek, List<Purchase>> entry) {
                    for (Purchase purchase : entry.getValue()) {
                        if (purchase.getProduct().getName().equals("milk")) {
                            return true;
                        }
                    }
                    return false;
                }

            });

            printMap(firstMap, "Printing first map");       //15
            printMap(secondMap, "Printing second map");     //15
            printMap(enumMap, "Printing enum map");         //15
        } catch (FileNotFoundException e) {
            logger.error("File not found");
            e.printStackTrace();
        }
    }

    private static <K, V> void printMap(Map<K, V> map, String message) {
        System.out.println(message + "\n");
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(String.format("%s => %s", entry.getKey(), entry.getValue()));
        }
        System.out.println("---> end");
    }

    private static <K, V> void printValue(Map<K, V> map, K argument) {
        V value = map.get(argument);
        System.out.println((!(value == null)) ? value.toString() : "Data is absent");
    }

    private static Byn getTotalCost(List<? extends Purchase> purchases) {
        Byn result = new Byn(0);
        for (Purchase purchase : purchases) {
            result = result.addition(purchase.getCost());
        }
        return result;
    }

    private static <K, V> void removeEntries(Map<K, V> map, EntryChecker<K, V> checker) {
        for (Iterator<Map.Entry<K, V>> it = map.entrySet().iterator(); it.hasNext(); )
            if (checker.check(it.next())) {
                it.remove();
            }
    }

}

