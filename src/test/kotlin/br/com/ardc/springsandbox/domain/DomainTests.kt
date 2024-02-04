package br.com.ardc.springsandbox.domain

import org.assertj.core.api.Assertions.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DomainTests {
    private val expectedUsername = "ralves"
    private val expectedDisplayName = "Rodolpho Alves"
    private val expectedUserJson = """{"userName":"$expectedUsername","displayName":"$expectedDisplayName"}"""
    private val expectedMessageContent = "I like turtles"
    private val expectedUserObject = User(expectedUsername, expectedDisplayName)
    private val expectedMessageReadStatus = false
    private val expectedMessageJson = """{"sender":$expectedUserJson,"recipient":$expectedUserJson,"content":"$expectedMessageContent","isRead":$expectedMessageReadStatus}"""
    private val expectedMessageObject = Message(expectedUserObject, expectedUserObject, expectedMessageContent, expectedMessageReadStatus)

    @Nested
    inner class UserTests {
        @Test
        fun `Given a valid JSON, When serialized, Then an User instance is retrieved`(): Unit {
            // Arrange
            val objectMapper = ObjectMapper().registerModules(KotlinModule.Builder().build())

            // Act
            val got = objectMapper.readValue(expectedUserJson, User::class.java)

            // Assert
            assertThat(got).isEqualTo(expectedUserObject)
        }

        @Test
        fun `Given a valid Object, When serialized, Then a JSON is retrieved`(): Unit {
            // arrange
            val objectMapper = ObjectMapper().registerModules(KotlinModule.Builder().build())

            // act
            val got = objectMapper.writeValueAsString(expectedUserObject)

            // assert
            assertThat(got).isEqualTo(expectedUserJson)
        }
    }

    @Nested
    inner class MessageTests {
        @Test
        fun `Given a valid JSON, When Serialized, Then a Message instance is recovered`(): Unit {
            // arrange
            val objectMapper = ObjectMapper().registerModules(KotlinModule.Builder().build())

            // act
            val got = objectMapper.readValue(expectedMessageJson, Message::class.java)

            // assert
            assertThat(got).isEqualTo(expectedMessageObject)
        }

        @Test
        fun `Given a valid Instance, When Serialized, Then a JSON is returned`(): Unit {
            // arrange
            val objectMapper = ObjectMapper().registerModules(KotlinModule.Builder().build())

            // act
            val got = objectMapper.writeValueAsString(expectedMessageObject)

            // assert
            assertThat(got).isEqualTo(expectedMessageJson)
        }
    }

    @Nested
    inner class UseCasesTests {
        @Test
        fun `Given an User, When creating a new message, A new message should be created with the appropriate sender`(): Unit {
            // arrange
            val sender = User("ralves", "Rodolpho Alves")
            val recipient = User("fulano", "Full Lano")
            val content = "I like turtles"

            // act
            val got = sender.createMessageTo(recipient, content)

            // assert
            assertThat(got.sender).isEqualTo(sender)
            assertThat(got.recipient).isEqualTo(recipient)
            assertThat(got.content).isEqualTo(content)
            assertThat(got.isRead).isFalse()
        }
    }
}