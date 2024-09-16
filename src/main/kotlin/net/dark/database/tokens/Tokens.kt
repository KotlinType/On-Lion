package net.dark.database.tokens

import net.dark.controllers.user.model.User
import net.dark.dtos.TokenDTO
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object Tokens : Table() {
    private val userId = long("userId")
    private val refreshToken = varchar("refreshToken", 250)

    fun insert(tokenDTO: TokenDTO) {
        transaction {
            Tokens.insert {
                it[userId] = tokenDTO.userId
                it[refreshToken] = tokenDTO.refreshToken
            }
        }
    }

    fun fetchTokenByRefreshToken(refreshToken: String) = transaction {
        Tokens.selectAll().where { Tokens.refreshToken.eq(refreshToken) }
            .singleOrNull()?.mapToTokenDTO()
    }

    fun update(tokenDTO: TokenDTO) {
        transaction {
            Tokens.update({userId eq tokenDTO.userId}) {
                it[refreshToken] = tokenDTO.refreshToken
            }
        }
    }

    fun delete(refreshToken: String) = transaction {
        Tokens.deleteWhere { Tokens.refreshToken.eq(refreshToken) }
    }

    fun check(userId: Long): Boolean = transaction {
        selectAll().where { Tokens.userId eq userId }.any()
    }

    private fun ResultRow.mapToTokenDTO() = TokenDTO(
        userId = this[userId],
        refreshToken = this[refreshToken]
    )
}
