package eu.happycoders.shop.model.example.A;
import eu.happycoders.shop.model.example.B.B;

public class A {
    public void doSomething() {
        System.out.println("Method in class A called");
    }
    // Chiamo metodo normalmente
    public void callMethodB() {
        B b = new B();
        b.doSomething();
    }
}



    /*
    // Uso reflection
    public void callMethodInB() throws Exception {
        // Usa la riflessione per invocare il metodo "doSomething" di B
        Class<?> clazz = Class.forName("example.B");
        Method method = clazz.getMethod("doSomething");
        method.invoke(clazz.getDeclaredConstructor().newInstance());
    }*/
