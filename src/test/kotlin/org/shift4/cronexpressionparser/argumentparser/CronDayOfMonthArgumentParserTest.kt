package org.shift4.cronexpressionparser.argumentparser

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertThrows
import org.shift4.cronexpressionparser.CronParserIllegalArgumentException
import org.shift4.cronexpressionparser.DayOfMonth
import org.shift4.cronexpressionparser.DayOfWeek
import org.shift4.cronexpressionparser.Month

class CronDayOfMonthArgumentParserTest {

    private val cronDayOfMonthArgumentParser = CronDayOfMonthArgumentParser()

    @TestFactory
    fun `parse returns appropriate set`() = listOf(
        dynamicTest("given 1 method parse returns set with 1") {
            parseTest("1", listOf(1).map { DayOfMonth.of(it) }.toSet())
        },
        dynamicTest("given 1,1 method parse returns set with 1") {
            parseTest("1,1", listOf(1).map { DayOfMonth.of(it) }.toSet())
        },
        dynamicTest("given 1,2 method parse returns set with 1,2") {
            parseTest("1,2", listOf(1,2).map { DayOfMonth.of(it) }.toSet())
        },
        dynamicTest("given 31 method parse returns set with 31") {
            parseTest("31", listOf(31).map { DayOfMonth.of(it) }.toSet())
        },
        dynamicTest("given * method parse returns set with full range") {
            parseTest("*", (1..31).map { DayOfMonth.of(it) }.toSet())
        },
        dynamicTest("given 2-4 method parse returns set with 2,3,4") {
            parseTest("2-4", listOf(2, 3, 4).map { DayOfMonth.of(it) }.toSet())
        },
        dynamicTest("given */5 method parse returns set with every 5th values from 1 to 31") {
            parseTest("*/5", (1..31 step 5).map { DayOfMonth.of(it) }.toSet())
        },
        dynamicTest("given 1-11/5 method parse returns set with 1,5,11") {
            parseTest("1-11/5", listOf(1, 6, 11).map { DayOfMonth.of(it) }.toSet())
        },
    )

    private fun parseTest(argument: String, expectedResult: Set<DayOfMonth>) {
        // when
        val result = cronDayOfMonthArgumentParser.parse(argument)
        // then
        assertThat(result).containsExactlyElementsOf(expectedResult)
    }

    @TestFactory
    fun `parse throws exception`() = listOf(
        dynamicTest("given 0 method throws exception") {
            invalidArgumentParseTest("0")
        },
        dynamicTest("given 32 method throws exception") {
            invalidArgumentParseTest("32")
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
        val action = { cronDayOfMonthArgumentParser.parse(invalidArgument) }
        // then
        assertThrows<CronParserIllegalArgumentException> { action() }
    }
}
