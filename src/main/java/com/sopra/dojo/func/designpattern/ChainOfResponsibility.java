package com.sopra.dojo.func.designpattern;

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

    interface FileParser {
        String parse(File file);

        void setNextParser(FileParser next);
    }

    static abstract class AbstractFileParser implements FileParser {
        protected FileParser next;

        @Override
        public void setNextParser(FileParser next) {
            this.next = next;
        }
    }

    static class TextFileParser extends AbstractFileParser {
        @Override
        public String parse(File file) {
            if (file.getType() == File.Type.TEXT) {
                return "Text file: " + file.getContent();
            } else if (next != null) {
                return next.parse(file);
            } else {
                throw new RuntimeException("Unknown file: " + file);
            }
        }
    }

    static class AudioFileParser extends AbstractFileParser {
        @Override
        public String parse(File file) {
            if (file.getType() == File.Type.AUDIO) {
                return "Audio file: " + file.getContent();
            } else if (next != null) {
                return next.parse(file);
            } else {
                throw new RuntimeException("Unknown file: " + file);
            }
        }
    }

    static class VideoFileParser extends AbstractFileParser {
        @Override
        public String parse(File file) {
            if (file.getType() == File.Type.VIDEO) {
                return "Video file: " + file.getContent();
            } else if (next != null) {
                return next.parse(file);
            } else {
                throw new RuntimeException("Unknown file: " + file);
            }
        }
    }

    public static void main(String[] args) {
        FileParser textParser = new TextFileParser();
        FileParser audioParser = new AudioFileParser();
        FileParser videoParser = new VideoFileParser();

        textParser.setNextParser(audioParser);
        audioParser.setNextParser(videoParser);

        File file = new File(File.Type.AUDIO, "Dream Theater  - The Astonishing");
        String result = textParser.parse(file);
        System.out.println("RESULT: " + result);
    }

}
