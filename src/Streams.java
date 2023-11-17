import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.*;

import javax.sound.midi.Patch;

public class Streams {

    public static long counter;

    public static void wasCalled() {
        counter++;
    }

    public static void main(String[] args) throws Exception {

        List<Product> productList = Arrays.asList(new Product(23, "potatoes"), new Product(12, "apple"),
                new Product(24, "pumpkin"), new Product(23, "sugar"));

        List<String> collectorCollection = productList.stream().map(Product::getName).collect(Collectors.toList());

        String listToString = productList.stream().map(Product::getName).collect(Collectors.joining(", ", "[", "]"));

        double average = productList.stream().collect(Collectors.averagingInt(Product::getPrice));
        int summingInt = productList.stream().collect(Collectors.summingInt(Product::getPrice));

        IntSummaryStatistics statistics = productList.stream().collect(Collectors.summarizingInt(Product::getPrice));

        int max = statistics.getMax();

        Map<Integer, List<Product>> collectMap = productList.stream().collect(Collectors.groupingBy(Product::getPrice));

        Set<Map.Entry<Integer, List<Product>>> set = collectMap.entrySet();

        Collector<Product, ?, LinkedList<Product>> toLinked = Collector.of(LinkedList::new, LinkedList::add,
                (first, second) -> {
                    first.addAll(second);
                    return first;
                });

        LinkedList<Product> linked = productList.stream().collect(toLinked);

        linked

                .forEach(n -> System.out.println("Name:" + n.getName() + ",  " + "Price: " + n.getPrice()));

    }

}
