import java.time.LocalDate

data class Posts(
    var id: Int? = null,
    val ownerId: Int,
    val fromId: Int? = null,
    val createdBy: Int? = null,
    val date: LocalDate = LocalDate.now(),
    var text: String? = null,
    val replyOwnerId: Int? = null,
    val replyPostId: Int? = null,
    val friendsOnly: Boolean = false,
    val comments: Comments = Comments(),
    val likes: Likes = Likes(),
    val reposts: Reposts = Reposts(),
    val views: Views? = null,
    val postType: PostType = PostType.REPLY,
    val canPin: Boolean = false,
    val canDelete: Boolean = true,
    val canEdit: Boolean = true,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val postponedId: Int? = null
) {
    enum class PostType {
        POST, COPY, REPLY, POSTPONE, SUGGEST
    }

    data class Comments(
        val count: Int = 0,
        val canPost: Boolean = true,
        val groupsCanPost: Boolean? = null,
        val canClose: Boolean = true,
        val canOpen: Boolean = true
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

    data class Views(
        val count: Int? = null
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