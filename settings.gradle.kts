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
include(":features:common")
include(":features:api:root")
include(":features:impl:root")
include(":features:api:loyaltyCardsList")
include(":features:impl:loyaltyCardsList")
include(":features:api:loyaltyCardDetails")
include(":features:impl:loyaltyCardDetails")
include(":features:api:addLoyaltyCard")
include(":features:impl:addLoyaltyCard")
include(":features:api:deepLinks")
include(":features:impl:deepLinks")
include(":features:api:widget")
include(":features:impl:widget")
include(":features:api:home")
include(":features:impl:home")
include(":features:api:widgetsList")
include(":features:impl:widgetsList")
include(":features:api:widgetDetails")
include(":features:impl:widgetDetails")
include(":data:storage:api:loyaltyCards")
include(":data:storage:impl:loyaltyCards")
include(":data:storage:api:widget")
include(":data:storage:impl:widget")
include(":data:storage:impl:database")
include(":common")