enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "LoyaltyCards"
include(":app")
include(":features:api:root")
include(":features:impl:root")
include(":features:api:loyaltyCardsList")
include(":features:impl:loyaltyCardsList")
include(":features:api:loyaltyCardDetails")
include(":features:impl:loyaltyCardDetails")
include(":features:api:addLoyaltyCard")
include(":features:impl:addLoyaltyCard")
include(":features:api:widget")
include(":features:impl:widget")
 