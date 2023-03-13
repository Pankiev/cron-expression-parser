package org.shift4.cronexpressionparser

import org.shift4.cronexpressionparser.argumentparser.*

class CronJobParserFactory {

    fun newInstance(expressionParameters: List<String>): CronJobParser {
        if (expressionParameters.size != 6) {
            throw CronParserIllegalArgumentException("Cron expression should contain 6 arguments (5 standard cron arguments and a command)")
        }
        return CronJobParser(
            listOf(
                CronMinuteArgumentParser(),
                CronHourArgumentParser(),
                CronDayOfMonthArgumentParser(),
                CronMonthArgumentParser(),
                CronDayOfWeekArgumentParser()
            )
        )
    }
}
