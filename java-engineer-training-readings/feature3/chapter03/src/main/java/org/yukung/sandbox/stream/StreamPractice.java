package org.yukung.sandbox.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPractice {
    public static void main(String[] args) throws Exception {
        new StreamPractice().run();
    }

    private void run() throws Exception {
        printEven();
        printSeparator();
        printStudentsName();
        printSeparator();
        printMaxValue();
        printSeparator();
        Path path = Paths.get("./");
        System.out.println(containsTextFile(path));
        printSeparator();
    }

    private boolean containsTextFile(Path dir) throws IOException {
        return Files.walk(dir)
                .filter(path -> Files.isRegularFile(path))
                .map(path -> path.toString())
                .anyMatch(name -> name.endsWith(".txt"));
    }

    private void printMaxValue() {
        Path path = Paths.get("./data.txt");
        try (Stream<String> s = Files.lines(path)) {
            OptionalInt maxOpt = s.mapToInt(Integer::parseInt).max();
            maxOpt.ifPresent(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                .map(Student::getName)
                .forEach(System.out::println);
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
