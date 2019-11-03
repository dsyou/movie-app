import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id ("java")
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

springBoot {
    mainClassName = "pl.dsyou.movieapp.MovieAppApplication"
}

tasks.getByName<BootJar>("bootJar") {
    mainClassName = "pl.dsyou.movieapp.MovieAppApplication"
}

dependencies {
    compile(project(":rating-data"))

    compile ("org.springframework.boot:spring-boot-starter-web:2.2.0.RELEASE")
    compile("org.springframework:spring-webmvc:5.2.0.RELEASE")

}
