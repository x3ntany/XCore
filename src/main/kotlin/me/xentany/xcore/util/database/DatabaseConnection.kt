package me.xentany.xcore.util.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection

class DatabaseConnection(config: DatabaseConfig) {

  private val dataSource: HikariDataSource

  init {
    val hikariConfig = HikariConfig().apply {
      this.jdbcUrl = config.jdbcUrl
      this.username = config.username
      this.password = config.password
      this.maximumPoolSize = config.maximumPoolSize
      this.minimumIdle = config.minimumIdle
      this.poolName = config.poolName

      for ((key, value) in config.dataSourceProperties) {
        this.addDataSourceProperty(key, value)
      }
    }

    this.dataSource = HikariDataSource(hikariConfig)
  }

  fun getConnection(): Connection = this.dataSource.connection
  fun close() = this.dataSource.close()
}