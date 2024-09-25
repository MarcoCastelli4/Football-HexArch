package eu.happycoders.shop.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // Indica che questa annotazione sarà utilizzata sui metodi
@Retention(RetentionPolicy.RUNTIME) // Sarà disponibile a runtime
public @interface ImportantCalculation {
  // Puoi aggiungere proprietà opzionali se necessario
}
