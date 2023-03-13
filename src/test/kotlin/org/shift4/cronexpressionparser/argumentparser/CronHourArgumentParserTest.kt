package org.shift4.cronexpressionparser.argumentparser

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertThrows
import org.shift4.cronexpressionparser.CronParserIllegalArgumentException
import org.shift4.cronexpressionparser.Hour
import org.shift4.cronexpressionparser.Minute

class CronHourArgumentParserTest {

    private val cronHourArgumentParser = CronHourArgumentParser()

    @TestFactory
    fun `parse returns appropriate set`() = listOf(
        dynamicTest("given 0 method parse returns set with 0") {
            parseTest("0", listOf(0).map { Hour.of(it) }.toSet())
        },
        dynamicTest("given 1,1 method parse returns set with 1") {
            parseTest("1,1", listOf(1).map { Hour.of(it) }.toSet())
        },
        dynamicTest("given 1,2 method parse returns set with 1,2") {
            parseTest("1,2", listOf(1,2).map { Hour.of(it) }.toSet())
        },
        dynamicTest("given 23 method parse returns set with 23") {
            parseTest("23", listOf(23).map { Hour.of(it) }.toSet())
        },
        dynamicTest("given * method parse returns set with full range") {
            parseTest("*", (0..23).map { Hour.of(it) }.toSet())
        },
        dynamicTest("given 2-4 method parse returns set with 2,3,4") {
            parseTest("2-4", listOf(2, 3, 4).map { Hour.of(it) }.toSet())
        },
        dynamicTest("given */5 method parse returns set with every 5th values from 0 to 23") {
            parseTest("*/5", (0..23 step 5).map { Hour.of(it) }.toSet())
        },
        dynamicTest("given 0-10/5 method parse returns set with 0,5,10") {
            parseTest("0-10/5", listOf(0, 5, 10).map { Hour.of(it) }.toSet())
        },
    )

    private fun parseTest(argument: String, expectedResult: Set<Hour>) {
        // when
        val result = cronHourArgumentParser.parse(argument)
        // then
        assertThat(result).containsExactlyElementsOf(expectedResult)
    }

    @TestFactory
    fun `parse throws exception`() = listOf(
        dynamicTest("given -1 method throws exception") {
            invalidArgumentParseTest("-1")
        },
        dynamicTest("given 24 method throws exception") {
            invalidArgumentParseTest("24")
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
        val action = { cronHourArgumentParser.parse(invalidArgument) }
        // then
        assertThrows<CronParserIllegalArgumentException> { action() }
    }
}
