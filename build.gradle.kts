plugins {
    id("com.gradleup.shadow") version "9.6.1"
    id("java")
    id("org.jetbrains.dokka") version "2.2.0"
}

allprojects {
    apply(plugin = "java")

    group = "io.github.inertiaorg.inertia"
    version = "0.1.0-SNAPSHOT"
    description = "Inertia is a base codebase for AntiCheats"

    repositories {
        mavenCentral()
        maven {
            name = "papermc"
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }
    }


}




dependencies {
    // Testing
    testImplementation(platform("org.junit:junit-bom:6.1.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")


    // Dokka
    dokka(project(":inertia-api:"))
    dokka(project(":inertia-core:"))
    dokka(project(":inertia-testkit:"))
    dokkaPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:2.2.0") // Make our code look like Java and not like kotlin
}

tasks {
    test {
        useJUnitPlatform()
    }
}


dokka {
    pluginsConfiguration.html {
        footerMessage.set("© Inertia Contributors. Inertia is licensed under the MIT License")
    }

}