package unibs.project.football.bootstrap.archunit;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import java.util.ArrayList;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DependencyRuleTest {

  // i miei package che definiscono la struttura hex
  private static final String ROOT = "unibs.project.football";
  private static final String MODEL = "model";
  private static final String APPLICATION = "application";
  private static final String PORT = "application.port";
  private static final String SERVICE = "application.service";
  private static final String ADAPTER = "adapter";

  private static final String CART_CLASS = "unibs.project.football.model.cart.Cart";

  private static final JavaClasses ALL_SERVICE_CLASSES =
      new ClassFileImporter()
          .withImportOption(new ImportOption.DoNotIncludeTests())
          .importPackages("unibs.project.football");

  private static final JavaClasses MODEL_CLASSES =
      new ClassFileImporter()
          .withImportOption(new ImportOption.DoNotIncludeTests())
          .importPackages("unibs.project.football.model");

  @Test
  void checkHexagonalArchitecture() {
    String importPackages = ROOT + "..";
    JavaClasses classes = new ClassFileImporter().importPackages(importPackages);

    verifyNoDependencyFromTo(MODEL, APPLICATION, classes);
    verifyNoDependencyFromTo(MODEL, ADAPTER, classes);
    verifyNoDependencyFromTo(MODEL, SERVICE, classes);
    verifyNoDependencyFromTo(MODEL, PORT, classes);

    verifyNoDependencyFromTo(APPLICATION, ADAPTER, classes);

    verifyNoDependencyFromTo(PORT, SERVICE, classes);

    verifyNoDependencyFromTo(ADAPTER, SERVICE, classes);
  }

  private void verifyNoDependencyFromTo(String fromPackage, String toPackage, JavaClasses classes) {
    noClasses()
        .that()
        .resideInAPackage(ROOT + '.' + fromPackage + "..")
        .should()
        .dependOnClassesThat()
        .resideInAPackage(ROOT + '.' + toPackage + "..")
        .check(classes);
  }

  @Test
  @Disabled
  void checkTypeErasureLimitation() {
    JavaClasses importedClasses =
        new ClassFileImporter().importPackages("unibs.project.football.model.example.A");

    // Definiamo una regola per verificare che nella classe Cart ci sia un campo di tipo List
    ArchRuleDefinition.fields()
        .that()
        .areDeclaredIn("unibs.project.football.model.example.A.A")
        .and()
        .haveRawType(ArrayList.class) // Controlla se il campo è una List
        .should()
        .beDeclaredInClassesThat()
        .haveSimpleName("A") // Limita alla classe A
        .check(importedClasses);
  }

  @Test
  @Disabled
  void testNoDirectDependencyAtoB() {
    // cerco le classi del mio package al quale voglio applicare la regola
    JavaClasses importedClasses =
        new ClassFileImporter().importPackages("unibs.project.football.model.example");

    noClasses()
        .that()
        .resideInAPackage("unibs.project.football.model.example.A")
        .should()
        .dependOnClassesThat()
        .resideInAPackage("unibs.project.football.model.example.B")
        .check(importedClasses);
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
  void projectShouldBeFreeOfCycles() {
    slices()
        .matching("%s.(**)".formatted("unibs.project.football"))
        .should()
        .beFreeOfCycles()
        .check(ALL_SERVICE_CLASSES);
  }

  @Test
  @DisplayName("Each module should depend only on declared modules")
  void modulesDependencyTest() {
    layeredArchitecture()
        .consideringOnlyDependenciesInLayers()
        .ensureAllClassesAreContainedInArchitectureIgnoring(MODEL)
        .layer("player")
        .definedBy("unibs.project.football.model.player..")
        .layer("team")
        .definedBy("unibs.project.football.model.team..")
        .layer("message")
        .definedBy("unibs.project.football.model.message..")
        .whereLayer("player")
        .mayOnlyAccessLayers("team")
        .check(MODEL_CLASSES);
  }
}
