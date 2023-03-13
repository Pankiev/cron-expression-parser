package org.shift4.cronexpressionparser.argumentparser

import org.shift4.cronexpressionparser.Month
import org.shift4.cronexpressionparser.replaceIgnoreCase

internal class CronMonthArgumentParser : CronTemporalArgumentParser<Month> {

    private val numericalCronArgumentParser = NumericalCronArgumentParser()

    override fun parse(cronArgument: String): Set<Month> {
        val numericalValuesCronArgument = replaceWordValuesToNumericalValues(cronArgument)
        return numericalCronArgumentParser.parseCronArgument(numericalValuesCronArgument, Month.MIN_VALUE.rawValue, Month.MAX_VALUE.rawValue)
            .map { Month.of(it) }
            .toSet()
    }

    private fun replaceWordValuesToNumericalValues(cronArgument: String): String {
        val replacements = java.time.Month.values()
            .map { it.name.substring(0, 3) to (it.value).toString() }
        return cronArgument.replaceIgnoreCase(replacements)
    }

    override fun getProducedTemporalClass() = Month::class.java
}
