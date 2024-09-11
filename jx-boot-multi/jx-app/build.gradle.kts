import com.laolang.gradle.Version
import com.laolang.gradle.util.GradleUtil
import org.springframework.boot.gradle.tasks.bundling.BootJar

/**
 * 已经在根项目声明过的插件,在子项目中使用时, 不需要写版本号
 */
plugins {
    application
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("maven-publish")
    id("com.dorongold.task-tree")
}

/**
 * 设置启动类
 */
application {
    mainClass.set("com.laolang.jx.App")
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

/**
 * 依赖
 */
dependencies {
    implementation(project(":jx-module:jx-module-system:jx-module-system-biz"))

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // 由于引入了 log4j2, 所以此处排除默认的 logback 依赖
    configurations {
        all {
            exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
        }
    }
}

group = "com.laolang.jx"
version = "0.1"

java {
    targetCompatibility = Version.targetCompatibility
    sourceCompatibility = Version.sourceCompatibility
    withSourcesJar()
    withJavadocJar()
}

/**
 * java 编译配置
 */
tasks.withType<JavaCompile> {
    options.encoding = Charsets.UTF_8.name()
}

/**
 * javadoc
 */
tasks.withType<Javadoc> {
    options {
        encoding = Charsets.UTF_8.name()
        charset(Charsets.UTF_8.name())
    }
    isFailOnError = false
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

/**
 * spring-boot 打包
 */
tasks.named<BootJar>("bootJar") {
    // jar 包名称
    archiveBaseName.set("jx-boot")
    // 版本号设置为空
    archiveVersion.set("")
}