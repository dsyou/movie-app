import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id ("java")
    id("io.spring.dependency-management")
    id("org.springframework.boot")
}

repositories {
    mavenCentral()
}

tasks.getByName<Jar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

tasks.getByName<BootJar>("bootJar") {
    manifest {
        attributes("Start-Class" to "pl.dsyou.movieapp.core.MovieAppApplication")
    }
}

tasks.getByName<BootJar>("bootJar") {
    mainClassName = "pl.dsyou.movieapp.MovieAppApplication"
}

springBoot {
    mainClassName = "pl.dsyou.movieapp.MovieAppApplication"
}

dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}

dependencies {

    compile ("org.springframework.boot:spring-boot-starter-web:2.2.0.RELEASE")
}
