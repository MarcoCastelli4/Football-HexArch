package eu.happycoders.shop.model.example.C;
import eu.happycoders.shop.model.example.A.A;

public class C {
    public void doSomething() {
        System.out.println("Method in class C called");
    }
    public void callMethodA() {
        A a = new A();
        a.doSomething();
    }

}
