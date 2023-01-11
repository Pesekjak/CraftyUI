pluginManagement {
    includeBuild("../build-logic")
}

include(":common")
project(":common").projectDir = file("../common")

listOf(
    "v1_19_R2"
).forEach {
    include(":nms:$it")
    project(":nms:$it").projectDir = file("$it")
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}