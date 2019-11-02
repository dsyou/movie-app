plugins {
    id ("java")
    id("io.spring.dependency-management")
}

group ="pl.dsyou"

repositories {
    mavenCentral()
}

//dependencyManagement {
//    imports {
//        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
//    }
//}

dependencies {
    compile(project(":rating-data"))
    compile("org.springframework:spring-webmvc:5.2.0.RELEASE")

}
