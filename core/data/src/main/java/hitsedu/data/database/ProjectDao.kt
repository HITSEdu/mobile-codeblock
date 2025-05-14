package hitsedu.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProjectDao {

//    @Query("SELECT * FROM projects")
    suspend fun get()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert()

    @Delete
    suspend fun delete()

    @Update
    suspend fun update()
}