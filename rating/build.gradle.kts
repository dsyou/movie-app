
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
    implementation(project(mapOf("path" to ":common")))
}

tasks.test {
    useJUnitPlatform()
}
