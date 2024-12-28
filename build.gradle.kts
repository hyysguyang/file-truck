plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    id("org.jetbrains.intellij") version "1.17.4"
}

group = "com.lifecosys"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    maven {
        url=uri("https://maven.aliyun.com/repository/public")
    }
}

intellij {
    localPath.set("/develop/tools/jetbrain/ideaIC-2024.3.1.1/idea-IC-243.22562.218/")
    //    version.set("2023.2.6")
    //    type.set("IC") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */))
    // 移除最高版本限制
    updateSinceUntilBuild.set(true)
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
        untilBuild.set("243.*")
    }

    signPlugin {
        certificateChainFile.set(file("/develop/sourcecode/file-truck/sign/chain.crt"))
        privateKeyFile.set(file("/develop/sourcecode/file-truck/sign/private.pem"))
        password.set("hyysguyang")
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
