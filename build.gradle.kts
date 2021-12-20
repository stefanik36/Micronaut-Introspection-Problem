plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.0"
    id("org.jetbrains.kotlin.kapt") version "1.6.0"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.6.0"
    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("io.micronaut.application") version "3.0.2"
}

version = "0.1"
group = "com.example"

val kotlinVersion = project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.example.*")
    }
}

dependencies {
    kapt("io.micronaut:micronaut-http-validation:3.2.2")
    implementation("io.micronaut:micronaut-http-client:3.2.2")
    implementation("io.micronaut:micronaut-runtime:3.2.2")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime:3.0.0")
    implementation("jakarta.annotation:jakarta.annotation-api:2.0.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic:1.2.7")
    implementation("io.micronaut:micronaut-validation:3.2.2")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
    implementation("io.micronaut:micronaut-inject:3.2.2")
    annotationProcessor("io.micronaut:micronaut-inject-java:3.2.2")

    implementation(project(":model"))

    testImplementation("org.assertj:assertj-core:3.21.0")
}


application {
    mainClass.set("com.example.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("11")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}
