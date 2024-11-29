plugins {
    id("org.springframework.boot") version "3.1.6"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.spring") version "1.9.0"
    kotlin("plugin.jpa") version "1.9.0"
}

group = "com.example"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    // Dependências Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.microsoft.sqlserver:mssql-jdbc:12.2.0.jre11") // Verifique se esta é a versão que você deseja
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.microsoft.sqlserver:mssql-jdbc:12.2.0.jre11")
    implementation("org.jetbrains.exposed:exposed-core:0.41.1") // Biblioteca Exposed Core
    implementation("org.jetbrains.exposed:exposed-dao:0.41.1") // Biblioteca Exposed DAO
    implementation("org.jetbrains.exposed:exposed-jdbc:0.41.1") // Biblioteca Exposed para JDBC
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

}
