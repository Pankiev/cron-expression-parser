package org.shift4.cronexpressionparser.argumentparser

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertThrows
import org.shift4.cronexpressionparser.CronParserIllegalArgumentException
import org.shift4.cronexpressionparser.Minute
import org.shift4.cronexpressionparser.Month

class CronMinuteArgumentParserTest {

    private val cronMinuteArgumentParser = CronMinuteArgumentParser()

    @TestFactory
    fun `parse returns appropriate set`() = listOf(
        dynamicTest("given 0 method parse returns set with 0") {
            parseTest("0", listOf(0).map { Minute.of(it) }.toSet())
        },
        dynamicTest("given 1,1 method parse returns set with 1") {
            parseTest("1,1", listOf(1).map { Minute.of(it) }.toSet())
        },
        dynamicTest("given 1,2 method parse returns set with 1,2") {
            parseTest("1,2", listOf(1,2).map { Minute.of(it) }.toSet())
        },
        dynamicTest("given 59 method parse returns set with 59") {
            parseTest("59", listOf(59).map { Minute.of(it) }.toSet())
        },
        dynamicTest("given * method parse returns set with full range") {
            parseTest("*", (0..59).map { Minute.of(it) }.toSet())
        },
        dynamicTest("given 2-4 method parse returns set with 2,3,4") {
            parseTest("2-4", listOf(2, 3, 4).map { Minute.of(it) }.toSet())
        },
        dynamicTest("given */5 method parse returns set with every 5th values from 0 to 59") {
            parseTest("*/5", (0..59 step 5).map { Minute.of(it) }.toSet())
        },
        dynamicTest("given 0-10/5 method parse returns set with 0,5,10") {
            parseTest("0-10/5", listOf(0, 5, 10).map { Minute.of(it) }.toSet())
        },
    )

    private fun parseTest(argument: String, expectedResult: Set<Minute>) {
        // when
        val result = cronMinuteArgumentParser.parse(argument)
        // then
        assertThat(result).containsExactlyElementsOf(expectedResult)
    }

    @TestFactory
    fun `parse throws exception`() = listOf(
        dynamicTest("given -1 method throws exception") {
            invalidArgumentParseTest("-1")
        },
        dynamicTest("given 60 method throws exception") {
            invalidArgumentParseTest("60")
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
        val action = { cronMinuteArgumentParser.parse(invalidArgument) }
        // then
        assertThrows<CronParserIllegalArgumentException> { action() }
    }
}
