package ee.rsx.kata.codurance.bank.account

import java.time.Clock
import java.time.ZoneOffset.UTC

class TransactionStorage(private val clock: Clock) {

  private val transactions: MutableList<Transaction> = mutableListOf()

  fun addDeposit(amount: Int) {
    transactions.add(Transaction(amount, clock.today()))
  }

  fun isAvailable(amount: Int) =
    runningBalance() >= amount

  fun addWithdrawal(amount: Int) {
    transactions.add(Transaction(-amount, clock.today()))
  }

  fun listAll() = transactions.toList()

  private fun runningBalance() =
    transactions.sumOf { it.amount }

  private fun Clock.today() = instant().atZone(UTC).toLocalDate()
}
