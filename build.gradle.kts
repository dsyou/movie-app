import org.gradle.kotlin.dsl.*

plugins {
    id("org.springframework.boot") version "2.2.0.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    java
    idea
}

group = "pl.dsyou"
version = "0.0.1-SNAPSHOT"

subprojects {
    tasks.withType<JavaCompile> {
        sourceCompatibility = "12"
        targetCompatibility = "12"
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_12
}

//configurations {
//    developmentOnly
//    runtimeClasspath {
//        extendsFrom developmentOnly
//    }
//    compileOnly {
//        extendsFrom annotationProcessor
//    }
//}

repositories {
    mavenCentral()
}

dependencies {
    compile(project(":rating-core"))
    compile(project(":rating-api"))
    compile(project(":rating-data"))

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-web")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude("org.junit.vintage", "junit-vintage-engine")
    }
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo")
}


