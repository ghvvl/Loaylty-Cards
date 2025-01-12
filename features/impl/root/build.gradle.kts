plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    api(projects.features.api.root)

    implementation(libs.material.compose)
    implementation(libs.decompose)
    implementation(libs.decompose.compose)
}