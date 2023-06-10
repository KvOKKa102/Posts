import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class WallServiceTest {

    @Before
    fun clearBeforeTest() {

        WallService.clear()
    }

    @Test
    fun addPost() {

        val postBeforeAdd1: Posts = Posts(ownerId = 1)
        val postBeforeAdd2: Posts = Posts(ownerId = 2)
        val postBeforeAdd3: Posts = Posts(ownerId = 3)

        val post1: Posts = WallService.add(postBeforeAdd1)
        val post2: Posts = WallService.add(postBeforeAdd2)
        val post3: Posts = WallService.add(postBeforeAdd3)

        assertEquals(1, post1.id)
        assertEquals(2, post2.id)
        assertEquals(3, post3.id)
    }

    @Test
    fun updateExistingFalse() {
        val postBeforeAdd1: Posts = Posts(ownerId = 1)
        val postBeforeAdd2: Posts = Posts(ownerId = 2)
        val postBeforeAdd3: Posts = Posts(ownerId = 3)

        val post1: Posts = WallService.add(postBeforeAdd1)
        val post2: Posts = WallService.add(postBeforeAdd2)
        val post3: Posts = WallService.add(postBeforeAdd3)

        val postUpdate: Posts = Posts(id = 4, ownerId = 4, text = "Update Record")
        val isUpdate = WallService.update(postUpdate)
        assertFalse(isUpdate)
    }

    @Test
    fun updateExistingTrue() {
        val postBeforeAdd: Posts = Posts(ownerId = 1)
        val postBeforeUpdate: Posts = WallService.add(postBeforeAdd)
        postBeforeAdd.text = "Update Record"
        val isUpdate: Boolean = WallService.update(postBeforeAdd)

        println(isUpdate)
        assertTrue(isUpdate)
    }
}