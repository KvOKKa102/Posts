import java.time.LocalDate

data class Posts<T>(
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
    val postponedId: Int? = null,
    val attachments: Array<Attachments> = emptyArray()
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

interface Attachments {
    val type: String
}

data class Video(
    override val type: String = "video",
    val id: Int,
    val ownerId: Int,
    val title: String,
    val description: String? = null,
    val duration: Int,
    val image: List<Image> = emptyList()
) : Attachments {
    data class Image(
        val url: String,
        val width: Int,
        val height: Int
    )
}

data class Audio(
    override val type: String = "audio",
    val id: Int,
    val ownerId: Int,
    val artist: String,
    val title: String,
    val duration: Int,
    val url: String? = null
) : Attachments

data class Document(
    override val type: String = "doc",
    val id: Int,
    val ownerId: Int,
    val title: String,
    val size: Int,
    val url: String? = null
) : Attachments

data class Link(
    override val type: String = "link",
    val url: String,
    val title: String,
    val caption: String? = null
) : Attachments

data class Album(
    override val type: String = "albumAttachments",
    val id: Int,
    val thumb: Photo,
    val ownerId: Int,
    val title: String,
    val description: String? = null,
    val created: Long,
    val updated: Long,
    val size: Int
) : Attachments {
    data class Photo(
        val id: Int,
        val albumId: Int,
        val ownerId: Int,
        val userId: Int? = null,
        val text: String? = null,
        val date: Long? = null,
        val sizes: Array<Size> = emptyArray(),
        val width: Int? = null,
        val height: Int? = null
    ) {
        data class Size(
            val type: String,
            val url: String,
            val width: Int,
            val height: Int
        )
    }
}


object WallService {
    private var posts = emptyArray<Posts<Any?>>()
    private var uniqId: Int = 0
    fun add(post: Posts<Any?>): Posts<Any?> {
        uniqId++
        val newPost = post.copy()
        newPost.id = uniqId
        newPost.text = "Запись #$uniqId"
        posts += newPost
        return newPost
    }

    fun update(post: Posts<Any?>): Boolean {
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