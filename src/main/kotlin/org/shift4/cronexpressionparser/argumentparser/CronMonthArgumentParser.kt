package org.shift4.cronexpressionparser.argumentparser

import org.shift4.cronexpressionparser.Month

internal class CronMonthArgumentParser : CronTemporalArgumentParser<Month> {

    private val numericalCronArgumentParser = NumericalCronArgumentParser()

    override fun parse(cronArgument: String): Set<Month> {
        return numericalCronArgumentParser.parseCronArgument(cronArgument, Month.MIN_VALUE.rawValue, Month.MAX_VALUE.rawValue)
            .map { Month.of(it) }
            .toSet()
    }

    override fun getProducedTemporalClass() = Month::class.java
}
