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
        val newPost = post.copy()
        newPost.id = uniqId
        newPost.text = "Запись #$uniqId"
        posts += newPost
        return newPost
    }

    fun update(post: Posts): Boolean {
        for (index in posts.indices) {
            if (posts[index].id == post.id) {
                posts[index] = post.copy()
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        uniqId = 0
    }
}