pluginManagement {

  this.plugins {
    this.kotlin("jvm") version "2.1.20"
  }
}

plugins {
  this.id("org.gradle.toolchains.foojay-resolver-convention") version "0.10.0"
}

rootProject.name = "xcore"