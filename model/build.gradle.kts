plugins {
    kotlin("jvm")
}

group = "com.example"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.micronaut:micronaut-validation:3.2.2")
    annotationProcessor("io.micronaut:micronaut-validation:3.2.2")

    implementation("io.micronaut:micronaut-inject:3.2.2")
    annotationProcessor("io.micronaut:micronaut-inject-java:3.2.2")
}