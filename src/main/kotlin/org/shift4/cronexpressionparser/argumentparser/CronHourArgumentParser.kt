package org.shift4.cronexpressionparser.argumentparser

import org.shift4.cronexpressionparser.Hour

internal class CronHourArgumentParser : CronTemporalArgumentParser<Hour> {

    private val numericalCronArgumentParser = NumericalCronArgumentParser()

    override fun parse(cronArgument: String): Set<Hour> {
        return numericalCronArgumentParser.parseCronArgument(cronArgument, Hour.MIN_VALUE.rawValue, Hour.MAX_VALUE.rawValue)
            .map { Hour.of(it) }
            .toSet()
    }

    override fun getProducedTemporalClass() = Hour::class.java
}
