plugins {
    id("com.gradleup.shadow") version "9.6.0"
    id("java")
    id("org.jetbrains.dokka") version "2.2.0"
}


dependencies {
    implementation(project(":inertia-api"))

    dokkaPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:2.2.0") // Make our code look like Java and not like kotlin
}

dokka {
    pluginsConfiguration.html {
        footerMessage.set("© Inertia Contributors. Inertia is licensed under the MIT License")
    }

}