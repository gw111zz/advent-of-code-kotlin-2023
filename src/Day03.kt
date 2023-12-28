fun main() {

    data class Coordinate(val x: Int, val y: Int)

    fun Char.isSingleDigit(): Boolean = Regex("[0-9]").matches(this.toString())

    fun Char.isSymbol(): Boolean = !isSingleDigit() && this != '.'

    fun isPartNumber(grid: Array<CharArray>, coordinate: Coordinate, number: String): Boolean {
        val numberLength = number.length

        var foundSymbol = false

        // Search above if possible
        if (coordinate.y > 0) {
            val y = coordinate.y - 1
            val aboveLeftSearchX = if (coordinate.x == 0) 0 else (coordinate.x - 1)
            val aboveRightSearchX =
                if (coordinate.x + numberLength > grid[0]!!.lastIndex) coordinate.x + numberLength - 1 else coordinate.x + numberLength
            for (x in aboveLeftSearchX..aboveRightSearchX) {
                if (grid[y]!![x]!!.isSymbol()) {
                    println("$number is a part number, symbol is above")
                    foundSymbol = true
                }
            }
        }

        // Search left if possible
        if (!foundSymbol && coordinate.x > 0) {
            val x = coordinate.x - 1
            if (grid[coordinate.y][x].isSymbol()) {
                println("$number is a part number, symbol is to the left")
                foundSymbol = true
            }
        }

        // Search right if possible
        if (!foundSymbol && (coordinate.x + numberLength) < grid[0]!!.lastIndex) {
            val x = coordinate.x + numberLength
            if (grid[coordinate.y][x].isSymbol()) {
                println("$number is a part number, symbol is to the right")
                foundSymbol = true
            }
        }

        // Search below if possible
        if (!foundSymbol && coordinate.y < grid.lastIndex) {
            val y = coordinate.y + 1
            val belowLeftSearchX = if (coordinate.x > 0) coordinate.x - 1 else coordinate.x
            val belowRightSearchX =
                if (coordinate.x + numberLength <= grid[0]!!.lastIndex) coordinate.x + numberLength else grid[0]!!.lastIndex
            for (x in belowLeftSearchX..belowRightSearchX) {
                if (grid[y]!![x]!!.isSymbol()) {
                    println("$number is a part number, symbol is below")
                    foundSymbol = true
                }
            }
        }

        return foundSymbol
    }

    fun part1(input: List<String>): Int {
        val grid: Array<CharArray> = input.map { it.toCharArray() }.toTypedArray()

        var sum = 0
        grid.forEachIndexed { y, line ->
            var currentNumber = ""
            line.forEachIndexed { x, c ->
                if (c.isSingleDigit()) {
                    currentNumber += c
                }

                if ((!c.isSingleDigit() && currentNumber != "") ||
                        (c.isSingleDigit() && x == line.lastIndex)) {
                    // Now we don't have a digit or we have reached the end of the line
                    // and current number is not empty, check if it is a part number
                    val offset = if (x == line.lastIndex && c.isSingleDigit()) 1 else 0
                    val numberStartX = x - currentNumber.length + offset
                    val isPartNumber =
                        isPartNumber(grid, Coordinate(numberStartX, y), currentNumber)
                    if (isPartNumber) {
                        sum += currentNumber.toInt()
                    }
                    currentNumber = ""
                }
            }
        }

        return sum
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 4361)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
