plugins {
    id("com.gradleup.shadow") version "9.5.1"
    id("java")
    id("org.jetbrains.dokka") version "2.2.0"
}

dependencies {
    implementation(project(":inertia-core"))
    implementation(project(":inertia-api"))


    // Testing
    testImplementation(platform("org.junit:junit-bom:6.1.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

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