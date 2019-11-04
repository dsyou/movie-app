import org.gradle.kotlin.dsl.*

plugins {
    java
    idea
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    id("org.springframework.boot") version "2.2.0.RELEASE" apply false
    id("net.ltgt.apt") version "0.21"
}

group = "pl.dsyou"

ext {
    var mapstructVersion = "1.3.1.Final"
    var lombokVersion = "1.18.10"
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

subprojects {
    group = "pl.dsyou"
    version = "1.0"
    apply(plugin = "java")
    apply(plugin = "net.ltgt.apt")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin ="org.springframework.boot")

    dependencies {
        compile("org.springframework:spring-context")

        compile("javax.validation:validation-api:2.0.1.Final")

        compile("org.mapstruct:mapstruct:1.3.1.Final")
        annotationProcessor("org.mapstruct:mapstruct-processor:1.3.1.Final")

        compileOnly("org.projectlombok:lombok:1.18.10")
        annotationProcessor("org.projectlombok:lombok:1.18.10")

        testImplementation("junit:junit:4.12")
        testCompile("org.assertj:assertj-core:3.6.1")
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_12
}

repositories {
    mavenCentral()
}

dependencies {
    compile(project(":rating-core"))
    compile(project(":rating-api"))
    compile(project(":rating-data"))


//    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
//    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
//    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo")
}


