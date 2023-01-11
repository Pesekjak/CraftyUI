plugins {
    id("craftyui.library-conventions")
}

group = "me.pesekjak"
version = "1.0.0-SNAPSHOT"

dependencies {
    compileOnly(project(":common"))
    listOf(
        "v1_19_R2"
    ).forEach {
        compileOnly(project(":nms:$it"))
    }
    compileOnly(project(":nms:v1_19_R2"))
}