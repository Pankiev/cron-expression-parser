package org.shift4.cronexpressionparser.argumentparser

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertThrows
import org.shift4.cronexpressionparser.CronParserIllegalArgumentException
import org.shift4.cronexpressionparser.DayOfMonth
import org.shift4.cronexpressionparser.Hour
import org.shift4.cronexpressionparser.Month

class CronMonthArgumentParserTest {

    private val cronMonthArgumentParser = CronMonthArgumentParser()

    @TestFactory
    fun `parse returns appropriate set`() = listOf(
        dynamicTest("given 1 method parse returns set with 1") {
            parseTest("1", listOf(1).map { Month.of(it) }.toSet())
        },
        dynamicTest("given 12 method parse returns set with 12") {
            parseTest("12", listOf(12).map { Month.of(it) }.toSet())
        },
        dynamicTest("given * method parse returns set with full range") {
            parseTest("*", (1..12).map { Month.of(it) }.toSet())
        },
        dynamicTest("given 2-4 method parse returns set with 2,3,4") {
            parseTest("2-4", listOf(2, 3, 4).map { Month.of(it) }.toSet())
        },
        dynamicTest("given */5 method parse returns set with every 5th values from 1 to 12") {
            parseTest("*/5", listOf(1, 6, 11).map { Month.of(it) }.toSet())
        },
        dynamicTest("given 1-11/5 method parse returns set with 1,5,11") {
            parseTest("1-11/5", listOf(1, 6, 11).map { Month.of(it) }.toSet())
        },
    )

    private fun parseTest(argument: String, expectedResult: Set<Month>) {
        // when
        val result = cronMonthArgumentParser.parse(argument)
        // then
        assertThat(result).containsExactlyElementsOf(expectedResult)
    }

    @TestFactory
    fun `parse throws exception`() = listOf(
        dynamicTest("given 0 method throws exception") {
            invalidArgumentParseTest("0")
        },
        dynamicTest("given 13 method throws exception") {
            invalidArgumentParseTest("13")
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
        val action = { cronMonthArgumentParser.parse(invalidArgument) }
        // then
        assertThrows<CronParserIllegalArgumentException> { action() }
    }
}
