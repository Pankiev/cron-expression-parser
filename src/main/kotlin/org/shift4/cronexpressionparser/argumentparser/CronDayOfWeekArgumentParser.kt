package org.shift4.cronexpressionparser.argumentparser

import org.shift4.cronexpressionparser.DayOfWeek

internal class CronDayOfWeekArgumentParser : CronTemporalArgumentParser<DayOfWeek> {

    private val numericalCronArgumentParser = NumericalCronArgumentParser()

    override fun parse(cronArgument: String): Set<DayOfWeek> {
        return numericalCronArgumentParser.parseCronArgument(cronArgument, DayOfWeek.MIN_VALUE.rawValue, DayOfWeek.MAX_VALUE.rawValue)
            .map { DayOfWeek.of(it) }
            .toSet()
    }

    override fun getProducedTemporalClass() = DayOfWeek::class.java
}
