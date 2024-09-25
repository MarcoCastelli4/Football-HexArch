package eu.happycoders.shop.model.example.B;
import eu.happycoders.shop.model.example.C.C;

public class B {
    public void doSomething() {
        System.out.println("Method in class B called");
    }
    // Chiamo metodo normalmente
    public void callMethodC() {
        C c = new C();
        c.doSomething();
    }
}

