package org.simple.clinic.user

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import org.simple.clinic.util.RoomEnumTypeConverter
import org.threeten.bp.Instant
import java.util.UUID

// We intentionally have to keep the table name same because
// Room starts complaining if you try to rename a table which
// is referenced by another table in a foreign key (technically,
// SQLite supports renaming tables, but Room complains).
@Entity(tableName = "LoggedInUser")
data class User(

    @PrimaryKey
    val uuid: UUID,

    val fullName: String,

    val phoneNumber: String,

    val pinDigest: String,

    val status: UserStatus,

    val createdAt: Instant,

    val updatedAt: Instant,

    val loggedInStatus: LoggedInStatus
) {

  enum class LoggedInStatus {
    /**
     * Phone number match happened on the server,
     * and information was stored locally, but the
     * OTP request has not yet been made.
     **/
    NOT_LOGGED_IN,

    /**
     * Login OTP request has been raised with the server.
     **/
    OTP_REQUESTED,

    /**
     * Login OTP was received, and a call to validate
     * the OTP with the server has been scheduled.
     */
    VERIFYING_OTP,

    /**
     * Login OTP has been validated with the server
     * and the user is verified.
     */
    LOGGED_IN;

    class RoomTypeConverter : RoomEnumTypeConverter<LoggedInStatus>(LoggedInStatus::class.java)
  }

  @Dao
  interface RoomDao {

    @Query("SELECT * FROM LoggedInUser LIMIT 1")
    fun user(): Flowable<List<User>>

    @Query("SELECT * FROM LoggedInUser LIMIT 1")
    fun userImmediate(): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createOrUpdate(user: User)
  }

  fun isApprovedForSyncing(): Boolean {
    return loggedInStatus == LoggedInStatus.LOGGED_IN && status == UserStatus.APPROVED_FOR_SYNCING
  }
}