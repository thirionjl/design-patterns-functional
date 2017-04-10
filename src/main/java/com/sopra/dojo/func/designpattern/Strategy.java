package com.sopra.dojo.func.designpattern;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Strategy {

    static class TextEditor {
        final Predicate<String> predicateFilter;
        final UnaryOperator<String> formatter;

        TextEditor(Predicate<String> predicateFilter, UnaryOperator<String> formatter) {
            this.predicateFilter = predicateFilter;
            this.formatter = formatter;
        }

        public void publishText(String text) {
            if (predicateFilter.test(text)) {
                System.out.println(formatter.apply(text));
            }
        }
    }

    static class TextFilters {
        static Predicate<String> acceptAll() {
            return (text) -> true;
        }

        static Predicate<String> onlyErrors() {
            return (text) -> text.startsWith("ERROR");
        }

        static Predicate<String> onlyShorterThan(int len) {
            return (text) -> text.length() < len;
        }
    }

    static class TextFormats {
        static UnaryOperator<String> noFormat() {
            return UnaryOperator.identity();
        }

        static UnaryOperator<String> upperCase() {
            return String::toUpperCase;
        }

        static UnaryOperator<String> lowerCase() {
            return String::toLowerCase;
        }
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor(TextFilters.onlyShorterThan(20), TextFormats.lowerCase());
        textEditor.publishText("ERROR - something bad happened");
        textEditor.publishText("DEBUG - I'm here");
    }

}
