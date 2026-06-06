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
        maven("https://jitpack.io")
    }
}

rootProject.name = "BaranCalendar"

include(":app")
include(":core:common")
include(":core:data")
include(":core:database")
include(":core:datastore")
include(":domain:calendar")
include(":domain:prayer")
include(":domain:qibla")
include(":domain:numerology")
include(":domain:zodiac")
include(":feature:home")
include(":feature:gregorian")
include(":feature:hijri")
include(":feature:sabaean")
include(":feature:converter")
include(":feature:prayer")
include(":feature:qibla")
include(":feature:numerology")
include(":feature:zodiac")
include(":feature:settings")
include(":feature:widgets")
