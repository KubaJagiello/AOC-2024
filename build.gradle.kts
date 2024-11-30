plugins {
    kotlin("jvm") version "2.1.0"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
    id("io.gitlab.arturbosch.detekt") version "1.23.7"
}

group = "se.jakub"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(23)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
    }
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
