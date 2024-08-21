plugins {
	java
	id("org.springframework.boot") version "3.3.2"
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
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// JUnit 5
	testImplementation("org.junit.jupiter:junit-jupiter")
	testRuntimeOnly("org.junit.vintage:junit-vintage-engine")

	// Cucumber dependencies
	testImplementation("io.cucumber:cucumber-java:7.18.1")
	testImplementation("io.cucumber:cucumber-spring:7.18.1")
	testImplementation("io.cucumber:cucumber-junit:7.18.1")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
