plugins {
    id("java")
}

group = "io.github.inertiaorg.inertia"
version = "1.0.0-ALPHA"
description = "Inertia is a base codebase for AntiCheats"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    implementation("org.jspecify:jspecify:1.0.1")
}