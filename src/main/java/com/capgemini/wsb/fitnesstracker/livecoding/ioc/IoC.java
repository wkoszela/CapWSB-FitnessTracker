package com.capgemini.wsb.fitnesstracker.livecoding.ioc;

public class IoC {

    public static void main(String[] args) {
        // Instance created outside
        Bar bar = new Bar();

        // Foo constructed with Bar (from outside)
        Foo foo = new Foo(bar);
        foo.useBar();
    }

    static class Bar {
        public void doSomething() {
            System.out.println("Doing something in Bar");
        }
    }

    static class Foo {
        private Bar bar;

        // Dependency injected from Outside
        public Foo(Bar bar) {
            this.bar = bar;
        }

        public void useBar() {
            bar.doSomething();
        }
    }
}
