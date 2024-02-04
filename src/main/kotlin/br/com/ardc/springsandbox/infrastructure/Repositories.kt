package br.com.ardc.springsandbox.infrastructure

import br.com.ardc.springsandbox.domain.User

/**
 * A generic repository interface for saving, deleting, finding and listing entities.
 */
interface Repository<T> {
    fun save(entity: T)
    fun delete(entity: T)
    fun findById(id: String): T?
    fun findAll(): List<T>
}

/**
 * An in-memory implementation of the User repository.
 */
@org.springframework.stereotype.Repository
class InMemoryUserRepository : Repository<User> {
    private val users = mutableListOf<User>()

    override fun save(entity: User) {
        users.add(entity)
    }

    override fun delete(entity: User) {
        users.remove(entity)
    }

    override fun findById(id: String): User? {
        return users.find { it.userName == id }
    }

    override fun findAll(): List<User> {
        return users.toList()
    }
}