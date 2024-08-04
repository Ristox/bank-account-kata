package ee.rsx.kata.codurance.bank.account

import ee.rsx.kata.codurance.bank.Console
import java.math.BigDecimal
import java.time.format.DateTimeFormatter.ofPattern

class StatementPrinter(private val console: Console) {

  fun print(transactions: List<Transaction>) {
    console.printLine("DATE        | AMOUNT   | BALANCE  |")

    if (transactions.isNotEmpty()) {
      console.printLine(transactions.first().asLine())
    }
  }

  private fun Transaction.asLine(): String =
    with(BigDecimal(amount).setScale(2)) {
      "${date.format(ofPattern(DATE_FORMAT))}  | $this   | $this   |"
    }

  companion object {
    const val DATE_FORMAT = "dd/MM/yyyy"
  }
}

