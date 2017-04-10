package com.sopra.dojo.func.designpattern;

import java.util.Random;

public class TemplateMethod {

    static class Resource {
        public Resource() {
            System.out.println("Resource created");
        }

        public void useResource() {
            riskyOperation();
            System.out.println("Resource used");
        }

        public void employResource() {
            riskyOperation();
            System.out.println("Resource employed");
        }

        public void dispose() {
            System.out.println("Resource disposed");
        }

        private void riskyOperation() {
            if (new Random().nextInt(10) == 0) {
                throw new RuntimeException();
            }
        }
    }

    static abstract class AbstractResourceManipulatorTemplate {
        protected Resource resource;

        private void openResource() {
            resource = new Resource();
        }

        protected abstract void doSomethingWithResource();

        private void closeResource() {
            resource.dispose();
            resource = null;
        }

        public void execute() {
            openResource();
            try {
                doSomethingWithResource();
            } finally {
                closeResource();
            }
        }
    }

    static class ResourceUser extends AbstractResourceManipulatorTemplate {
        @Override
        protected void doSomethingWithResource() {
            resource.useResource();
        }
    }

    static class ResourceEmployer extends AbstractResourceManipulatorTemplate {
        @Override
        protected void doSomethingWithResource() {
            resource.employResource();
        }
    }

    public static void main(String[] args) {
        new ResourceUser().execute();
        new ResourceEmployer().execute();
    }

}
