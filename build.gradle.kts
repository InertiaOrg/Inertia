plugins {
    alias(libs.plugins.dokka)
}

dependencies {
    dokka(project(":inertia-api:"))
    dokka(project(":inertia-core:"))
    dokka(project(":inertia-testkit:"))
    dokkaPlugin(libs.dokkajava)
}

dokka {
    pluginsConfiguration.html {
        footerMessage.set("© Inertia Contributors. Inertia is licensed under the MIT License")
    }
}