pluginManagement {
    includeBuild("../../build-logic")
    includeBuild("../../remapping")
}

include(":common")
project(":common").projectDir = file("../../common")

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../../gradle/libs.versions.toml"))
        }
    }
}