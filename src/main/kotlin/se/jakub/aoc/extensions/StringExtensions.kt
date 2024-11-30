package se.jakub.aoc.extensions

fun String.splitBySpaces(): List<String> {
  return this.trim().split("\\s+".toRegex())
}
