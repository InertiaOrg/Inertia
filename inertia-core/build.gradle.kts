plugins {
    id("com.gradleup.shadow") version "9.4.3"
    id("java")
    id("org.jetbrains.dokka") version "2.2.0"
}


dependencies {
    implementation(project(":inertia-api"))
}

dokka {
    pluginsConfiguration.html {
        footerMessage.set("© Inertia Contributors. Inertia is licensed under the MIT License")
    }

}