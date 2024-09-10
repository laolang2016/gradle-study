pluginManagement {
    repositories {
        // 插件使用阿里云 maven 源
        maven("https://maven.aliyun.com/repository/gradle-plugin")
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "jx-schema"
