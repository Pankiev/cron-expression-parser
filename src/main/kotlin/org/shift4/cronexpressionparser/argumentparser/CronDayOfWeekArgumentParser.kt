package org.shift4.cronexpressionparser.argumentparser

import org.shift4.cronexpressionparser.DayOfWeek
import org.shift4.cronexpressionparser.replaceIgnoreCase

internal class CronDayOfWeekArgumentParser : CronTemporalArgumentParser<DayOfWeek> {

    private val numericalCronArgumentParser = NumericalCronArgumentParser()

    override fun parse(cronArgument: String): Set<DayOfWeek> {
        val numericalValuesCronArgument = replaceWordValuesToNumericalValues(cronArgument)
        return numericalCronArgumentParser.parseCronArgument(numericalValuesCronArgument, DayOfWeek.MIN_VALUE.rawValue, DayOfWeek.MAX_VALUE.rawValue)
            .map { DayOfWeek.of(it) }
            .toSet()
    }

    private fun replaceWordValuesToNumericalValues(cronArgument: String): String {
        val replacements = java.time.DayOfWeek.values()
            .map { it.name.substring(0, 3) to it.ordinal.toString() }
        return cronArgument.replaceIgnoreCase(replacements)
    }

    override fun getProducedTemporalClass() = DayOfWeek::class.java
}
