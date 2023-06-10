import java.time.LocalDate

data class Posts(
    var id: Int = 0,
    val ownerId: Int,
    val date: LocalDate = LocalDate.now(),
    var text: String = "contains nothing",
    val friendsOnly: Boolean = false,
    var comments: Comments = Comments(),
    val likes: Likes = Likes(),
    val reposts: Reposts = Reposts(),
    val views: Int = 0,
    val postType: PostType = PostType.REPLY,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val isFavorite: Boolean = false
) {
    enum class PostType {
        POST, COPY, REPLY, POSTPONE, SUGGEST
    }

    class Comments(
        count: Int = 0,
        canPost: Boolean = true,
        canClose: Boolean = true,
        canOpen: Boolean = true
    )

    class Likes(
        val count: Int = 0,
        val userLikes: Boolean = true,
        val canLike: Boolean = true,
        val canPublish: Boolean = true
    )

    class Reposts(
        val count: Int = 0,
        val userReposted: Boolean = false
    )
}

object WallService {
    private var posts = emptyArray<Posts>()
    private var uniqId: Int = 0
    fun add(post: Posts): Posts {
        uniqId++
        post.id = uniqId
        post.text = "Запись #$uniqId"
        posts += post
        return posts.last()
    }

    fun update(post: Posts): Boolean {
        var update: Boolean = false
        for (oldPost in posts) {
            if (oldPost.id == post.id) {
                posts[oldPost.id - 1] = post.copy()
                update = true
            }
        }
        return update
    }

    fun clear() {
        posts = emptyArray()
        uniqId = 0
    }
}