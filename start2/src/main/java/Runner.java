import by.epam.inner.date.WrapperDate;
import by.epam.inner.productsandpurchases.*;
import org.apache.logging.log4j.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;


public class Runner {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {
        Map<Purchase, WrapperDate> firstMap = new HashMap<>();
        Map<Purchase, WrapperDate> secondMap = new HashMap<>();
        Map<DayOfWeek, List<Purchase>> enumMap = new EnumMap<>(DayOfWeek.class);
        List<PriceDiscountPurchase> discountPurchases = new ArrayList<>();
        try (Stream<String> csvLines = Files.lines(Paths.get(args[0]))) {
            csvLines
                    .map(WrapperEntry::getWrapperEntryFromCsv)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .forEach(entry -> {
                                Purchase purchase = entry.getPurchase();
                                WrapperDate date = entry.getDate();
                                firstMap.put(purchase, date);                             //1
                                secondMap.putIfAbsent(purchase, date);                    //2
                                enumMap.computeIfAbsent(date.getDayOfWeek(), k -> new ArrayList<>())
                                        .add(purchase);                                   //3
                                if (purchase instanceof PriceDiscountPurchase) {          //4
                                    discountPurchases.add((PriceDiscountPurchase) purchase);
                                }

                            }
                    );
            printMap(firstMap, "Printing first map");                             //5
            printMap(secondMap, "Printing second map");                           //5
            printMap(enumMap, "Printing enum map");                               //5
            printValue(secondMap                                                          //6
                    , new Purchase(new Product("bread", 1, 55), 1));
            printValue(firstMap                                                           //7
                    , new Purchase(new Product("bread", 1, 70), 1));
            printValue(enumMap, DayOfWeek.SATURDAY);                                     //8
            if (enumMap.entrySet().isEmpty()) {
                System.out.println("enumMap is empty");
            } else {
                enumMap.forEach((key, value) -> System.out.println(
                        String.format("Total cost of all purchases in %s => %s Byn",
                                key, getTotalCost(value))));                              //9
            }


            System.out.println(String.format("Total cost of all discount purchases => %s Byn"
                    , getTotalCost(discountPurchases)));                                  //10
            removeEntries(firstMap, (entry) -> entry.getKey().getProduct().getName().equals("meat"));  //11

            removeEntries(secondMap, (entry) -> entry.getValue().getDayOfWeek()
                    .equals(DayOfWeek.FRIDAY));                                            //12

            removeEntries(secondMap, (entry) -> entry.getValue().getYear() == 2018
                    && entry.getKey().getCost().compareTo(new Byn("10.00")) < 0);          //13

            removeEntries(enumMap, (entry) -> entry.getValue().stream()
                    .anyMatch(x -> x.getProduct()
                            .getName().equals("milk")));                                   //14

            printMap(firstMap, "Printing first map");                              //15
            printMap(secondMap, "Printing second map");                            //15
            printMap(enumMap, "Printing enum map");                                //15
        } catch (IOException e) {
            LOGGER.fatal("couldn't open the file: " + args[0]);
            e.printStackTrace();
        }
    }

    private static <K, V> void printMap(Map<K, V> map, String message) {
        System.out.println(message + "\n");
        map.forEach((key, value) -> System.out.println(String.format("%s => %s", key, value)));
        System.out.println("---> end");
    }

    private static <K, V> void printValue(Map<K, V> map, K argument) {
        V result = map.get(argument);
        System.out.println(Objects.nonNull(result) ? result : "Data is absent");
    }

    private static Byn getTotalCost(List<? extends Purchase> purchases) {
        return purchases.stream().map(Purchase::getCost).reduce(new Byn(0), Byn::addition);
    }

    private static <K, V> void removeEntries(Map<K, V> map, Predicate<Map.Entry<K, V>> checker) {
        map.entrySet().removeIf(checker);
    }
}

