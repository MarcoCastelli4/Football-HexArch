package unibs.project.football.bootstrap.archunit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.google.protobuf.Service;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

import org.hibernate.mapping.Component;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DependencyRuleTest {

  // i miei package che definiscono la struttura hex
  private static final String ROOT_PACKAGE = "unibs.project.football";
  private static final String MODEL_PACKAGE = "model";
  private static final String APPLICATION_PACKAGE = "application";
  private static final String PORT_PACKAGE = "application.port";
  private static final String SERVICE_PACKAGE = "application.service";
  private static final String ADAPTER_PACKAGE = "adapter";
  private static final String BOOTSTRAP_PACKAGE = "bootstrap";

  private static final String CART_CLASS = "unibs.project.football.model.cart.Cart";

  private static final JavaClasses ALL_SERVICE_CLASSES =
          new ClassFileImporter().withImportOption(new ImportOption.DoNotIncludeTests())
          .importPackages("unibs.project.football");

  private static final JavaClasses MODEL_CLASSES =
          new ClassFileImporter().withImportOption(new ImportOption.DoNotIncludeTests())
                  .importPackages("unibs.project.football.model");

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


  private String fullyQualified(String packageName) {
    return ROOT_PACKAGE + '.' + packageName + "..";
  }

  @Test
  void checkTypeErasureLimitation() {
    JavaClasses importedClasses = new ClassFileImporter().importPackages("unibs.project.football.model.example.A");

    // Definiamo una regola per verificare che nella classe Cart ci sia un campo di tipo List
    ArchRuleDefinition.fields()
        .that().areDeclaredIn("unibs.project.football.model.example.A.A")
        .and().haveRawType(ArrayList.class) // Controlla se il campo è una List
        .should().beDeclaredInClassesThat().haveSimpleName("A") // Limita alla classe A
        .check(importedClasses);
  }

  @Test
  void testNoDirectDependencyAtoB() {
    // cerco le classi del mio package al quale voglio applicare la regola
    JavaClasses importedClasses =
        new ClassFileImporter().importPackages("unibs.project.football.model.example");

    noClasses()
        .that().resideInAPackage("unibs.project.football.model.example.A")
        .should().dependOnClassesThat()
        .resideInAPackage("unibs.project.football.model.example.B").check(importedClasses);
  }

  @Test
  @DisplayName("Packages should be free of cycles")
  void packagesShouldBeFreeOfCycles() {
    slices()
        .matching("%s.(**)".formatted("unibs.project.football"))
        .should()
        .beFreeOfCycles()
        .check(ALL_SERVICE_CLASSES);
  }

  @Test
  @DisplayName("Project should be free of cycles")
  void projectShouldBeFreeOfCycles(){
    slices().matching("%s.(**)".formatted("unibs.project.football"))
            .should().beFreeOfCycles().check(ALL_SERVICE_CLASSES);
  }

  @Test
  @DisplayName("Each module should depend only on declared modules")
  void modulesDependencyTest() {
    layeredArchitecture()
            .consideringOnlyDependenciesInLayers()
            .ensureAllClassesAreContainedInArchitectureIgnoring(MODEL_PACKAGE)
            .layer("player")   .definedBy("unibs.project.football.model.player..")
            .layer("team")  .definedBy("unibs.project.football.model.team..")
            .layer("message")  .definedBy("unibs.project.football.model.message..")
            .whereLayer("player")   .mayOnlyAccessLayers("team")
            .check(MODEL_CLASSES);
  }







}
