plugins {
    id ("java")
    id("io.spring.dependency-management")
}

group ="pl.dsyou"


repositories {
    mavenCentral()
}

dependencies {
    compile(project(":rating-core"))
}