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
        attributes("Start-Class" to "pl.dsyou.movierating.MovieAppApplication")
    }
}

tasks.test {
    useJUnitPlatform()
}

springBoot {
    mainClass.set("pl.dsyou.movierating.MovieAppApplication")
}

dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}

dependencies {

    implementation(project(mapOf("path" to ":common")))
}
