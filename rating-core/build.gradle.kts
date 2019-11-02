plugins {
    id ("java")
    id("org.springframework.boot") version "2.2.0.RELEASE"
}

group ="pl.dsyou"
version = "0.0.1-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {

    compile ("org.springframework.boot:spring-boot-starter-web:2.2.0.RELEASE")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude("org.junit.vintage", "junit-vintage-engine")
    }
}

dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}