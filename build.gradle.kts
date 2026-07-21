plugins {
    id("java")

}

group = "org.loveroo.uuidbruteforce"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

}

tasks.withType(JavaCompile::class.java).configureEach {
    options.encoding = "UTF-8"
}

tasks.withType(Jar::class.java).forEach {
    it.manifest {
        attributes["Main-Class"] = "org.loveroo.uuidbruteforce.Main"
    }
}