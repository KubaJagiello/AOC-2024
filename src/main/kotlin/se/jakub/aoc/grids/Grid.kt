package se.jakub.aoc.grids

sealed class Direction(val dx: Int, val dy: Int) {
  data object Up : Direction(0, -1)

  data object Down : Direction(0, 1)

  data object Left : Direction(-1, 0)

  data object Right : Direction(1, 0)

  data object UpLeft : Direction(-1, -1)

  data object UpRight : Direction(1, -1)

  data object DownLeft : Direction(-1, 1)

  data object DownRight : Direction(1, 1)

  companion object {
    val allDirections = listOf(Up, Down, Left, Right, UpLeft, UpRight, DownLeft, DownRight)
  }
}

class Grid(private val lines: List<String>) {
  val rows = lines.size
  val cols = lines[0].length

  fun get(x: Int, y: Int): Char = lines[y][x]

  fun getPath(x: Int, y: Int, steps: Int, direction: Direction): List<Char> {

    val path: MutableList<Char> = mutableListOf()
    for (i in 0 until steps) {
      val newX = i * direction.dx + x
      val newY = i * direction.dy + y

      if (!isInBounds(newX, newY)) return path

      path.add(get(newX, newY))
    }

    return path
  }

  fun getPositions(): List<Pair<Int, Int>> = positions

  private val positions: List<Pair<Int, Int>> =
      (0 until rows).flatMap { x -> (0 until cols).map { y -> Pair(x, y) } }

  private fun isInBounds(x: Int, y: Int): Boolean = x in 0 until cols && y in 0 until rows
}
