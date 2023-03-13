package org.shift4.cronexpressionparser

import java.io.PrintStream

class JobExecutionLogger(private val printStream: PrintStream = System.out) {

    fun logCronJob(cronJob: CronJob) {
        logSchedule(cronJob)
        logCommand(cronJob)
    }

    private fun logSchedule(cronJob: CronJob) {
        logScheduleTemporal("minute", cronJob.getTemporal(Minute::class.java))
        logScheduleTemporal("hour", cronJob.getTemporal(Hour::class.java))
        logScheduleTemporal("day of month", cronJob.getTemporal(DayOfMonth::class.java))
        logScheduleTemporal("month", cronJob.getTemporal(Month::class.java))
        logScheduleTemporal("day of week", cronJob.getTemporal(DayOfWeek::class.java))
    }

    private fun logScheduleTemporal(name: String, temporalValues: Set<CronTemporal>) {
        printStream.println("$name ${temporalValues.toSortedStringSeparatedBySpace()}")
    }

    private fun logCommand(cronJob: CronJob) {
        printStream.println("command ${cronJob.command}")
    }

    private fun Set<CronTemporal>.toSortedStringSeparatedBySpace() = this.asSequence()
        .map { it.rawValue }
        .sorted()
        .joinToString(separator = " ")
}
