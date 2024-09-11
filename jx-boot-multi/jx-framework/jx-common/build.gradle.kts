dependencies {

    compileOnlyApi("org.springframework.boot:spring-boot-starter-web")
    api("org.springframework.boot:spring-boot-starter-json")
    api("org.springframework.boot:spring-boot-starter-log4j2")

    compileOnlyApi(libs.knife4j)

    api(libs.tlog)

    api(libs.hutool.all)
    api(libs.vavr)
    api(libs.bundles.mapstruct)
    api(libs.guava)
    api("org.apache.commons:commons-lang3")
}

