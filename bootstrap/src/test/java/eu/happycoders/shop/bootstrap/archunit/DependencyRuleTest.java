package eu.happycoders.shop.bootstrap.archunit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import eu.happycoders.shop.model.annotation.ImportantCalculation;
import eu.happycoders.shop.model.cart.Cart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

class DependencyRuleTest {

  // i miei package che definiscono la struttura hex
  private static final String ROOT_PACKAGE = "eu.happycoders.shop";
  private static final String MODEL_PACKAGE = "model";
  private static final String APPLICATION_PACKAGE = "application";
  private static final String PORT_PACKAGE = "application.port";
  private static final String SERVICE_PACKAGE = "application.service";
  private static final String ADAPTER_PACKAGE = "adapter";
  private static final String BOOTSTRAP_PACKAGE = "bootstrap";


  private final static String CART_CLASS = "eu.happycoders.shop.model.cart.Cart";

  private static final JavaClasses ALL_SERVICE_CLASSES = new ClassFileImporter()
          .withImportOption(new ImportOption.DoNotIncludeTests())
          .importPackages("eu.happycoders.shop");


  @Test
  void checkDependencyRule() {
    String importPackages = ROOT_PACKAGE + "..";
    // importo tutte le classi del package
    JavaClasses classesToCheck = new ClassFileImporter().importPackages(importPackages);

    checkNoDependencyFromTo(MODEL_PACKAGE, APPLICATION_PACKAGE, classesToCheck);
    checkNoDependencyFromTo(MODEL_PACKAGE, ADAPTER_PACKAGE, classesToCheck);
    checkNoDependencyFromTo(MODEL_PACKAGE, BOOTSTRAP_PACKAGE, classesToCheck);

    checkNoDependencyFromTo(APPLICATION_PACKAGE, ADAPTER_PACKAGE, classesToCheck);
    checkNoDependencyFromTo(APPLICATION_PACKAGE, BOOTSTRAP_PACKAGE, classesToCheck);

    checkNoDependencyFromTo(PORT_PACKAGE, SERVICE_PACKAGE, classesToCheck);

    checkNoDependencyFromTo(ADAPTER_PACKAGE, SERVICE_PACKAGE, classesToCheck);
    checkNoDependencyFromTo(ADAPTER_PACKAGE, BOOTSTRAP_PACKAGE, classesToCheck);
  }

  private void checkNoDependencyFromTo(
      String fromPackage, String toPackage, JavaClasses classesToCheck) {
    noClasses()
        .that()
        .resideInAPackage(fullyQualified(fromPackage))
        .should()
        .dependOnClassesThat()
        .resideInAPackage(fullyQualified(toPackage))
        .check(classesToCheck);
  }

  @Test
  void noMethodsShouldBeAnnotatedWithImportantCalculation() {
    // Importa tutte le classi del package specificato
    JavaClasses importedClasses =
        new ClassFileImporter().importPackages("eu.happycoders.shop.model.cart");

    // Regola ArchUnit: nessun metodo dovrebbe essere annotato con @ImportantCalculation
    ArchRuleDefinition.noMethods()
        .should()
        .beAnnotatedWith(
            ImportantCalculation
                .class) // Verifica che nessun metodo sia annotato con @ImportantCalculation
        .check(importedClasses); // Esegui la regola sulle classi importate
  }

    @Test
    void fieldShouldBeListOfString() throws NoSuchFieldException {
        // Ottieni il campo
        Field field = Cart.class.getDeclaredField("test");

        // Verifica che il campo sia di tipo List
        assertTrue(List.class.isAssignableFrom(field.getType()));

        // Verifica il tipo generico del campo
        ParameterizedType listType = (ParameterizedType) field.getGenericType();
        assertTrue(listType.getActualTypeArguments()[0].equals(String.class));
    }

  private String fullyQualified(String packageName) {
    return ROOT_PACKAGE + '.' + packageName + "..";
  }

  @Test
  void checkTypeErasureLimitation(){

       // Modifica il percorso del pacchetto se necessario
      JavaClasses importedClasses = new ClassFileImporter().importPackages("eu.happycoders.shop.model.cart");

      // Definiamo una regola per verificare che nella classe Cart ci sia un campo di tipo List
      ArchRuleDefinition.fields()
              .that().areDeclaredIn("eu.happycoders.shop.model.cart.Cart")
              .and().haveRawType(ArrayList.class) // Controlla se il campo è una List
              .should().beDeclaredInClassesThat().haveSimpleName("Cart") // Limita alla classe Cart
              .check(importedClasses);

  }

    @Test
    void testNoDirectDependencyAtoB() {
      // cerco le classi del mio package al quale voglio applicare la regola
      JavaClasses importedClasses = new ClassFileImporter().importPackages("eu.happycoders.shop.model.example");

      noClasses()
              .that().resideInAPackage("eu.happycoders.shop.model.example.A")
              .should().dependOnClassesThat().resideInAPackage("eu.happycoders.shop.model.example.B")
              .check(importedClasses);
    }


  @Test
  @DisplayName("Packages should be free of cycles")
  void packagesShouldBeFreeOfCycles() {
    slices()
            .matching("%s.(**)".formatted("eu.happycoders.shop"))
            .should().beFreeOfCycles()
            .check(ALL_SERVICE_CLASSES);
  }



}
