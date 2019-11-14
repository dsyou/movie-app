import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("java")
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

tasks.getByName<BootJar>("bootJar") {
    mainClassName = "pl.dsyou.movieapp.MovieAppApplication"
}

springBoot {
    mainClassName = "pl.dsyou.movieapp.MovieAppApplication"
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
    compile(project(":rating-data"))
    compile("org.springframework:spring-webmvc:5.2.0.RELEASE")
}

