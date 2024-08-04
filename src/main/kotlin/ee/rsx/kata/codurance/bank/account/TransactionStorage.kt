package ee.rsx.kata.codurance.bank.account

class TransactionStorage {

  private val transactions: MutableList<Transaction> = mutableListOf()

  fun addDeposit(amount: Int) {
    transactions.add(Transaction(amount))
  }

  fun isAvailable(amount: Int): Boolean {
    return transactions.sumOf { it.amount } >= amount
  }

  fun addWithdrawal(amount: Int) {
    transactions.add(Transaction(-amount))
  }

  fun listAll(): List<Transaction> {
    return transactions.toList()
  }
}
