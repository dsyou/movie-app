plugins {
    id ("java")
    id("io.spring.dependency-management")
    id("org.springframework.boot")
}

repositories {
    mavenCentral()
}

dependencies {
    tasks.getByName<Jar>("bootJar") {
        enabled = false
    }
}
