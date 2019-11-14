import org.gradle.kotlin.dsl.*

plugins {
    java
    idea
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    id("org.springframework.boot") version "2.2.0.RELEASE" apply false
    id("net.ltgt.apt") version "0.21"
}

group = "pl.dsyou"

var mapstructVersion = "1.3.1.Final"
var lombokVersion = "1.18.10"


allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_12
    targetCompatibility = JavaVersion.VERSION_12
}

subprojects {
    tasks.withType<JavaCompile> {
        sourceCompatibility = "12"
        targetCompatibility = "12"
    }

    group = "pl.dsyou"
    version = "1.0"
    apply(plugin = "java")
    apply(plugin = "net.ltgt.apt")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")

    dependencies {
        compile("org.springframework:spring-context")

        compile("javax.validation:validation-api:2.0.1.Final")

        compile("org.mapstruct:mapstruct:$mapstructVersion")
        annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")

        compileOnly("org.projectlombok:lombok:$lombokVersion")
        annotationProcessor("org.projectlombok:lombok:$lombokVersion")

//        testImplementation("junit:junit:4.12")
        testImplementation("org.springframework.boot:spring-boot-starter-test") {
            exclude("org.junit.vintage", "junit-vintage-engine")
        }

        testCompile("org.assertj:assertj-core:3.6.1")
        testCompile("org.mockito:mockito-core:3.1.0")
        testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo")
    }
}


repositories {
    mavenCentral()
}

dependencies {
    compile(project(":rating-core"))
    compile(project(":rating-api"))
    compile(project(":rating-data"))
}


