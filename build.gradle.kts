plugins {
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.spring") version "1.8.0"
    kotlin("plugin.jpa") version "1.8.0"
}

group = "com.example"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.h2database:h2")  // Banco de dados em memória H2
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
        // Exposed library dependencies
        implementation("org.jetbrains.exposed:exposed-core:0.41.1") // Versão mais recente disponível no momento
        implementation("org.jetbrains.exposed:exposed-dao:0.41.1")
        implementation("org.jetbrains.exposed:exposed-jdbc:0.41.1")

        // Driver do banco de dados H2
        implementation("com.h2database:h2:2.1.214") // Verifique a versão mais recente do H2
}
