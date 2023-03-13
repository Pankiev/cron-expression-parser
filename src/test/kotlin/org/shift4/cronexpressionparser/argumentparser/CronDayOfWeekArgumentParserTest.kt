package org.shift4.cronexpressionparser.argumentparser

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertThrows
import org.shift4.cronexpressionparser.CronParserIllegalArgumentException
import org.shift4.cronexpressionparser.DayOfWeek
import org.shift4.cronexpressionparser.Hour
import org.shift4.cronexpressionparser.Month

class CronDayOfWeekArgumentParserTest {

    private val cronDayOfWeekArgumentParser = CronDayOfWeekArgumentParser()

    @TestFactory
    fun `parse returns appropriate set`() = listOf(
        dynamicTest("given 0 method parse returns set with 0") {
            parseTest("0", listOf(0).map { DayOfWeek.of(it) }.toSet())
        },
        dynamicTest("given 1,1 method parse returns set with 1") {
            parseTest("1,1", listOf(1).map { DayOfWeek.of(it) }.toSet())
        },
        dynamicTest("given 1,2 method parse returns set with 1,2") {
            parseTest("1,2", listOf(1,2).map { DayOfWeek.of(it) }.toSet())
        },
        dynamicTest("given 6 method parse returns set with 6") {
            parseTest("6", listOf(6).map { DayOfWeek.of(it) }.toSet())
        },
        dynamicTest("given * method parse returns set with full range") {
            parseTest("*", (0..6).map { DayOfWeek.of(it) }.toSet())
        },
        dynamicTest("given 2-4 method parse returns set with 2,3,4") {
            parseTest("2-4", listOf(2, 3, 4).map { DayOfWeek.of(it) }.toSet())
        },
        dynamicTest("given */2 method parse returns set with every 2th values from 0 to 6") {
            parseTest("*/2", listOf(0, 2, 4, 6).map { DayOfWeek.of(it) }.toSet())
        },
        dynamicTest("given 1-6/5 method parse returns set with 1,5") {
            parseTest("1-6/5", listOf(1, 6,).map { DayOfWeek.of(it) }.toSet())
        },
    )

    private fun parseTest(argument: String, expectedResult: Set<DayOfWeek>) {
        // when
        val result = cronDayOfWeekArgumentParser.parse(argument)
        // then
        assertThat(result).containsExactlyElementsOf(expectedResult)
    }

    @TestFactory
    fun `parse throws exception`() = listOf(
        dynamicTest("given -1 method throws exception") {
            invalidArgumentParseTest("-1")
        },
        dynamicTest("given 7 method throws exception") {
            invalidArgumentParseTest("7")
        },
        dynamicTest("given 5-100 method throws exception") {
            invalidArgumentParseTest("5-100")
        },
        dynamicTest("given 2-* method throws exception") {
            invalidArgumentParseTest("2-*")
        },
        dynamicTest("given 1/* method throws exception") {
            invalidArgumentParseTest("1/*")
        },
        dynamicTest("given 5-2 method throws exception") {
            invalidArgumentParseTest("5-2")
        },
    )

    private fun invalidArgumentParseTest(invalidArgument: String) {
        // when
        val action = { cronDayOfWeekArgumentParser.parse(invalidArgument) }
        // then
        assertThrows<CronParserIllegalArgumentException> { action() }
    }
}
