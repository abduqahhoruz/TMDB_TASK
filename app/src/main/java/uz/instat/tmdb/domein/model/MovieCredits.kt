package uz.instat.tmdb.domein.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MovieCredits(
    @SerializedName("cast")
    val cast: List<Cast>
)
{
    @Keep
    data class Cast(
        @SerializedName("adult")
        val adult: Boolean, // false
        @SerializedName("cast_id")
        val castId: Int, // 1
        @SerializedName("character")
        val character: String, // Peter Parker / Spider-Man
        @SerializedName("credit_id")
        val creditId: String, // 5d8e28d38289a0000fcc32f9
        @SerializedName("gender")
        val gender: Int, // 2
        @SerializedName("id")
        val id: Int, // 1136406
        @SerializedName("known_for_department")
        val knownForDepartment: String, // Acting
        @SerializedName("name")
        val name: String, // Tom Holland
        @SerializedName("order")
        val order: Int, // 0
        @SerializedName("original_name")
        val originalName: String, // Tom Holland
        @SerializedName("popularity")
        val popularity: Double, // 131.713
        @SerializedName("profile_path")
        val profilePath: String // /2qhIDp44cAqP2clOgt2afQI07X8.jpg
    )
}