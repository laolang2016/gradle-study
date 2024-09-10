plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://maven.aliyun.com/repository/spring/")
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(libs.guava)

    testImplementation(libs.testng)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(8)
    }
}

application {
    mainClass = "org.example.App"
}

tasks.named<Test>("test") {
    useTestNG()
}
