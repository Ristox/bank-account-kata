package ee.rsx.kata.codurance.bank.account

import ee.rsx.kata.codurance.bank.Console
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ofPattern


class StatementPrinter(private val console: Console) {

  fun print(transactions: List<Transaction>) {
    console.printLine(STATEMENT_HEADER)

    var runningTotal = 0
    transactions.map {
      runningTotal += it.amount
      it to runningTotal
    }
      .reversed()
      .forEach { (transaction, runningTotal) ->
        console.printLine(transaction.asLineWith(runningTotal))
      }
  }

  private fun Transaction.asLineWith(runningTotal: Int) =
      "${date.format()}  | ${amount.format()}   | ${runningTotal.format()}   |"


  private fun LocalDate.format() = this.format(ofPattern(DATE_FORMAT))

  private fun Int.format() = BigDecimal(this).setScale(2)

  companion object {
    private const val STATEMENT_HEADER = "DATE        | AMOUNT   | BALANCE  |"

    const val DATE_FORMAT = "dd/MM/yyyy"
  }
}

