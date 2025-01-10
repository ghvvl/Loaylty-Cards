plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

dependencies {
    api(projects.features.api.widget)

    implementation(libs.glance.appwidget)
    implementation(libs.glance.material3)
    implementation(libs.zxing)
}

android {
    namespace = "com.vvl.loyalty_cards.impl.widget"
    buildFeatures.compose = true
}
