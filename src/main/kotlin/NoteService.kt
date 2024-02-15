object NoteService {

    private var notes = mutableListOf<Note>()
    private var noteId = 0

    private var deletedComments = mutableListOf<Comment>()
    private var commentId = 0

    fun add(
        title: String,
        text: String,
        privacy: Int,
        commentPrivacy: Int
    ): Int {

        val note = Note(title, text, privacy, commentPrivacy, ++noteId)
        notes += note
        return noteId
    }

    fun delete(noteId: Int): Int {

        for ((index, note) in notes.withIndex()) {
            if (note.noteId == noteId) {

                notes.removeAt(index)
                return 1
            }
        }
        throw NoteNotFoundException("Note with id $noteId not found")
    }

    fun edit(
        noteId: Int,
        title: String,
        text: String,
        privacy: Int,
        commentPrivacy: Int
    ): Int {

        for ((index, note) in notes.withIndex()) {
            if (note.noteId == noteId) {

                notes[index] = note.copy(
                    title = title,
                    text = text,
                    privacy = privacy,
                    commentPrivacy = commentPrivacy
                )
                return 1
            }
        }
        throw NoteNotFoundException("Note with id $noteId not found")
    }

    fun createComment(
        noteId: Int,
        message: String
    ): Int {

        for (note in notes) {
            if (note.noteId == noteId) {

                note.comments += Comment(noteId, ++commentId, message)
                return commentId
            }
        }
        throw NoteNotFoundException("Note with id $noteId not found")
    }

    fun deleteComment(
        noteId: Int,
        commentId: Int
    ): Int {

        for (note in notes) {
            if (note.noteId == noteId) {

                for ((index, comment) in note.comments.withIndex()) {
                    if (comment.commentId == commentId) {

                        deletedComments += comment
                        note.comments.removeAt(index)
                        return 1
                    }
                }
                throw CommentNotFoundException("Comment with id $commentId not found")
            }
        }
        throw NoteNotFoundException("Note with id $noteId not found")
    }

    fun editComment(
        noteId: Int,
        commentId: Int,
        message: String
    ): Int {

        for (note in notes) {
            if (note.noteId == noteId) {

                for ((index, comment) in note.comments.withIndex()) {
                    if (comment.commentId == commentId) {

                        note.comments[index] = comment.copy(message = message)
                        return 1
                    }
                }
                throw CommentNotFoundException("Comment with id $commentId not found")
            }
        }
        throw NoteNotFoundException("Note with id $noteId not found")
    }

    fun getById(noteId: Int): Note {

        for (note in notes) {
            if (note.noteId == noteId) {
                return note
            }
        }
        throw NoteNotFoundException("Note with id $noteId not found")
    }

    fun get(
        vararg noteId: Int
    ): List<Note> {

        val result = mutableListOf<Note>()

        for (id in noteId) {
            result += getById(id)
        }

        return result
    }

    private fun getCommentById(
        noteId: Int,
        commentId: Int
    ): Comment {

        for (note in notes) {
            if (note.noteId == noteId) {

                for (comment in note.comments) {
                    if (comment.commentId == commentId) {
                        return comment
                    }
                }
            }
        }
        throw CommentNotFoundException("Comment with id $commentId not found")
    }

    fun getComments(
        noteId: Int,
        vararg commentId: Int
    ): List<Comment> {

        val result = mutableListOf<Comment>()

        for (id in commentId) {
            result += getCommentById(noteId, id)
        }

        return result
    }

    fun restoreComment(commentId: Int): Int {

        for (comment in deletedComments) {
            if (comment.commentId == commentId) {

                for (note in notes) {
                    if (note.noteId == comment.noteId) {

                        note.comments += comment
                        deletedComments.remove(comment)
                        return 1
                    }
                }
            }
        }
        throw CommentNotFoundException("Comment with id $commentId not found")
    }

    fun clear() {
        notes = mutableListOf()
        noteId = 0
        deletedComments = mutableListOf()
        commentId = 0
    }
}