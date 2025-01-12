plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    api(projects.features.api.loyaltyCardsList)
    api(projects.features.api.loyaltyCardDetails)
    api(projects.features.api.addLoyaltyCard)

    implementation(libs.decompose)
}