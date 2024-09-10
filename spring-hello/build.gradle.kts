import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    application
    jacoco
}

repositories {
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://maven.aliyun.com/repository/spring/")
    mavenLocal()
    mavenCentral()
}

dependencies {
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    implementation(libs.guava)

    implementation(libs.bundles.logback)

    testImplementation(libs.testng)
    testCompileOnly(libs.lombok)
    testAnnotationProcessor(libs.lombok)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(8)
    }
}

application {
    mainClass = "com.laolang.jx.SpringHelloApp"
}

/**
 * 测试任务
 */
tasks.named<Test>("test") {
    useTestNG {
        suites("testng.xml")
    }
    // 输出详细日志
    testLogging {
        // 记录日志的事件类型
        events("FAILED", "PASSED", "SKIPPED", "STANDARD_ERROR", "STANDARD_OUT", "STARTED")
        // 记录测试异常的格式
        // FULL: 完整显示异常
        // SHORT: 异常的简短显示
        exceptionFormat = TestExceptionFormat.FULL
        // 是否记录标准输出和标准错误的输出
        showStandardStreams = true
    }

    finalizedBy(tasks.jacocoTestReport)
}

/**
 * jacoco 任务
 */
tasks.named<JacocoReport>("jacocoTestReport") {
    // 依赖于测试任务
    dependsOn(tasks.test)

    reports {
        // 把不需要的报告去掉
        xml.required.set(false)
        csv.required.set(false)

        // 只启用 html 报告
        html.required.set(true)

        // jacoco 报告位置
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}