package org.shift4.cronexpressionparser.argumentparser

import org.shift4.cronexpressionparser.CronTemporal

sealed interface CronTemporalArgumentParser<T : CronTemporal> {

    fun parse(cronArgument: String): Set<T>

    fun getProducedTemporalClass(): Class<T>
}



