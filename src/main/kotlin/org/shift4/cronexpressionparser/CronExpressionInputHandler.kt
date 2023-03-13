package org.shift4.cronexpressionparser

import java.io.PrintStream

class CronExpressionInputHandler(
    private val printStream: PrintStream = System.out,
    private val jobExecutionLogger: JobExecutionLogger = JobExecutionLogger(),
    private val cronJobParserFactory: CronJobParserFactory = CronJobParserFactory()
) {

    fun handleInput(args: Array<String>) {
        try {
            doHandleInput(args)
        } catch (e: CronParserIllegalArgumentException) {
            printStream.println(e.message)
        }
    }

    private fun doHandleInput(args: Array<String>) {
        if (args.size != 1) {
            throw CronParserIllegalArgumentException("Cron expression should be passed as a single argument")
        }
        val expressionParameters = args[0].split(Regex("\\s+"))
        val cronJobParser = cronJobParserFactory.newInstance(expressionParameters)
        val cronJob = cronJobParser.parse(expressionParameters)
        jobExecutionLogger.logCronJob(cronJob)
    }

}
