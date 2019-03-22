
package hr.sil.android.mplhuber.core.remote.service

import com.example.perfect_pitch_trainer.model.RegisterDAO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * @author mfatiga
 */
interface UserAppService {

    /* companion object : RestServiceAccessor<UserAppService>(UserAppService::class) {
        //auth: Basic
        private const val ENDPOINT_PREFIX = "app/service/rest/"
    }

    @POST(ENDPOINT_PREFIX + "device/register")
    fun registerDevice(@Body deviceInfo: RegisterDAO): Call<RegisterDAO>

    @POST(ENDPOINT_PREFIX + "device/register")
    fun registerDevice(@Body deviceInfo: RUserDeviceInfo): Call<RUserDeviceInfo>

    @GET(ENDPOINT_PREFIX + "languages")
    fun getLanguages(): Call<List<RLanguage>>

    @POST(ENDPOINT_PREFIX + "endUser/register")
    fun registerEndUser(@Body endUserInfo: REndUserRegisterRequest): Call<REndUserInfo>

    @GET(ENDPOINT_PREFIX + "endUser/recoverPassword/{email}")
    fun requestPasswordRecovery(@Path("email") email: String): Call<Void>

    @POST(ENDPOINT_PREFIX + "endUser/resetPassword")
    fun resetPassword(@Body resetPasswordRequest: RResetPasswordRequest): Call<Void>

    @POST(ENDPOINT_PREFIX + "endUser/updatePassword")
    fun updatePassword(@Body updatePasswordRequest: RUpdatePasswordRequest): Call<Void>

    @POST(ENDPOINT_PREFIX + "endUser/update")
    fun updateUserProfile(@Body updateUserProfileRequest: RUpdateUserProfileRequest): Call<REndUserInfo>

    @GET(ENDPOINT_PREFIX + "endUser/info")
    fun getUserInfo(): Call<REndUserInfo>

    @GET(ENDPOINT_PREFIX + "group")
    fun getGroupInfo(): Call<REndUserGroupInfo>

    @GET(ENDPOINT_PREFIX + "groupMembers")
    fun getGroupMembers(): Call<List<REndUserGroupMember>>

    @GET(ENDPOINT_PREFIX + "groupMemberships")
    fun getGroupMemberships(): Call<List<RGroupInfo>>


    @GET(ENDPOINT_PREFIX + "groupMembers/{id}")
    fun getGroupMembershipsById(@Path("id") id: Int): Call<MutableList<RGroupInfo>> */



}