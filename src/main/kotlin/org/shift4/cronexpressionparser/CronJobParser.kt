package org.shift4.cronexpressionparser

import org.shift4.cronexpressionparser.argumentparser.CronTemporalArgumentParser

class CronJobParser(private val cronArgumentParsers: List<CronTemporalArgumentParser<out CronTemporal>>) {

    fun parse(expressionParameters: List<String>): CronJob {
        val expressionParametersIterator = expressionParameters.iterator()
        return CronJob(
            schedule = cronArgumentParsers.associate { it.getProducedTemporalClass() to it.parse(expressionParametersIterator.next()) },
            command = expressionParametersIterator.next()
        )
    }
}
