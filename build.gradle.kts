plugins {
  kotlin("jvm") version "2.1.20"
  id("com.github.johnrengelman.shadow") version "8.1.1"
}

kotlin {
  jvmToolchain(17)
}

group = "me.xentany"
version = "1.0.0"

repositories {
  mavenCentral()
  maven("https://repo.papermc.io/repository/maven-public/")
  maven("https://oss.sonatype.org/content/groups/public/")
  maven("https://repo.phoenix616.dev/")
}

dependencies {
  compileOnly("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
  compileOnly("org.yaml:snakeyaml:2.4")
  compileOnly("com.google.code.gson:gson:2.13.0")
  compileOnly("com.google.guava:guava:33.4.7-jre")
  compileOnly("net.kyori:adventure-text-minimessage:4.20.0")
  compileOnly("net.kyori:adventure-text-serializer-plain:4.20.0")
  compileOnly("net.kyori:adventure-text-serializer-gson:4.20.0")
  compileOnly("net.kyori:adventure-text-serializer-legacy:4.20.0")
  compileOnly("net.kyori:adventure-text-serializer-json:4.20.0")
  compileOnly("net.kyori:adventure-api:4.20.0")
  compileOnly("net.luckperms:api:5.4")
  implementation("net.kyori:adventure-text-minimessage:4.1.0-SNAPSHOT")
  testImplementation("junit:junit:4.13.1")
  testImplementation(platform("org.junit:junit-bom:5.10.0"))
  testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
  useJUnitPlatform()
}

tasks.shadowJar {
  exclude("META-INF/MANIFEST.MF")
  exclude("org/intellij/lang/annotations/**")
  exclude("jetbrains/annotations/**")
  exclude("org/jetbrains/annotations/**")
}

tasks.processResources {
  duplicatesStrategy = DuplicatesStrategy.EXCLUDE
  filteringCharset = "UTF-8"

  val properties = project.properties.filterValues {
    it != null
  }

  filesMatching(listOf("**/*.yml")) {
    expand(properties)
  }
}