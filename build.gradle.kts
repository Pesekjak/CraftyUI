plugins {
    id("craftyui.library-conventions")
}

group = "me.pesekjak"
version = "1.0.0-SNAPSHOT"

dependencies {
    implementation(project(":common"))
    listOf(
        "v1_19_R2"
    ).forEach {
        implementation(project("nms:$it"))
    }
}

tasks.register<Jar>("buildAll") {
    group = "build"
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.isFile }.map { zipTree(it) }
    })
}