import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java")
    id("jacoco")
    id("io.spring.dependency-management")
    id("org.springframework.boot")
}

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

tasks.getByName<Jar>("bootJar") {
    enabled = false
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
}

