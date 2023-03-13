package org.shift4.cronexpressionparser

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.charset.Charset

class CronExpressionInputHandlerTest {

    private var printedDataStream = ByteArrayOutputStream()
    private var printStream = PrintStream(printedDataStream)
    private var cronExpressionInputHandler = CronExpressionInputHandler(printStream, JobExecutionLogger(printStream))


    private fun setup() {
        printedDataStream = ByteArrayOutputStream()
        printStream = PrintStream(printedDataStream)
        cronExpressionInputHandler = CronExpressionInputHandler(printStream, JobExecutionLogger(printStream))
    }

    @TestFactory
    fun `handleInput prints appropriate message`() = listOf(
        dynamicTestWithSetup("given 6 arguments method handleInput prints appropriate error message") {
            handleInputTest(
                arrayOf("*/15", "0", "1,15", "*", "1-5", "/usr/bin/find"),
                "Cron expression should be passed as a single argument"
            )
        },
        dynamicTestWithSetup("given 0 arguments method handleInput prints appropriate error message") {
            handleInputTest(
                arrayOf(),
                "Cron expression should be passed as a single argument"
            )
        },
        dynamicTestWithSetup("given 3 cron arguments method handleInput prints appropriate error message") {
            handleInputTest(
                arrayOf("*/15 0 1,15"),
                "Cron expression should contain 6 arguments (5 standard cron arguments and a command)"
            )
        },
        dynamicTestWithSetup("given proper argument method handleInput prints cron schedule and command") {
            handleInputTest(
                arrayOf("*/15 0 1,15 * 1-5 /usr/bin/find"),
                "minute 0 15 30 45",
                "hour 0",
                "day of month 1 15",
                "month 1 2 3 4 5 6 7 8 9 10 11 12",
                "day of week 1 2 3 4 5",
                "command /usr/bin/find"
            )
        }
    )

    private fun dynamicTestWithSetup(displayName: String, executable: () -> Unit) = dynamicTest(displayName) {
        setup()
        executable()
    }

    private fun handleInputTest(args: Array<String>, vararg expectedMessages: String) {
        // when
        cronExpressionInputHandler.handleInput(args)
        // then
        val printedData = printedDataStream.toString(Charset.forName("UTF-8"))
        assertThat(printedData.lines()).containsExactlyElementsOf(expectedMessages.asList() + "")
    }
}
