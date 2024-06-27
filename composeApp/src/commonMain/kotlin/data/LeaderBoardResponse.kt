package data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LeaderBoardResponse(

	@SerialName("is_active")
	val is_active: Boolean? = null,

	@SerialName("mmr")
	val mmr: Double? = null,

	@SerialName("flags")
	val flags: List<FlagsItem?>? = null,

	@SerialName("rank")
	val rank: Int? = null,

	@SerialName("top_percentage")
	val top_percentage: Double? = null,
	@SerialName("rank_active")
	val rank_active: Int? = null,

	@SerialName("rank_title")
	val rank_title: String? = null,

	@SerialName("id")
	val id: String? = null,

	@SerialName("is_ranked")
	val isRanked: Boolean? = null,

	@SerialName("display_name")
	val display_name: String? = null,

	@SerialName("region")
	val region: String? = null,

	@SerialName("rank_image")
	val rank_image: String? = null
)
@Serializable
data class FlagsItem(

	@SerialName("identifier")
	val identifier: String? = null,

	@SerialName("color")
	val color: String? = null,

	@SerialName("text")
	val text: String? = null
)
