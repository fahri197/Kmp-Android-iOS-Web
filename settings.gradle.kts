rootProject.name = "KmpDemo"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}


include(":composeApp")
include(":domain")
include(":data")
include(":data:local")
include(":data:network")
include(":data:auth")
include(":shared")
include(":shared:resources")
include(":usecase")
include(":presentation")
include(":presentation:authentication")
include(":presentation:diy")
include(":presentation:home")
include(":presentation:profile")
include(":presentation:schedule")