plugins {
    id("craftyui.library-conventions")
    id("craftyui.remap")
}

group = "me.pesekjak"
version = "1.0.0-SNAPSHOT"

var spigotversion = "1.19.3-R0.1-SNAPSHOT"
var serverversion = "1.19.3"

repositories {
    mavenLocal() // required for spigot server
}

dependencies {
    compileOnly(project(":common"))
    compileOnly("org.spigotmc:spigot:$spigotversion:remapped-mojang")
}

tasks {
    jar {
        doLast {
            remap.get().execute()
        }
    }
    remap {
        version.set(serverversion)
    }
}