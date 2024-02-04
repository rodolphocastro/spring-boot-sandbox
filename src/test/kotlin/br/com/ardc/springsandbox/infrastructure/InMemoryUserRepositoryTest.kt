package br.com.ardc.springsandbox.infrastructure

import br.com.ardc.springsandbox.domain.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InMemoryUserRepositoryTest {

    @Test
    fun `when the repository is empty, findAll should return an empty list`(): Unit {
        val repository = InMemoryUserRepository()
        val result = repository.findAll()
        assertThat(result).isEmpty()
    }

    @Test
    fun `when a user is saved, it should be returned by findAll`(): Unit {
        val repository = InMemoryUserRepository()
        val user = User("user", "User")
        repository.save(user)
        val result = repository.findAll()
        assertThat(result).containsExactly(user)
    }

    @Test
    fun `when a user is saved, it should be returned by findById`(): Unit {
        val repository = InMemoryUserRepository()
        val user = User("user", "User")
        repository.save(user)
        val result = repository.findById("user")
        assertThat(result).isEqualTo(user)
    }

    @Test
    fun `when a user is deleted, it should not be returned by findAll`(): Unit {
        val repository = InMemoryUserRepository()
        val user = User("user", "User")
        repository.save(user)
        repository.delete(user)
        val result = repository.findAll()
        assertThat(result).isEmpty()
    }
}