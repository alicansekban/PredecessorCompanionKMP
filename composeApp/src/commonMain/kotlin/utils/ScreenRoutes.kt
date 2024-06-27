package utils

import kotlinx.serialization.Serializable

object ScreenRoutes {

    const val BACK_PRESSED = "back_pressed"

    // Home Graph
    const val HOME_HOST_ROUTE = "home_host"
    const val HOME_ROUTE = "heroes"

    // LeaderBoard Graph

    const val LEADER_BOARD_HOST_ROUTE = "leader_board_host"
    const val LEADER_BOARD_ROUTE = "leader_board"
    const val PLAYER_DETAIL_ROUTE = "player_detail_route/{player_id}"

    // Saved Graph

    const val SAVED_HOST_ROUTE = "saved_host"
    const val SAVED_ROUTE = "saved"

    // Builds Graph

    const val BUILD_HOST_ROUTE = "build_host"
    const val BUILD_ROUTE = "build"

    // More Graph

    const val MORE_HOST_ROUTE = "more_host"
    const val MORE_ROUTE = "more"
    const val MORE_ITEMS_ROUTE = "more_items"

}

@Serializable
data class ItemDetail(
    val name: String
)

@Serializable
object ItemsList

@Serializable
data class HeroDetail(
    val heroName: String = "",
    val heroId: String = "",
    val image: String = "",
    val role: String = ""
)