import com.laolang.gradle.mavenAlibaba
import com.laolang.gradle.util.GradleUtil
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.internal.classpath.Instrumented.systemProperty
import org.springframework.boot.gradle.tasks.bundling.BootJar


repositories {
    mavenAlibaba()
    mavenLocal()
    mavenCentral()
}

plugins {
    application
    jacoco
    id("com.dorongold.task-tree") version "3.0.0"
    id("maven-publish")
    id("org.springframework.boot") version "2.7.6"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("my-plugin")
}

// 项目坐标
group = "com.laolang.jx"
version = "0.1"

application {
    mainClass.set("com.laolang.jx.JxApplication")
}

// profile
val profile: String = GradleUtil.getProfile("dev")
// 资源目录
val resourceDir = "src/main/resources/$profile"

println("profile:$profile")

// 设置资源目录
sourceSets {
    main {
        resources {
            srcDir(resourceDir)
        }
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("org.springframework.boot:spring-boot-starter-json")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    compileOnly("org.springframework.boot:spring-boot-configuration-processor")

    implementation(libs.tlog)

    implementation(libs.hutool.all)
    implementation(libs.vavr)
    implementation(libs.bundles.mapstruct)
    implementation(libs.guava)
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    implementation("org.apache.commons:commons-lang3")

    testImplementation(libs.testng)
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")

    // 由于引入了 log4j2 , 故此处排除 spring-boot 默认的 logback 日志依赖
    configurations {
        all {
            exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
        }
    }
}


/**
 * 打包时生成 source.jar 和 javadoc.jar
 */
configure<JavaPluginExtension> {
    withSourcesJar()
    withJavadocJar()
}

/**
 * java 编译配置
 */
tasks.withType<JavaCompile> {
    options.encoding = Charsets.UTF_8.name()
    sourceCompatibility = JavaVersion.VERSION_1_8.toString()
    targetCompatibility = JavaVersion.VERSION_1_8.toString()
}

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
 * javadoc
 */
tasks.withType<Javadoc> {
    options {
        encoding = Charsets.UTF_8.name()
        charset(Charsets.UTF_8.name())
        windowTitle = "spring-hello"
        header = "spring-hello"
    }
    // 忽略 javadoc 报错
    isFailOnError = false
}

/**
 * jacoco 任务
 */
tasks.named<JacocoReport>("jacocoTestReport") {
    // 依赖于测试任务
    dependsOn(tasks.test)

    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.required.set(true)

        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}


/**
 * spring-boot 打包
 */
tasks.named<BootJar>("bootJar") {
    // jar 包名称
    archiveBaseName.set("jx-boot")
    // 版本号设置为空
    archiveVersion.set("")
}

/**
 * 发布到本地
 */
publishing {
    repositories {
        mavenLocal()
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])
        }
    }
}

