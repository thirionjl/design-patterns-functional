package com.sopra.dojo.func.designpattern;

import java.util.ArrayList;
import java.util.List;

public class Commands {

    interface Command {
        void run();
    }

    static class Logger implements Command {
        public final String message;

        public Logger(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            System.out.println("Logging: " + message);
        }
    }

    static class FileSaver implements Command {
        public final String message;

        public FileSaver(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            System.out.println("Saving: " + message);
        }
    }

    static class Mailer implements Command {
        public final String message;

        public Mailer(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            System.out.println("Sending: " + message);
        }
    }

    static class Executor {
        public void execute(List<Command> tasks) {
            for (Command task : tasks) {
                task.run();
            }
        }
    }

    public static void main(String[] args) {
        List<Command> tasks = new ArrayList<>();
        tasks.add(new Logger("Hi"));
        tasks.add(new FileSaver("Cheers"));
        tasks.add(new Mailer("Bye"));
        new Executor().execute(tasks);
    }


}
