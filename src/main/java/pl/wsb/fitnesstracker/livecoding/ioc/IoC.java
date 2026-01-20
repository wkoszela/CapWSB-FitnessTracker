package pl.wsb.fitnesstracker.livecoding.ioc;

public class IoC {

    public static void main(String[] args) {

        Bar bar = new Bar();


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


        public Foo(Bar bar) {
            this.bar = bar;
        }

        public void useBar() {
            bar.doSomething();
        }
    }
}
