package se.jakub.aoc.extensions

fun String.splitBySpaces(): List<String> {
  return this.trim().split("""\s+""".toRegex())
}

fun String.getInts(): List<Int> {
  return """\d+""".toRegex().findAll(this).map { it.value.toInt() }.toList()
}
