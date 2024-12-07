package se.jakub.aoc.day05

import se.jakub.aoc.Day
import se.jakub.aoc.extensions.toInts

typealias Graph = Map<Int, Set<Int>>

class Day05 : Day {

  override fun part1(lines: List<String>): Any {
    val (graph, lists) = parseInput(lines)

    return lists.filter { isInRightOrder(graph, it) }.sumOf { it[(it.size - 1) / 2] }
  }

  override fun part2(lines: List<String>): Any {
    val (graph, lists) = parseInput(lines)

    return lists
        .filterNot { isInRightOrder(graph, it) }
        .sumOf { list ->
          val setList = list.toSet()
          val sortedList =
              list.sortedWith { p1, p2 ->
                when {
                  isToTheRight(p1, p2, setList, graph) -> -1
                  isToTheRight(p2, p1, setList, graph) -> 1
                  else -> 0
                }
              }
          sortedList[(list.size - 1) / 2]
        }
  }

  private fun isToTheRight(start: Int, lookingFor: Int, list: Set<Int>, graph: Graph): Boolean {
    val queue = ArrayDeque<Int>().apply { addLast(start) }
    val visited = mutableSetOf<Int>()

    while (queue.isNotEmpty()) {
      val current = queue.removeFirst()

      if (current == lookingFor) return true

      if (visited.add(current)) {
        graph[current]?.filter { it in list }?.forEach { queue.addLast(it) }
      }
    }
    return false
  }

  private fun isInRightOrder(graph: Graph, list: List<Int>): Boolean {
    val setList = list.toSet()
    return list.zipWithNext().all { (left, right) -> isToTheRight(left, right, setList, graph) }
  }

  private fun parseInput(lines: List<String>): Pair<Graph, List<List<Int>>> {
    val separatorIndex = lines.indexOf("")
    val pageOrderings = lines.subList(0, separatorIndex)
    val pageUpdates = lines.subList(separatorIndex + 1, lines.size)

    val graph =
        pageOrderings
            .map { it.split("|") }
            .map { it.toInts() }
            .groupBy({ it[0] }, { it[1] })
            .mapValues { (_, v) -> v.toSet() }

    val listOfPages = pageUpdates.map { it.split(",").toInts() }

    return graph to listOfPages
  }
}
