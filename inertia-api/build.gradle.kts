plugins {
    id("com.gradleup.shadow") version "9.4.2"
    id("java")
    id("org.jetbrains.dokka") version "2.2.0"
}


dependencies {
    // Testing
    testImplementation(platform("org.junit:junit-bom:6.1.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
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