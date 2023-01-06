package me.pesekjak.craftyui

import org.gradle.api.Plugin
import org.gradle.api.Project

class MojangSpigotRemapperPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.register("remap", RemapTask::class.java)
    }
}