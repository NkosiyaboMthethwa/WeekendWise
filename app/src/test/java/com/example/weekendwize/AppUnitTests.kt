package com.example.weekendwize

import org.junit.Assert.*
import org.junit.Test

class AppUnitTests {

    @Test
    fun testPasswordHashingAndVerification() {
        val plainPassword = "MySecurePassword123!"
        val hashedPassword = SecurityUtils.hashPassword(plainPassword)

        assertNotNull(hashedPassword)
        assertNotEquals(plainPassword, hashedPassword)
        assertTrue(SecurityUtils.verifyPassword(plainPassword, hashedPassword))
        assertFalse(SecurityUtils.verifyPassword("WrongPassword", hashedPassword))
    }

    @Test
    fun testDataRepositorySubjectCompleteness() {
        val subjects = DataRepository.subjects
        assertTrue("Subjects list should not be empty", subjects.isNotEmpty())
        assertTrue("Should have at least 6 subjects", subjects.size >= 6)

        subjects.forEach { subject ->
            assertNotNull("Subject name should not be null", subject.name)
            assertTrue("Subject ${subject.name} should have topics", subject.topics.isNotEmpty())

            subject.topics.forEach { topic ->
                assertNotNull("Topic name should not be null", topic.name)
                assertTrue("Topic ${topic.name} in ${subject.name} should have subtopics", topic.subTopics.isNotEmpty())

                topic.subTopics.forEach { subTopic ->
                    assertNotNull("SubTopic name should not be null", subTopic.name)
                    assertTrue("SubTopic ${subTopic.name} should have quiz questions", subTopic.quiz.questions.isNotEmpty())
                    assertTrue("SubTopic ${subTopic.name} theory should not be empty", subTopic.theory.isNotEmpty())
                    assertTrue("SubTopic ${subTopic.name} notes should not be empty", subTopic.notes.isNotEmpty())
                }
            }
        }
    }

    @Test
    fun testSubjectListContainsRequiredSubjects() {
        val subjectNames = DataRepository.subjects.map { it.name }
        assertTrue(subjectNames.contains("Mathematics"))
        assertTrue(subjectNames.contains("Physical Sciences"))
        assertTrue(subjectNames.contains("Life Sciences"))
        assertTrue(subjectNames.contains("Accounting"))
        assertTrue(subjectNames.contains("Business Studies"))
        assertTrue(subjectNames.contains("Geography"))
    }
}
