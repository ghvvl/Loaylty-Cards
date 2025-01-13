plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.compose)
}

dependencies {
    api(projects.features.api.loyaltyCardsList)
    implementation(projects.features.api.root)

    implementation(libs.material.compose)
    implementation(libs.decompose)
    implementation(libs.decompose.compose)
}