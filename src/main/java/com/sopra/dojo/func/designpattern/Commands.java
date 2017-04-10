package com.sopra.dojo.func.designpattern;

import java.util.ArrayList;
import java.util.List;

public class Commands {

    static Runnable logger(String message) {
        return () -> System.out.println("Logging: " + message);
    }

    static Runnable fileSaver(String message) {
        return () -> System.out.println("Saving: " + message);
    }

    static Runnable mailer(String message) {
        return () -> System.out.println("Sending: " + message);
    }

    private static void execute(List<Runnable> tasks) {
        tasks.forEach(Runnable::run);
    }

    public static void main(String[] args) {
        List<Runnable> tasks = new ArrayList<>();
        tasks.add(logger("Hi"));
        tasks.add(fileSaver("Cheers"));
        tasks.add(mailer("Bye"));
        execute(tasks);
    }


}
