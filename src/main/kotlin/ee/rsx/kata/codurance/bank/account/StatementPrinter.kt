package ee.rsx.kata.codurance.bank.account

import ee.rsx.kata.codurance.bank.Console
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ofPattern
import java.util.concurrent.atomic.AtomicInteger


class StatementPrinter(private val console: Console) {

  fun print(transactions: List<Transaction>) {
    console.printLine(STATEMENT_HEADER)

    printStatementLinesFor(transactions)
  }

  private fun printStatementLinesFor(transactions: List<Transaction>) {
    val runningTotal = AtomicInteger(0)
    transactions
      .map { it.asLineWith(runningTotal.addAndGet(it.amount)) }
      .reversed()
      .forEach(console::printLine)
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

