data class Note(
    var title: String = "",
    var text: String = "",
    var privacy: Int = 0,
    var commentPrivacy: Int,
    var noteId: Int,
    var comments: MutableList<Comment> = mutableListOf()
)