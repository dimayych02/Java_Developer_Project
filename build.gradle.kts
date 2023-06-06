plugins {
    id("java")
    id ("io.qameta.allure") version "2.11.2"

}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation ("org.testng:testng:7.3.0")
    testImplementation ("io.rest-assured:rest-assured:5.1.0")
    implementation ("io.qameta.allure:allure-testng:2.20.0")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.13.3")
    implementation("org.projectlombok:lombok:1.18.22")
    implementation ("io.qameta.allure:allure-java-commons:2.20.0")
    implementation ("io.qameta.allure:allure-rest-assured:2.20.1")
    implementation ("org.seleniumhq.selenium:selenium-java:4.8.3")
    implementation ("com.github.mifmif:generex:1.0.2")
    implementation ("io.github.bonigarcia:webdrivermanager:5.3.0")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.22")
    testImplementation ("org.aspectj:aspectjweaver:1.9.8")
}

tasks.test {
    useTestNG()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
