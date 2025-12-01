val junitVersion = "5.10.0"
val reflectionVersion = "0.10.2"
val lombokVersion = "1.18.42"
val slf4jVersion = "2.0.9"

plugins {
    id("java")
    id("application")
}

group = "dev.zooty"
version = "1.0-SNAPSHOT"

apply(from = "gradle/aoc-download.gradle.kts")

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

application {
    mainClass = "dev.zooty.AdventOfCode"
}

dependencies {
    annotationProcessor ("org.projectlombok:lombok:$lombokVersion")
    compileOnly ("org.projectlombok:lombok:$lombokVersion")
    implementation("org.reflections:reflections:$reflectionVersion")
    implementation("org.slf4j:slf4j-nop:$slf4jVersion")

    testAnnotationProcessor ("org.projectlombok:lombok:$lombokVersion")
    testCompileOnly ("org.projectlombok:lombok:$lombokVersion")
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    maxParallelForks = Runtime.getRuntime().availableProcessors()
}