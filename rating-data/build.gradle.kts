
plugins {
    id ("java")
    id("io.spring.dependency-management")
    id("org.springframework.boot")
}

tasks.getByName<Jar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}

dependencies {
    compile(project(":rating-core"))
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
}

tasks.test {
    useJUnitPlatform()
}
