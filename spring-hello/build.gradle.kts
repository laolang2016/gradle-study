import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.laolang.jx.gradle.GradleUtil
import org.gradle.api.tasks.testing.logging.TestExceptionFormat

/**
 * 添加插件
 */
plugins {
    application
    jacoco
    id("maven-publish")
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("com.dorongold.task-tree") version "3.0.0"
}

/**
 * 配置仓库
 */
repositories {
    maven("https://maven.aliyun.com/repository/public/")
    maven("https://maven.aliyun.com/repository/spring/")
    mavenLocal()
    mavenCentral()
}

// 项目坐标
group = "com.laolang.jx"
version = "0.1"

/**
 * 声明启动类
 */
application {
    mainClass = "com.laolang.jx.SpringHelloApp"
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

// 配置依赖
dependencies {

    implementation(platform("org.springframework:spring-framework-bom:5.3.31"))

    implementation("org.springframework:spring-context")

    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok)
    implementation(libs.guava)

    implementation(libs.bundles.logback)

    testImplementation(libs.testng)
    testCompileOnly(libs.lombok)
    testAnnotationProcessor(libs.lombok)
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

/**
 * 排除 dev 目录和 test 目录
 */
tasks.withType<ProcessResources> {
    exclude("dev/**")
    exclude("test/**")
}

/**
 * javadoc
 */
tasks.withType<Javadoc> {
    options {
        encoding = Charsets.UTF_8.name()
        charset(Charsets.UTF_8.name())
    }
    // 忽略 javadoc 报错
    isFailOnError = false
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

/**
 * 打包可执行 jar
 */
tasks.named<ShadowJar>("shadowJar") {
    archiveBaseName.set(project.name)
    archiveVersion.set(project.version.toString())
    archiveFileName.set(project.name + ".jar")

    destinationDirectory.set(layout.buildDirectory.dir("shaded"))
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