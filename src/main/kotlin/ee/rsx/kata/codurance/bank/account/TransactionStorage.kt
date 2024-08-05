package ee.rsx.kata.codurance.bank.account

import java.time.Clock

class TransactionStorage(private val clock: Clock) {

  private val transactions: MutableList<Transaction> = mutableListOf()

  fun addDeposit(amount: Int) {
    transactions.add(Transaction(amount))
  }

  fun isAvailable(amount: Int) =
    runningBalance() >= amount

  fun addWithdrawal(amount: Int) {
    transactions.add(Transaction(-amount))
  }

  fun listAll() = transactions.toList()

  private fun runningBalance() =
    transactions.sumOf { it.amount }
}
