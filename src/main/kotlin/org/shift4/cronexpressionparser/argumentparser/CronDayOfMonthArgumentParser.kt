package org.shift4.cronexpressionparser.argumentparser

import org.shift4.cronexpressionparser.DayOfMonth

internal class CronDayOfMonthArgumentParser : CronTemporalArgumentParser<DayOfMonth> {

    private val numericalCronArgumentParser = NumericalCronArgumentParser()

    override fun parse(cronArgument: String): Set<DayOfMonth> {
        return numericalCronArgumentParser.parseCronArgument(cronArgument, DayOfMonth.MIN_VALUE.rawValue, DayOfMonth.MAX_VALUE.rawValue)
            .map { DayOfMonth.of(it) }
            .toSet()
    }

    override fun getProducedTemporalClass() = DayOfMonth::class.java
}
