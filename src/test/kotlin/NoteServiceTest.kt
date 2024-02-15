import org.junit.Test
import org.junit.Before
import kotlin.test.assertEquals

class NoteServiceTest {

    @Before
    fun clearBeforeTest() {
        NoteService.clear()
    }

    @Test
    fun addTest() {

        val result = NoteService.add(
            "Новая заметка",
            "Пусто",
            0,
            0
        )

        assertEquals(1, result)
    }

    @Test
    fun deleteTest() {

        NoteService.add(
            "Новая заметка",
            "Пусто",
            0,
            0
        )

        val result = NoteService.delete(1)

        assertEquals(1, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteExceptionTest() {

        NoteService.add(
            "Новая заметка",
            "Пусто",
            0,
            0
        )

        NoteService.delete(2)
    }

    @Test
    fun editTest() {

        NoteService.add(
            "Новая заметка",
            "Пусто",
            0,
            0
        )

        val result = NoteService.edit(
            1,
            "Старая заметка",
            "Пусто",
            0,
            0
        )

        assertEquals(1, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun editExceptionTest() {

        NoteService.add(
            "Новая заметка",
            "Пусто",
            0,
            0
        )

        val result = NoteService.edit(
            2,
            "Старая заметка",
            "Пусто",
            0,
            0
        )
    }

    @Test
    fun getByIdTest() {

        val note = Note(
            "Новая заметка",
            "Пусто",
            0,
            0,
            1
        )

        NoteService.add(
            "Новая заметка",
            "Пусто",
            0,
            0
        )

        val result = NoteService.getById(1)

        assertEquals(note, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getByIdExceptionTest() {

        val note = Note(
            "Новая заметка",
            "Пусто",
            0,
            0,
            1
        )

        NoteService.add(
            "Новая заметка",
            "Пусто",
            0,
            0
        )

        NoteService.getById(2)
    }

    @Test
    fun getTest() {

        NoteService.add(
            "Новая заметка",
            "Пусто",
            0,
            0
        )
        NoteService.add(
            "Новая заметка2",
            "Пусто",
            0,
            0
        )

        val notes = listOf(
            Note(
                "Новая заметка",
                "Пусто",
                0,
                0,
                1
            ),
            Note(
                "Новая заметка2",
                "Пусто",
                0,
                0,
                2
            )
        )

        val result = NoteService.get(1, 2)

        assertEquals(notes, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getExceptionTest() {
        NoteService.get(9999)
    }



    @Test
    fun createCommentTest() {

        NoteService.add(
            "Новая заметка",
            "Пусто",
            0,
            0
        )

        val result = NoteService.createComment(
            1,
            "Новый комментарий"
        )

        assertEquals(1, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun createCommentExceptionTest() {

        NoteService.createComment(
            9999,
            "Новый комментарий"
        )
    }

    @Test
    fun deleteCommentTest() {

        NoteService.add(
            "Новая заметка",
            "Пусто",
            0,
            0
        )

        NoteService.createComment(
            1,
            "Новый коментарий"
        )

        val result = NoteService.deleteComment(
            1,
            1
        )

        assertEquals(1, result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteComment_CommentNotFoundExceptionTest() {

        NoteService.add(
            "Новая заметка",
            "Пусто",
            0,
            0
        )

        NoteService.createComment(
            1,
            "Новый коментарий"
        )

        NoteService.deleteComment(
            1,
            9999
        )
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteComment_NoteNotFoundExceptionTest() {

        NoteService.add(
            "Новая заметка",
            "Пусто",
            0,
            0
        )

        NoteService.createComment(
            1,
            "Новый коментарий"
        )

        NoteService.deleteComment(
            9999,
            1
        )
    }

    @Test
    fun editCommentTest() {

        NoteService.add(
            "Новая заметка",
            "Пусто",
            0,
            0
        )

        NoteService.createComment(
            1,
            "Новый коментарий"
        )

        val result = NoteService.editComment(
            1,
            1,
            "Старый комментарий"
        )

        assertEquals(1, result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun editComment_CommentNotFoundExceptionTest() {

        NoteService.add(
            "Новая заметка",
            "Пусто",
            0,
            0
        )

        NoteService.createComment(
            1,
            "Новый коментарий"
        )

        NoteService.editComment(
            1,
            2,
            "Старый комментарий"
        )
    }

    @Test(expected = NoteNotFoundException::class)
    fun editComment_NoteNotFoundExceptionTest() {

        NoteService.add(
            "Новая заметка",
            "Пусто",
            0,
            0
        )

        NoteService.createComment(
            1,
            "Новый коментарий"
        )

        NoteService.editComment(
            2,
            1,
            "Старый комментарий"
        )
    }

    @Test
    fun getCommentsTest() {

        NoteService.add(
            "Новая заметка",
            "Пусто",
            0,
            0
        )

        NoteService.createComment(
            1,
            "Новый коментарий"
        )

        NoteService.createComment(
            1,
            "Новый комментарий2"
        )

        val comments = listOf(
            Comment(
                1,
                1,
                "Новый коментарий"
            ),
            Comment(
                1,
                2,
                "Новый комментарий2"
            )
        )

        val result = NoteService.getComments(1, 1, 2)

        assertEquals(comments, result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun getCommentsExceptionTest() {

        NoteService.add(
            "Новая заметка",
            "Пусто",
            0,
            0
        )

        NoteService.getComments(1,2)
    }

    @Test
    fun restoreCommentTest() {

        NoteService.add(
            "Новая заметка",
            "Пусто",
            0,
            0
        )

        NoteService.createComment(
            1,
            "Новый коментарий"
        )

        NoteService.deleteComment(1, 1)

        val result = NoteService.restoreComment(1)

        assertEquals(1, result)
    }

    @Test(expected = CommentNotFoundException::class)
    fun restoreCommentExceptionTest() {

        NoteService.add(
            "Новая заметка",
            "Пусто",
            0,
            0
        )

        NoteService.createComment(
            1,
            "Новый коментарий"
        )

        NoteService.deleteComment(1, 1)

        NoteService.restoreComment(9999)
    }

}