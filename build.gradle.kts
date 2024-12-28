plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    id("org.jetbrains.intellij.platform") version "2.2.0"
}

group = "com.lifecosys"
version = "0.0.1"


repositories {
    mavenLocal()
    maven {
        url = uri("https://maven.aliyun.com/repository/public")
    }
    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    intellijPlatform {
        intellijIdeaCommunity("2024.3.1")
    }
}


java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

kotlin {
    jvmToolchain(21)
}

intellijPlatform{
    projectName="File Truck"

    pluginConfiguration{
        ideaVersion{
            sinceBuild = "2024.2.1"
            untilBuild = "2025.3.1"
        }
    }

    signing{
        certificateChainFile.set(file("/develop/sourcecode/file-truck/sign/chain.crt"))
        privateKeyFile.set(file("/develop/sourcecode/file-truck/sign/private.pem"))
        password.set("TODO")
    }

    publishing {
        token.set("TODO")
    }
}
