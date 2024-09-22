plugins {
	java
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "tech.indus340"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("io.github.sashirestela:simple-openai:3.8.0") {
		exclude("org.slf4j","slf4j-simple")
	}
	implementation("dev.langchain4j:langchain4j:0.34.0")
	implementation("dev.langchain4j:langchain4j-open-ai:0.34.0")
	implementation("com.google.api-client:google-api-client:2.0.0")
	implementation("com.google.oauth-client:google-oauth-client-jetty:1.34.1")
	implementation("com.google.apis:google-api-services-calendar:v3-rev20220715-2.0.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

repositories {
	mavenCentral()
	maven {
		url = uri("https://mvn.0110.be/releases/")
	}
}
