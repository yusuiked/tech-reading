package org.yukung.sandbox.stream;

import java.util.stream.IntStream;

public class StreamPractice {
    public static void main(String[] args) {
        new StreamPractice().run();
    }

    private void run() {
        printEven();
    }

    private void printEven() {
        IntStream.rangeClosed(1, 10)
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println);
    }
}
