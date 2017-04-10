package com.sopra.dojo.func.designpattern;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class ChainOfResponsibility {

    static class File {
        enum Type {TEXT, AUDIO, VIDEO}

        private final Type type;
        private final String content;

        public File(Type type, String content) {
            this.type = type;
            this.content = content;
        }

        public Type getType() {
            return type;
        }

        public String getContent() {
            return content;
        }

        @Override
        public String toString() {
            return type + ": " + content;
        }
    }

    interface Parsers {

        static Function<File, Optional<String>> text() {
            return f -> f.getType() == File.Type.TEXT ? of("Text file: " + f.getContent()) : empty();
        }

        static Function<File, Optional<String>> audio() {
            return f -> f.getType() == File.Type.AUDIO ? of("Audio file: " + f.getContent()) : empty();
        }

        static Function<File, Optional<String>> video() {
            return f -> f.getType() == File.Type.VIDEO ? of("Video file: " + f.getContent()) : empty();
        }
    }

    static String parse(File file) {
        return Stream.of(Parsers.text(), Parsers.audio(), Parsers.video())
                .map(fct -> fct.apply(file))
                .filter(Optional::isPresent)
                .findFirst()
                .flatMap(Function.identity()) // flatten, join  !
                .orElseThrow(() -> new RuntimeException("Unknown file: " + file));
    }

    public static void main(String[] args) {
        File file = new File(File.Type.AUDIO, "Dream Theater  - The Astonishing");
        System.out.println("RESULT: " + parse(file));
    }

}
