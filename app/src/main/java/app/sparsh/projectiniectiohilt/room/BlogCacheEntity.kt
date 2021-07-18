package app.sparsh.projectiniectiohilt.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blogs")
data class BlogCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "body")
    val body: String = "",
    @ColumnInfo(name = "image")
    val image: String = "",
    @ColumnInfo(name = "category")
    val category: String = "",
)
