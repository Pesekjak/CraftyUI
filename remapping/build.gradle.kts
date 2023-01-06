plugins {
    id("craftyui.library-conventions")
    `kotlin-dsl`
}

group = "me.pesekjak"
version = "1.0.0-SNAPSHOT"

dependencies {
    implementation(libs.jetbrains.kotlin.gradle)
    api(kotlin("stdlib"))
    implementation(libs.md5.specialsource)
}