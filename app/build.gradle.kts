import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    java
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

val junitVersion="5.8.2"
val serenityVersion="4.0.30"

dependencies {
    implementation ("org.junit.jupiter:junit-jupiter:${junitVersion}")
    implementation ("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
    implementation ("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
    implementation ("org.junit.platform:junit-platform-launcher")
    implementation ("org.junit.platform:junit-platform-suite")
    implementation ("net.serenity-bdd:serenity-core:${serenityVersion}")
    implementation ("net.serenity-bdd:serenity-model:${serenityVersion}")
    implementation ("net.serenity-bdd:serenity-cucumber:${serenityVersion}")
    implementation ("net.serenity-bdd:serenity-junit5:${serenityVersion}")
    implementation ("io.cucumber:cucumber-java:latest.release")
    implementation ("io.cucumber:cucumber-junit-platform-engine:latest.release")
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("shadow")
        mergeServiceFiles()
        manifest {
            attributes(
                    "Main-Class" to "my.junit5.cucumber.main.Main"
            )
        }
    }
}

tasks {
    build {
        dependsOn(shadowJar)
    }
}

tasks.clean {
    if (File("target").exists()) {File("target").deleteRecursively()}
}

