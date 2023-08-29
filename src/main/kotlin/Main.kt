import kotlin.math.floor

fun main(args: Array<String>) {
    val currentOrder = Order(1,"Кулаковского 28","ТЦ Силуэт", Location(62.0214, 129.717387,62.032501, 129.720723),0.0)
    val suitableOrders = arrayOf( // какие виды создания массива или функции,  что такое массив, какие типы можно сохранить в массиве,
        Order(2,"ГУК","ТРК Туймаада",Location(62.019717,129.709677,62.028733,129.728282),0.0),
        Order(3,"Театр им. Пушкина","Центральный парк", Location(62.028677,129.734747,62.035335, 129.714576),0.0),
        Order(4,"к/т Лена","Лермонтова 83", Location(62.020076, 129.720658,62.029408, 129.706657),0.0)
    )


    val unsortedOrders = findDistance(currentOrder,suitableOrders)
    unsortedOrders.sortBy { it.distance }

    for (i in unsortedOrders) {
        println(i)
    }
}

fun findDistance(currentOrder: Order, suitableOrders: Array<Order>): ArrayList<Order> {
    val result: ArrayList<Order> = ArrayList()
    for (order in suitableOrders) {
        val distanceAtoOrder = calculate(currentOrder.location.latA,currentOrder.location.lngA, order.location.latA,order.location.lngA)
        val orderDistance = calculate(order.location.latA,order.location.lngA, order.location.latB, order.location.lngB)
        val distanceBToOrder = calculate(order.location.latB,order.location.lngB, currentOrder.location.latB, currentOrder.location.lngB)
        val distanceSum = distanceAtoOrder + orderDistance + distanceBToOrder
        val roundedDistance = floor(distanceSum * 100 ) / 100
        order.distance = roundedDistance
        result.add(order)
    }

    return result
}

fun calculate(latA: Double, lngA: Double, latB: Double, lngB: Double): Double {
    val theta = lngA - lngB
    return  60 * 1.1515 * (180/Math.PI) * Math.acos(
        Math.sin(latA * (Math.PI/180)) * Math.sin(latB * (Math.PI/180)) +
                Math.cos(latA * (Math.PI/180)) *
                Math.cos(latB * (Math.PI/180)) *
                Math.cos(theta * (Math.PI/180))) * 1.609344
}
