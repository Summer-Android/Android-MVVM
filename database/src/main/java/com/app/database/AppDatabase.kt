package com.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.application.BaseApplication
import com.app.database.dao.UserDao
import com.app.database.entity.UserEntity

// OnConflictStrategy.IGNORE：有旧数据存在，想要插入的数据将会插入失败（默认采用的策略）
// OnConflictStrategy.REPLACE：有旧数据存在，则替换掉旧数据
// OnConflictStrategy.ABORT：有旧数据存在，则终止事务
// OnConflictStrategy.ROLLBACK：有旧数据存在，则回滚事务（已废弃，使用 ABORT 代替）
// OnConflictStrategy.FAIL：有旧数据存在，则提示插入数据失败（已废弃，使用 ABORT 代替）

// SQLite In 最多998个参数，参考：https://www.sqlite.org/limits.html#max_variable_number

@Database(
    entities = [
        UserEntity::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        private const val DB_NAME = "App.db"

        val instance: AppDatabase by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            create(BaseApplication.instance)
        }

        private fun create(context: Context): AppDatabase {
            return Room
                .databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .allowMainThreadQueries()
                // .addMigrations(
                //     MIGRATION_1_2
                // )
                .build()
        }


        // private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        //     override fun migrate(db: SupportSQLiteDatabase) {
        //
        //     }
        // }
    }
}