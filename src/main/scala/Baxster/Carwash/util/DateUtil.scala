package Baxster.Carwash.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


object DateUtil {

  val DATE_PATTERN = "dd/MM/yyyy"
  val DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN)

  class DateFormatter(date: LocalDate) {
    def asString: String = {
      if (date == null) {
        return null
      }
      return DATE_FORMATTER.format(date)
    }
  }

  class StringFormatter(data: String) {
    def parseLocalDate: LocalDate = {
      try {
        LocalDate.parse(data, DATE_FORMATTER)
      } catch {
        case e: DateTimeParseException => null
      }
    }

    def isValid: Boolean = {
      parseLocalDate != null
    }
  }

  implicit def dateToFormatter(date: LocalDate): DateFormatter = new DateFormatter(date)
  implicit def stringToFormatter(data: String): StringFormatter = new StringFormatter(data)
}

