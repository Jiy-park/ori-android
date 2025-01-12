pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "OriAndroid"
include(":app")

include(":core:core")
include(":core:network")
include(":core:presentation")

include(":data:_source:local:data-store-manager")
include(":data:_source:local:room")
include(":data:_source:remote:api")

include(":data:code-post")

include(":domain:code-post")

include(":presentation:code-post")
