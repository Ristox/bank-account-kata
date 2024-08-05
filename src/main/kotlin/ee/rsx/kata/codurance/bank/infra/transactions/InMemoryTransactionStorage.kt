package ee.rsx.kata.codurance.bank.infra.transactions

import ee.rsx.kata.codurance.bank.core.transaction.Transaction
import ee.rsx.kata.codurance.bank.core.transaction.gateway.Transactions
import java.time.Clock
import java.time.ZoneOffset.UTC

class InMemoryTransactionStorage(private val clock: Clock) : Transactions {

  private val transactions: MutableList<Transaction> = mutableListOf()

  override fun addDeposit(amount: Int) {
    transactions.add(Transaction(amount, clock.today()))
  }

  override fun isAvailable(amount: Int) =
    runningBalance() >= amount

  override fun addWithdrawal(amount: Int) {
    transactions.add(Transaction(-amount, clock.today()))
  }

  override fun listAll() = transactions.toList()

  private fun runningBalance() =
    transactions.sumOf { it.amount }

  private fun Clock.today() = instant().atZone(UTC).toLocalDate()
}
