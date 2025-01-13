plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.compose)
}

dependencies {
    api(projects.features.api.loyaltyCardDetails)

    implementation(libs.material.compose)
    implementation(libs.decompose)
    implementation(libs.decompose.compose)
}