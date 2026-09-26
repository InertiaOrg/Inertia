plugins {
    id("shared")
    alias(libs.plugins.dokka)
}

dependencies {
    implementation(project(":inertia-core"))
    implementation(project(":inertia-api"))

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform)

    dokkaPlugin(libs.dokkajava)
}

tasks.test {
    useJUnitPlatform()
}

dokka {
    pluginsConfiguration.html {
        footerMessage.set("© Inertia Contributors. Inertia is licensed under the MIT License")
    }
}