package data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeroesResponse(

	@SerialName("abilities")
	val abilities: List<AbilitiesItem?>? = null,

	@SerialName("image")
	val image: String? = null,

	@SerialName("stats")
	val stats: List<Int?>? = null,

	@SerialName("classes")
	val classes: List<String?>? = null,

	@SerialName("roles")
	val roles: List<String>? = null,

	@SerialName("name")
	val name: String? = null,

	@SerialName("id")
	val id: Int? = null,

	@SerialName("display_name")
	val display_name: String? = null,

	@SerialName("base_stats")
	val base_stats: BaseStats? = null,

	@SerialName("game_id")
	val game_id: Int? = null
)

@Serializable
data class BaseStats(

	@SerialName("max_health")
	val max_health: List<Double> = emptyList(),

	@SerialName("basic_attack_time")
	val basic_attack_time: List<Double> = emptyList(),

	@SerialName("attack_range")
	val attackRange: List<Double> = emptyList(),

	@SerialName("base_movement_speed")
	val base_movement_speed: List<Double> = emptyList(),

	@SerialName("cleave")
	val cleave: List<Double> = emptyList(),

	@SerialName("magical_armor")
	val magicalArmor: List<Double> = emptyList(),

	@SerialName("physical_power")
	val physical_power: List<Double> = emptyList(),

	@SerialName("base_mana_regeneration")
	val base_mana_regeneration: List<Double> = emptyList(),

	@SerialName("attack_speed")
	val attack_speed: List<Double> = emptyList(),

	@SerialName("max_mana")
	val max_mana: List<Double> = emptyList(),

	@SerialName("base_health_regeneration")
	val base_health_regeneration: List<Double> = emptyList(),

	@SerialName("physical_armor")
	val physicalArmor: List<Double> = emptyList()
)

@Serializable
data class AbilitiesItem(

	@SerialName("menu_description")
	val menu_description: String? = null,

	@SerialName("image")
	val image: String? = null,

	@SerialName("cost")
	val cost: List<Double> = emptyList(),

	@SerialName("cooldown")
	val cooldown: List<Double> = emptyList(),

	@SerialName("game_description")
	val game_description: String? = null,

	@SerialName("display_name")
	val display_name: String? = null
)
