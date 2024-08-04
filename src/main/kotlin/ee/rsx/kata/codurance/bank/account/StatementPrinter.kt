package ee.rsx.kata.codurance.bank.account

import ee.rsx.kata.codurance.bank.Console
import java.time.format.DateTimeFormatter.ofPattern

class StatementPrinter(private val console: Console) {

  fun print(transactions: List<Transaction>) {
    console.printLine("DATE        | AMOUNT   | BALANCE  |")

    if (transactions.isNotEmpty()) {
      console.printLine(transactions.first().asLine())
    }
  }

  private fun Transaction.asLine(): String {
    return "${date.format(ofPattern(DATE_FORMAT))}  | $amount.00   | $amount.00   |"
  }

  companion object {
    const val DATE_FORMAT = "dd/MM/yyyy"
  }
}

