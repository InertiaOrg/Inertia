plugins {
    id("shared")
    alias(libs.plugins.dokka)
}


dependencies {
    implementation(project(":inertia-api"))

    dokkaPlugin(libs.dokkajava)
}

dokka {
    pluginsConfiguration.html {
        footerMessage.set("© Inertia Contributors. Inertia is licensed under the MIT License")
    }
}