import org.gradle.kotlin.dsl.*

plugins {
    java
    idea
    jacoco
    id("org.springframework.boot") version "2.7.13"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "pl.dsyou"
version = "0.0.1-SNAPSHOT"

var lombokVersion = "1.18.28"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

subprojects {
    group = "pl.dsyou"
    version = "0.0.1-SNAPSHOT"
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "jacoco")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-validation")

        compileOnly("org.projectlombok:lombok:$lombokVersion")
        annotationProcessor("org.projectlombok:lombok:$lombokVersion")

        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude("org.junit.vintage", "junit-vintage-engine")
        }

        testCompileOnly("org.assertj:assertj-core:3.24.2")
        testCompileOnly("org.mockito:mockito-core:3.12.4")
    }

}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(project(":core"))
    compileOnly(project(":common"))
    compileOnly(project(":rating"))
}


