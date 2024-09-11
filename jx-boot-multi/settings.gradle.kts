pluginManagement {
    repositories {
        // 插件使用阿里云 maven 源
        maven("https://maven.aliyun.com/repository/gradle-plugin")
        gradlePluginPortal()
    }
}

rootProject.name = "jx-boot-multi"

include(":jx-app")

include(":jx-framework:jx-common")
include(":jx-framework:jx-spring-boot-starter-web")

include(":jx-module:jx-module-system:jx-module-system-api")
include(":jx-module:jx-module-system:jx-module-system-biz")
