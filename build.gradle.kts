import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.4"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	kotlin("plugin.jpa") version "1.7.22"
	kotlin("kapt") version "1.7.22"
}

group = "com.blog"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.vladmihalcea:hibernate-types-60:2.21.1")
	implementation("com.infobip:infobip-spring-data-jpa-querydsl-boot-starter:8.1.0")
	kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

	implementation("me.yaman.can:spring-boot-webflux-h2-console:0.0.1")
	implementation("com.h2database:h2")

	implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")
	implementation("org.glassfish:jakarta.el:4.0.2")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
	testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-debug")
	testImplementation("io.mockk:mockk:1.13.4")
	testImplementation("org.mockito:mockito-inline:5.2.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
