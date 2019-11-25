import org.gradle.kotlin.dsl.*

plugins {
    java
    idea
    jacoco
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
    sourceCompatibility = JavaVersion.VERSION_13
    targetCompatibility = JavaVersion.VERSION_13
}


//export test coverage
tasks.jacocoTestReport {

    executionData(fileTree(project.rootDir.absolutePath).include("**/build/jacoco/*.exec"))

    subprojects.onEach {
        sourceSets(it.sourceSets["main"])
    }

    reports {
        xml.isEnabled = true
        xml.destination = File("$buildDir/reports/jacoco/report.xml")
        html.isEnabled = false
        csv.isEnabled = false
    }

    dependsOn("test")
}

subprojects {
    tasks.withType<JavaCompile> {
        sourceCompatibility = "13"
        targetCompatibility = "13"
    }

    group = "pl.dsyou"
    version = "1.0"
    apply(plugin = "java")
    apply(plugin = "net.ltgt.apt")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "jacoco")

    dependencies {
        compile("org.springframework:spring-context")

        compile("javax.validation:validation-api:2.0.1.Final")
        compile("io.vavr:vavr:0.10.2")

        compile("org.mapstruct:mapstruct:$mapstructVersion")
        annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")

        compileOnly("org.projectlombok:lombok:$lombokVersion")
        annotationProcessor("org.projectlombok:lombok:$lombokVersion")

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


