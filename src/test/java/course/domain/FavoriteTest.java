package course.domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by admin on 09.05.2017.
 */
public class FavoriteTest {
    @Test
    public void setId() throws Exception {
        Favorite favorite = new Favorite();
        favorite.setId(1L);
        assertEquals(favorite.getId(), 1L);
    }

    @Test
    public void setUserId() throws Exception {
        Favorite favorite = new Favorite();
        favorite.setUserId(1L);
        assertEquals(favorite.getUserId(), 1L);
    }

    @Test
    public void setSiteId() throws Exception {
        Favorite favorite = new Favorite();
        favorite.setSiteId(1L);
        assertEquals(favorite.getSiteId(), 1L);
    }
}