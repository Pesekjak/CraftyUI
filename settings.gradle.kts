rootProject.name = "CraftyUI"

enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    includeBuild("build-logic")
    includeBuild("remapping")
}
include("common")
include("nms")
listOf(
    "v1_19_R2"
).forEach {
    include("nms:$it")
    findProject(":nms:$it")?.name = it
}