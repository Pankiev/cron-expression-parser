package org.shift4.cronexpressionparser.argumentparser

import org.shift4.cronexpressionparser.CronParserIllegalArgumentException

internal class NumericalCronArgumentParser {

    fun parseCronArgument(input: String, minValue: Int, maxValue: Int): Set<Int> {
        val cronSubExpressions = input.split(Regex(","))
        return cronSubExpressions.asSequence().flatMap { parseCronSubExpression(it, minValue, maxValue) }.toSet()
    }

    private fun parseCronSubExpression(cronSubExpression: String, minValue: Int, maxValue: Int): Sequence<Int> {
        return FULL_RANGE_CRON_SUB_EXPRESSION_REGEX.matchEntire(cronSubExpression)?.let {
            rangeCronSequence(minValue, maxValue, it)
        } ?: RANGE_CRON_SUB_EXPRESSION_REGEX.matchEntire(cronSubExpression)?.let {
            customRangeCronSequence(it, cronSubExpression)
        } ?: SINGLE_VALUE_CRON_SUB_EXPRESSION_REGEX.matchEntire(cronSubExpression)?.let {
            singleValueCronSequence(it, maxValue)
        } ?: throw CronParserIllegalArgumentException("Invalid sub-expression: $cronSubExpression")
    }

    private fun rangeCronSequence(minValue: Int, maxValue: Int, it: MatchResult) =
        (minValue..maxValue step (it.intValue(STEP_GROUP_NAME) ?: DEFAULT_STEP)).asSequence()

    private fun customRangeCronSequence(it: MatchResult, cronSubExpression: String): Sequence<Int> {
        val minValue = it.intValue(MIN_VALUE_GROUP_NAME)!!
        val maxValue = it.intValue(MAX_VALUE_GROUP_NAME)!!
        if (minValue > maxValue) {
            throw CronParserIllegalArgumentException("Invalid sub-expression: $cronSubExpression")
        }
        return (minValue..maxValue step (it.intValue(STEP_GROUP_NAME) ?: DEFAULT_STEP)).asSequence()
    }

    private fun singleValueCronSequence(it: MatchResult, maxValue: Int): Sequence<Int> {
        return if (it.intValue(STEP_GROUP_NAME) == null) {
            sequenceOf(it.intValue(SINGLE_VALUE_GROUP_NAME)!!)
        } else {
            val minValueForRange = it.intValue(SINGLE_VALUE_GROUP_NAME)!!
            rangeCronSequence(minValueForRange, maxValue, it)
        }
    }

    private fun MatchResult.intValue(groupName: String) = groups[groupName]?.value?.toInt()

    companion object {
        private const val FULL_RANGE_GROUP_NAME = "fullRange"
        private const val MIN_VALUE_GROUP_NAME = "minValue"
        private const val MAX_VALUE_GROUP_NAME = "maxValue"
        private const val SINGLE_VALUE_GROUP_NAME = "singleValue"
        private const val STEP_GROUP_NAME = "step"
        private val FULL_RANGE_CRON_SUB_EXPRESSION_REGEX = Regex("(?<$FULL_RANGE_GROUP_NAME>\\*)(?:/(?<$STEP_GROUP_NAME>\\d+))?")
        private val RANGE_CRON_SUB_EXPRESSION_REGEX = Regex("(?<$MIN_VALUE_GROUP_NAME>\\d+)-(?<$MAX_VALUE_GROUP_NAME>\\d+)(?:/(?<$STEP_GROUP_NAME>\\d+))?")
        private val SINGLE_VALUE_CRON_SUB_EXPRESSION_REGEX = Regex("(?<$SINGLE_VALUE_GROUP_NAME>\\d+)(?:/(?<$STEP_GROUP_NAME>\\d+))?")
        private const val DEFAULT_STEP = 1
    }
}
