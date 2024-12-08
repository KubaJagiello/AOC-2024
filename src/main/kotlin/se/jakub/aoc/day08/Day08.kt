package se.jakub.aoc.day08

import kotlin.math.sqrt
import se.jakub.aoc.Day
import se.jakub.aoc.grids.Grid
import se.jakub.aoc.grids.Position

data class Antenna(val position: Position, val frequency: String)

class Day08 : Day {
  override fun part1(lines: List<String>): Any {
    val grid = Grid(lines)
    val frequenciesMap = frequenciesMap(grid)
    val antinodeLocations = mutableSetOf<Position>()

    for (antennas in frequenciesMap.values) {
      for (antinodePosition in grid.getPositions()) {
        for (antennaA in antennas) {
          for (antennaB in antennas) {
            if (antennaA == antennaB) continue

            if (areInLine(antinodePosition, antennaA, antennaB) &&
                isTwiceAsFar(antinodePosition, antennaA, antennaB)) {
              antinodeLocations.add(antinodePosition)
            }
          }
        }
      }
    }

    return antinodeLocations.size
  }

  override fun part2(lines: List<String>): Any {
    val grid = Grid(lines)
    val frequenciesMap = frequenciesMap(grid)
    val antinodeLocations = mutableSetOf<Position>()

    for (antennas in frequenciesMap.values) {
      for (antinodePosition in grid.getPositions()) {
        for (antennaA in antennas) {
          for (antennaB in antennas) {
            if (antennaA == antennaB) continue

            if (areInLine(antinodePosition, antennaA, antennaB)) {
              antinodeLocations.add(antinodePosition)
            }
          }
        }
      }
    }

    return antinodeLocations.size
  }

  private fun frequenciesMap(grid: Grid) =
      parseAntennas(grid).groupBy { it.frequency }.mapValues { (_, v) -> v.toSet() }

  private fun isTwiceAsFar(
      currentPosition: Position,
      antennaA: Antenna,
      antennaB: Antenna
  ): Boolean {
    val distanceToA = euclideanDistance(currentPosition, antennaA.position)
    val distanceToB = euclideanDistance(currentPosition, antennaB.position)

    return distanceToA == 2 * distanceToB || distanceToB == 2 * distanceToA
  }

  private fun euclideanDistance(pos1: Position, pos2: Position): Double {
    val dx = pos1.x - pos2.x
    val dy = pos1.y - pos2.y
    return sqrt((dx * dx + dy * dy).toDouble())
  }

  private fun parseAntennas(grid: Grid): List<Antenna> {
    val antennas = mutableListOf<Antenna>()
    for (position in grid.getPositions()) {
      val c = grid.get(position)
      if (c != '.') {
        antennas.add(Antenna(position, c.toString()))
      }
    }
    return antennas
  }

  private fun areInLine(pos1: Position, antennaA: Antenna, antennaB: Antenna): Boolean {
    val dx1 = antennaA.position.x - pos1.x
    val dy1 = antennaA.position.y - pos1.y
    val dx2 = antennaB.position.x - pos1.x
    val dy2 = antennaB.position.y - pos1.y

    return dx1 * dy2 == dy1 * dx2
  }
}
