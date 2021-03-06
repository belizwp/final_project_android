package kmitl.afinal.nakarin58070064.wallsplash.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import kmitl.afinal.nakarin58070064.wallsplash.model.MyCollection;

@Dao
public interface MyCollectionDao {

    @Query("SELECT * FROM MyCollection")
    List<MyCollection> getAll();

    @Insert
    long insert(MyCollection myCollection);

    @Update
    void update(MyCollection myCollection);

    @Delete
    void delete(MyCollection myCollection);

    @Query("SELECT MyCollection.`id`, MyCollection.`title`, MyCollection.`desc`, imageSmall AS cover, COUNT(MyPhoto.id) AS photos " +
            "FROM MyCollection " +
            "LEFT JOIN MyPhoto " +
            "ON MyCollection.id = MyPhoto.current_collection " +
            "GROUP BY MyCollection.id")
    List<MyCollection> getAllWithCover();

    @Query("DELETE FROM MyCollection")
    void nukeTable();
}
