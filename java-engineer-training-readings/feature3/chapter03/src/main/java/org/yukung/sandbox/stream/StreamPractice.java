package org.yukung.sandbox.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class StreamPractice {
    public static void main(String[] args) {
        new StreamPractice().run();
    }

    private void run() {
        printEven();
        printSeparator();
        printStudentsName();
        printSeparator();
    }

    private void printStudentsName() {
        class Student {
            private String name;

            public Student(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
        List<Student> students = Arrays.asList(new Student("Ichiro"), new Student("Jiro"), new Student("Saburo"));
        students.stream()
                .map(s -> s.getName())
                .forEach(n -> System.out.println(n));
    }

    private void printSeparator() {
        System.out.println("-------------------------------");
    }

    private void printEven() {
        IntStream.rangeClosed(1, 10)
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println);
    }
}
