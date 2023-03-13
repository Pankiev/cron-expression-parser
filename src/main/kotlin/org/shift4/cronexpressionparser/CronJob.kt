package org.shift4.cronexpressionparser

typealias SameTypeClassToSetMap<T> = Map<Class<T>, Set<T>>
typealias CronSchedule = SameTypeClassToSetMap<out CronTemporal>

data class CronJob(val schedule: CronSchedule, val command: String) {
    fun <T : CronTemporal> getTemporal(type: Class<T>) = schedule[type] as Set<T>
}

sealed interface CronTemporal {
    val rawValue: Int
}

data class Minute private constructor(override val rawValue: Int) : CronTemporal {
    companion object {
        val MIN_VALUE = Minute(0)
        val MAX_VALUE = Minute(59)

        fun of(minute: Int): Minute {
            if (minute !in (MIN_VALUE.rawValue..MAX_VALUE.rawValue)) {
                throw CronParserIllegalArgumentException("Given minute $minute should be between ${MIN_VALUE.rawValue} and ${MAX_VALUE.rawValue}")
            }
            return Minute(minute)
        }
    }
}

data class Hour private constructor(override val rawValue: Int) : CronTemporal {
    companion object {
        val MIN_VALUE = Hour(0)
        val MAX_VALUE = Hour(23)

        fun of(hour: Int): Hour {
            if (hour !in (MIN_VALUE.rawValue..MAX_VALUE.rawValue)) {
                throw CronParserIllegalArgumentException("Given hour $hour should be between ${MIN_VALUE.rawValue} and ${MAX_VALUE.rawValue}")
            }
            return Hour(hour)
        }
    }
}

data class DayOfMonth private constructor(override val rawValue: Int) : CronTemporal {
    companion object {
        val MIN_VALUE = DayOfMonth(1)
        val MAX_VALUE = DayOfMonth(31)

        fun of(dayOfMonth: Int): DayOfMonth {
            if (dayOfMonth !in (MIN_VALUE.rawValue..MAX_VALUE.rawValue)) {
                throw CronParserIllegalArgumentException("Given dayOfMonth $dayOfMonth should be between ${MIN_VALUE.rawValue} and ${MAX_VALUE.rawValue}")
            }
            return DayOfMonth(dayOfMonth)
        }
    }
}

data class Month private constructor(override val rawValue: Int) : CronTemporal {
    companion object {
        val MIN_VALUE = Month(1)
        val MAX_VALUE = Month(12)

        fun of(month: Int): Month {
            if (month !in (MIN_VALUE.rawValue..MAX_VALUE.rawValue)) {
                throw CronParserIllegalArgumentException("Given month $month should be between ${MIN_VALUE.rawValue} and ${MAX_VALUE.rawValue}")
            }
            return Month(month)
        }
    }
}

data class DayOfWeek private constructor(override val rawValue: Int) : CronTemporal {
    companion object {
        val MIN_VALUE = DayOfWeek(0)
        val MAX_VALUE = DayOfWeek(6)

        fun of(dayOfWeek: Int): DayOfWeek {
            if (dayOfWeek !in (MIN_VALUE.rawValue..MAX_VALUE.rawValue)) {
                throw CronParserIllegalArgumentException("Given dayOfWeek $dayOfWeek should be between ${MIN_VALUE.rawValue} and ${MAX_VALUE.rawValue}")
            }
            return DayOfWeek(dayOfWeek)
        }
    }
}
