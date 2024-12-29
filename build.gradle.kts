plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    id("org.jetbrains.intellij.platform") version "2.2.0"
}

group = "com.lifecosys"
version = "1.0-SNAPSHOT"


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

tasks {

    compileKotlin {
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
