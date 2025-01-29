plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.25"
    id("org.jetbrains.intellij") version "1.17.4"
}

group = "com.lifecosys"
version = "0.0.2"


repositories {
    mavenLocal()
    maven {
        url = uri("https://maven.aliyun.com/repository/public")
    }
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
//    version.set("2024.1.7")
    type.set("IC") // Target IDE Platform
    localPath.set("/develop/tools/jetbrain/idea-ic-testing")
//    plugins.set(listOf(/* Plugin Dependencies */))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "21"
    }

    patchPluginXml {
        sinceBuild.set("241")
        untilBuild.set("243.23654.117")
    }

    signPlugin {
        certificateChainFile.set(file("/develop/sourcecode/file-truck/sign/chain.crt"))
        privateKeyFile.set(file("/develop/sourcecode/file-truck/sign/private.pem"))
        password.set("TODO")
    }

    publishPlugin {
        token.set("TODO")
    }
}
