fun main() {
    fun part1(input: List<String>): Int {
        return input.map { line ->
            "${line.first { it.isDigit() }}${line.last { it.isDigit() }}".toInt()
        }.reduce { acc, i -> acc + i }
    }

    fun part2(input: List<String>): Int {

        val digits = mapOf(
            "1" to 1,
            "2" to 2,
            "3" to 3,
            "4" to 4,
            "5" to 5,
            "6" to 6,
            "7" to 7,
            "8" to 8,
            "9" to 9,
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9
        )

        return input.map { line ->
            val firstDigit = digits[line.findAnyOf(digits.keys)!!.second]!!
            val secondDigit = digits[line.findLastAnyOf(digits.keys)!!.second]!!
            firstDigit * 10 + secondDigit
        }.reduce { acc, i -> acc + i }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    part1(testInput)
    check(part1(testInput) == 142)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
