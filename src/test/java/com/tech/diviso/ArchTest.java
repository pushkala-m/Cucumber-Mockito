package com.tech.diviso;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.tech.diviso");

        noClasses()
            .that()
                .resideInAnyPackage("com.tech.diviso.service..")
            .or()
                .resideInAnyPackage("com.tech.diviso.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.tech.diviso.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
