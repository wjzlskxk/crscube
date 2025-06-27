import kotlin.math.sqrt
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    println("좌표의 개수를 입력하세요.")
    val n = readLine()!!.toInt()

    println("좌표를 다음과 같게 입력해주세요. ex) 2 1")
    val point = Array(n) {
        val (x, y) =  readLine()!!.split(',').map { it.toInt() }
        Pair(x, y)
    }

    val result = sortByProximity(point)
    println(result.joinToString(", ") { "( ${it.first}, ${it.second} )" })
}

fun distance(p1: Pair<Int, Int>, p2: Pair<Int, Int>): Double {
    val dx = p1.first - p2.first
    val dy = p1.second - p2.second
    return sqrt((dx * dx + dy * dy).toDouble())
}

fun sortByProximity(points: Array<Pair<Int, Int>>): List<Pair<Int, Int>> {
    return points.sortedBy { p ->
        points.sumOf { other -> if (p != other) distance(p, other) else 0.0 }
    }
}