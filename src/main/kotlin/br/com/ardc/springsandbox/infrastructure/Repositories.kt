package br.com.ardc.springsandbox.infrastructure

import br.com.ardc.springsandbox.domain.User
import org.slf4j.LoggerFactory

/**
 * A generic repository interface for saving, deleting, finding and listing entities.
 */
interface Repository<T> {
    /**
     * Saves an entity to the repository.
     * @param entity the entity to be saved.
     */
    fun save(entity: T)

    /**
     * Deletes an entity from the repository.
     * @param entity the entity to be deleted.
     */
    fun delete(entity: T)

    /**
     * Finds an entity in the repository by its id.
     * @param id the id of the entity to be found.
     * @return the entity found or null if not found.
     */
    fun findById(id: String): T?

    /**
     * Lists all entities in the repository.
     * @return a list of all entities in the repository.
     */
    fun findAll(): List<T>
}

/**
 * An in-memory implementation of the User repository.
 */
@org.springframework.stereotype.Repository
class InMemoryUserRepository : Repository<User> {
    companion object {
        /**
         * logger instance for this class
         * */
        private val logger = LoggerFactory.getLogger(InMemoryUserRepository::class.java)
    }

    private val users = mutableListOf<User>()

    override fun save(entity: User) {
        logger.atTrace()
            .addKeyValue("payload", entity)
            .log("Saving user to repository.")

        users.add(entity)

        logger.atTrace()
            .addKeyValue("payload", entity)
            .log("User saved to repository.")
    }

    override fun delete(entity: User) {
        logger.atTrace()
            .addKeyValue("payload", entity)
            .log("Deleting user from repository.")

        users.remove(entity)

        logger.atTrace()
            .addKeyValue("payload", entity)
            .log("User deleted from repository.")
    }

    override fun findById(id: String): User? {
        logger.atTrace()
            .addKeyValue("id", id)
            .log("Finding user in repository.")
        return users.find { it.userName == id }
    }

    override fun findAll(): List<User> {
        logger.atTrace()
            .log("Listing all users in repository.")
        return users.toList()
    }
}