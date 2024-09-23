import com.github.gradle.node.npm.task.NpmInstallTask
import com.github.gradle.node.npm.task.NpmTask

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.hibernate.orm") version "6.5.2.Final"
    id("org.graalvm.buildtools.native") version "0.10.2"
    id ("com.github.node-gradle.node") version "7.0.2"
    kotlin("plugin.jpa") version "1.9.25"
    kotlin("plugin.serialization") version "1.9.25"
}

group = "top.rhynia"
version = "1.2.1"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    maven("https://maven.aliyun.com/repository/public/")
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json")
    implementation("cn.hutool:hutool-all:5.8.32")
    implementation("com.github.houbb:opencc4j:1.8.1")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

hibernate {
    enhancement {
        enableAssociationManagement = true
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.named<NpmInstallTask>("npmInstall") {
    workingDir = file("../monorim")
}

tasks.register<NpmTask>("buildVue") {
    dependsOn("npmInstall")
    workingDir = file("../monorim")
    args = listOf("run", "build")
}

tasks.register<Delete>("cleanVue") {
    delete("src/main/resources/static")
}

tasks.register<Copy>("copyVue") {
    dependsOn("buildVue", "cleanVue")
    from("../monorim/dist")
    into("src/main/resources/static")
}

tasks.named<ProcessResources>("processResources") {
    dependsOn("copyVue")
}

tasks.register("publish") {
    dependsOn("copyVue")
    finalizedBy("build")
}
