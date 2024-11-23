package org.example;


import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    private record Order(String product, double cost) {
    }
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );
        Map<String , List<Order>> map = orders.stream()
                        .collect(Collectors.groupingBy(Order::product));
        var entrySet = map.entrySet();
        entrySet.stream()
                        .map((entry) -> {
            double value = entry.getValue().stream().map(Order::cost)
                            .reduce((x,y) -> x+y).orElse(0.0);
            return new Order(entry.getKey(), value);
        }).sorted(Comparator.comparing(Order::cost).reversed())
                .limit(3)
                .forEach( order -> System.out.println(order.product + ":" + order.cost));
    }


}

