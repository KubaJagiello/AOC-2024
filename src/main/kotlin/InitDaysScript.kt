import java.io.File

private const val ROOT_DIR = "src/main/kotlin/se/jakub/aoc"
private const val RESOURCES_DIR = "src/main/resources/inputs"
private const val TESTS_DIR = "src/test/kotlin/se/jakub/aoc"

fun main() {
  val totalDays = 25

  (1..totalDays).forEach { day -> createDayFiles(day) }
  println("All Advent of Code day files and directories created successfully!")
}

fun createDayFiles(day: Int) {
  val dayPackageName = formatDayPackageName(day)
  val dayClassName = formatDayClassName(day)

  createKotlinFile(dayPackageName, dayClassName)
  createResourceFiles(dayPackageName, dayClassName)
  createTests(dayPackageName, dayClassName)
}

fun formatDayPackageName(day: Int): String = "day${day.toString().padStart(2, '0')}"

fun formatDayClassName(day: Int): String = "Day${day.toString().padStart(2, '0')}"

fun createKotlinFile(
    dayPackageName: String,
    dayClassName: String,
) {
  val packageDir = File("$ROOT_DIR/$dayPackageName").apply { mkdirs() }
  val dayFile = File("$packageDir/$dayClassName.kt")

  if (!dayFile.exists()) {
    dayFile.writeText(
        """
            package se.jakub.aoc.$dayPackageName
            
            import se.jakub.aoc.Day
            
            class $dayClassName : Day {
            
                override fun part1(lines: List<String>): Any {
                    return "Not Implemented"
                }

                override fun part2(lines: List<String>): Any {
                    return "Not Implemented"
                }
            }
            """
            .trimIndent(),
    )
    println("Created $dayClassName.kt")
  }
}

fun createResourceFiles(
    dayPackageName: String,
    dayClassName: String,
) {
  val dayResourcesDir = File("$RESOURCES_DIR/$dayPackageName").apply { mkdirs() }

  val partFiles =
      listOf("part1", "part2").map { part ->
        File("$dayResourcesDir/$dayClassName-$part.txt".lowercase())
      }

  partFiles.forEach { file ->
    if (!file.exists()) {
      file.writeText("")
      println("Created ${file.name}")
    }
  }
}

fun createTests(dayPackageName: String, dayClassName: String) {
  val testFileDir = File("$TESTS_DIR/$dayPackageName").apply { mkdirs() }
  val dayTestFile = File("$testFileDir/Test$dayClassName.kt")

  if (!dayTestFile.exists()) {
    dayTestFile.writeText(
        """
            package se.jakub.aoc.$dayPackageName
            
            import org.junit.jupiter.api.Test
            import kotlin.test.assertEquals
            import java.io.File
            
            class Test$dayClassName {
                @Test
                fun testPart1() {
                    val ${dayClassName.lowercase()} = $dayClassName()
                    val input = File("src/main/resources/inputs/$dayPackageName/${dayClassName.lowercase()}-part1.txt").readLines()
                    val expected = "Not Implemented"
                    
                    assertEquals(expected, ${dayClassName.lowercase()}.part1(input))
                }

                @Test
                fun testPart2() {
                    val ${dayClassName.lowercase()} = $dayClassName()
                    val input = File("src/main/resources/inputs/$dayPackageName/${dayClassName.lowercase()}-part2.txt").readLines()
                    val expected = "Not Implemented"
                    
                    assertEquals(expected, ${dayClassName.lowercase()}.part2(input))
                }
            }
            """
            .trimIndent())
    println("Created Test$dayClassName.kt")
  }
}
