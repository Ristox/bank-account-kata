package ee.rsx.kata.codurance.bank.account

import ee.rsx.kata.codurance.bank.Console
import java.math.BigDecimal
import java.time.format.DateTimeFormatter.ofPattern

class StatementPrinter(private val console: Console) {

  fun print(transactions: List<Transaction>) {
    console.printLine("DATE        | AMOUNT   | BALANCE  |")

    var runningTotal = 0
    transactions.map {
      runningTotal += it.amount
      it to runningTotal
    }
      .reversed()
      .forEach { (transaction, runningTotal) ->
        console.printLine(transaction.asLine(runningTotal))
      }
  }

  private fun Transaction.asLine(runningTotal: Int) =
      "${date.format(ofPattern(DATE_FORMAT))}  | ${amount.format()}   | ${runningTotal.format()}   |"


  private fun Int.format() = BigDecimal(this).setScale(2)

  companion object {
    const val DATE_FORMAT = "dd/MM/yyyy"
  }
}

