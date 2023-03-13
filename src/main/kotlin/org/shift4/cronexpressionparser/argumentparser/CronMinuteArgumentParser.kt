package org.shift4.cronexpressionparser.argumentparser

import org.shift4.cronexpressionparser.Minute

internal class CronMinuteArgumentParser : CronTemporalArgumentParser<Minute> {

    private val numericalCronArgumentParser = NumericalCronArgumentParser()

    override fun parse(cronArgument: String): Set<Minute> {
        return numericalCronArgumentParser.parseCronArgument(cronArgument, Minute.MIN_VALUE.rawValue, Minute.MAX_VALUE.rawValue)
            .map { Minute.of(it) }
            .toSet()
    }

    override fun getProducedTemporalClass() = Minute::class.java
}
