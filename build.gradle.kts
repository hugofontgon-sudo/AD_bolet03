plugins {
    id("java")
}

group = "com.hugofont.PSP.tema01"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("com.google.code.gson:gson:2.13.2")
    implementation("org.json:json:20250517")
}

tasks.test {
    useJUnitPlatform()
}