package com.example.perfect_pitch_trainer.database.model

import android.arch.persistence.room.*
import android.support.annotation.NonNull

//@Entity(tableName = "masterClass")
@Entity(tableName = "masterClass" )
data class MasterClass(
   //@PrimaryKey(autoGenerate = true)
    //@Ignore
    //var id: Int? = 0,
    @NonNull
    var name: String? = "",
    @ColumnInfo(name = "solved")
    var solved: String? = "")
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0 // or foodId: Int? = null
}

//@Embedded
   // val routine: Routine)