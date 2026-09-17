package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.model.CurriculumSubjects
import com.example.data.model.Grade
import com.example.data.repository.CurriculumData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Timor EduLibrary", appName)
  }

  @Test
  fun `verify 10 official curriculum subjects exist`() {
    val subjects = CurriculumSubjects.subjects
    assertEquals(10, subjects.size)
    assertTrue(subjects.any { it.id == "tetum" })
    assertTrue(subjects.any { it.id == "portugues" })
    assertTrue(subjects.any { it.id == "matematica" })
    assertTrue(subjects.any { it.id == "ciencias_naturais" })
    assertTrue(subjects.any { it.id == "educacao_civica" })
  }

  @Test
  fun `verify books exist for all 3 grades in Ensino Basico 3 Ciclo`() {
    val grade7Books = CurriculumData.getBooksForGrade(Grade.GRADE_7)
    val grade8Books = CurriculumData.getBooksForGrade(Grade.GRADE_8)
    val grade9Books = CurriculumData.getBooksForGrade(Grade.GRADE_9)

    assertTrue(grade7Books.isNotEmpty())
    assertTrue(grade8Books.isNotEmpty())
    assertTrue(grade9Books.isNotEmpty())
    assertEquals(10, grade7Books.size)
  }
}

