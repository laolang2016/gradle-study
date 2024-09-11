plugins {
    `kotlin-dsl`
}

repositories {
    maven {
        setUrl("https://maven.aliyun.com/repository/public/")
    }
    maven {
        setUrl("https://maven.aliyun.com/repository/spring/")
    }
    gradlePluginPortal()
    mavenLocal()
    mavenCentral()
}

