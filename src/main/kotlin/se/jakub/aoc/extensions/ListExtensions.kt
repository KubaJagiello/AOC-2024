package se.jakub.aoc.extensions

fun <T> List<T>.isSortedBy(comparator: (T, T) -> Boolean): Boolean {
  return this.zipWithNext().all { (a, b) -> comparator(a, b) }
}

fun <T : Comparable<T>> List<T>.isSortedAscending(): Boolean {
  return this.isSortedBy { a, b -> a <= b }
}

fun <T : Comparable<T>> List<T>.isSortedDescending(): Boolean {
  return this.isSortedBy { a, b -> a >= b }
}

fun <T : Comparable<T>> List<T>.isSorted(): Boolean {
  if (size <= 1) return true

  var isAscending = true
  var isDescending = true

  for (i in 1 until size) {
    val first = this[i - 1]
    val second = this[i]

    when {
      first > second -> isAscending = false
      first < second -> isDescending = false
    }

    if (!isAscending && !isDescending) return false
  }

  return true
}

fun List<Int>.product(): Int {
  return this.fold(1) { acc, num -> acc * num }
}

fun List<String>.toInts(): List<Int> {
  return this.map { it.toInt() }
}

fun <T> List<T>.permutations(length: Int): List<List<T>> {
  if (length == 0) return listOf(emptyList())

  return this.flatMap { element -> this.permutations(length - 1).map { listOf(element) + it } }
}
