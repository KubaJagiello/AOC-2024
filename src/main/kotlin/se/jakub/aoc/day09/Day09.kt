package se.jakub.aoc.day09

import se.jakub.aoc.Day

data class SingleBlock(val id: Int) {

  companion object {
    private const val EMPTY_ID = -1

    fun empty(): SingleBlock {
      return SingleBlock(EMPTY_ID)
    }
  }

  fun isEmpty(): Boolean {
    return id == EMPTY_ID
  }
}

data class Block(val id: Int, val length: Int)

private const val EMPTY = -1

class Day09 : Day {

  override fun part1(lines: List<String>): Any {
    val blocks = getSingleBlocks(lines.first())

    while (true) {
      val leftmostDot = blocks.indexOfFirst { it.isEmpty() }
      val lastFileIndex = blocks.indexOfLast { !it.isEmpty() }

      if (lastFileIndex == -1 || leftmostDot == -1 || leftmostDot > lastFileIndex) {
        break
      }

      val tmp = blocks[leftmostDot]
      blocks[leftmostDot] = blocks[lastFileIndex]
      blocks[lastFileIndex] = tmp
    }

    return checksum(blocks)
  }

  override fun part2(lines: List<String>): Any {
    var blocks = getBlocks(lines)
    var currentlySearchAfter = blocks.last().id

    while (true) {
      if (currentlySearchAfter < 0) break
      blocks = compactEmptySpaces(blocks)
      var indexOfCurrent = blocks.indexOfLast { it.id == currentlySearchAfter }

      val rightBlock = blocks[indexOfCurrent]
      for (i in 0 until indexOfCurrent) {
        val leftBlock = blocks[i]

        if (leftBlock.id == EMPTY && leftBlock.length >= rightBlock.length) {
          if (leftBlock.length > rightBlock.length) {
            blocks.add(i + 1, Block(EMPTY, leftBlock.length - rightBlock.length))
            indexOfCurrent++
          }
          blocks[i] = rightBlock
          blocks[indexOfCurrent] = Block(EMPTY, rightBlock.length)
          break
        }
      }

      currentlySearchAfter--
    }

    return checksum(blocks.map { h -> List(h.length) { SingleBlock(h.id) } }.flatten())
  }

  private fun compactEmptySpaces(blocks: MutableList<Block>): MutableList<Block> {
    var i = 0
    while (true) {
      if (i + 1 >= blocks.size) break
      val leftBlock = blocks[i]
      val rightBlock = blocks[i + 1]

      if (leftBlock.id == EMPTY && rightBlock.id == EMPTY) {
        blocks[i] = Block(EMPTY, leftBlock.length + rightBlock.length)
        blocks.removeAt(i + 1)
      }

      i++
    }
    return blocks
  }

  private fun checksum(singleBlocks: List<SingleBlock>): Long {
    return singleBlocks.withIndex().sumOf { (index, block) ->
      if (!block.isEmpty()) {
        index.toLong() * block.id
      } else {
        0L
      }
    }
  }

  private fun getSingleBlocks(line: String): MutableList<SingleBlock> {
    val blocks = mutableListOf<SingleBlock>()

    var fileId = 0
    for ((i, length) in line.withIndex()) {
      val repeatCount = length.toString().toInt()

      for (j in 0 until repeatCount) {
        if (i % 2 == 0) {
          blocks.add(SingleBlock(fileId))
        } else {
          blocks.add(SingleBlock.empty())
        }
      }
      if (i % 2 == 0) {
        fileId++
      }
    }
    return blocks
  }

  private fun getBlocks(lines: List<String>): MutableList<Block> {
    val blocks = mutableListOf<Block>()

    var fileId = 0
    for ((i, l) in lines.first().withIndex()) {
      val length = l.toString().toInt()

      if (i % 2 == 0) {
        blocks.add(Block(fileId, length))
        fileId++
      } else {
        blocks.add(Block(EMPTY, length))
      }
    }
    return blocks
  }
}
