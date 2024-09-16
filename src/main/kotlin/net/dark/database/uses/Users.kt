package net.dark.database.uses

import net.dark.controllers.user.model.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object Users : Table("users") {
    private val id = Users.long("id").autoIncrement()
    private val email = Users.varchar("email", 35)
    private val password = Users.varchar("password", 80)
    private val isActivated = Users.bool("isActivated")
    private val activationLink = Users.varchar("activationLink", 150)

    fun insert(user: User) = transaction {
        Users.insert {
            it[email] = user.email
            it[password] = user.password
            it[isActivated] = user.isActivated
            it[activationLink] = user.activationLink
        }[Users.id]
    }

    fun fetchUserById(userId: Long) = transaction {
        Users.selectAll().where { Users.id.eq(userId) }.singleOrNull()?.mapToUser()
    }

    fun fetchUserByLogin(email: String) = transaction {
        Users.selectAll().where { Users.email.eq(email) }.singleOrNull()?.mapToUser()
    }

    fun fetchUserByActivationLink(activationLink: String) = transaction {
        Users.selectAll().where { Users.activationLink.eq(activationLink) }
            .singleOrNull()?.mapToUser()
    }

    fun update(user: User) = transaction {
        Users.update({ Users.id eq user.id!! }) {
            it[email] = user.email
            it[password] = user.password
            it[isActivated] = user.isActivated
            it[activationLink] = user.activationLink
        }
    }

    private fun ResultRow.mapToUser() = User(
        id = this[id],
        email = this[email],
        password = this[password],
        isActivated = this[isActivated],
        activationLink = this[activationLink],
    )
}
