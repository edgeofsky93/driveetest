data class Order(
    val id: Int,
    val from: String,
    val to: String,
    val location: Location,
    var distance: Double
)

data class Location(
    val latA: Double,
    val lngA: Double,
    val latB: Double,
    val lngB: Double,
)


