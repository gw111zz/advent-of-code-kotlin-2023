fun main() {

    val NUM_RED = 12
    val NUM_GREEN = 13
    val NUM_BLUE = 14

    fun part1(input: List<String>): Int {

        val validGames = mutableListOf<Int>()

        input.forEachIndexed { index, gameLine ->
            val bluesExceeded = "(?<num>\\d+)\\s+blue".toRegex().findAll(gameLine).any { matchResult ->
                matchResult.groups["num"]!!.value.toInt() > NUM_BLUE
            }
            val redsExceeded = "(?<num>\\d+)\\s+red".toRegex().findAll(gameLine).any { matchResult ->
                matchResult.groups["num"]!!.value.toInt() > NUM_RED
            }
            val greensExceeded = "(?<num>\\d+)\\s+green".toRegex().findAll(gameLine).any { matchResult ->
                matchResult.groups["num"]!!.value.toInt() > NUM_GREEN
            }

            if (!bluesExceeded && !redsExceeded && !greensExceeded) {
                validGames.add(index + 1)
            }
        }
        return validGames.reduce { acc, i -> acc + i }.also { println(it) }
    }

    fun part2(input: List<String>): Int {
        return input.map { gameLine ->
            val maxBlue = "(?<num>\\d+)\\s+blue".toRegex().findAll(gameLine).maxOf { matchResult ->
                matchResult.groups["num"]!!.value.toInt()
            }
            val maxRed = "(?<num>\\d+)\\s+red".toRegex().findAll(gameLine).maxOf { matchResult ->
                matchResult.groups["num"]!!.value.toInt()
            }
            val maxGreen = "(?<num>\\d+)\\s+green".toRegex().findAll(gameLine).maxOf { matchResult ->
                matchResult.groups["num"]!!.value.toInt()
            }
            maxBlue * maxRed * maxGreen
        }.reduce { acc, i -> acc + i }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    part1(testInput)
    check(part1(testInput) == 8)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
