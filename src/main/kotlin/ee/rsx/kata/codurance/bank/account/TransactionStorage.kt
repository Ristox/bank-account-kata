package ee.rsx.kata.codurance.bank.account

class TransactionStorage {

  private val transactions: MutableList<Transaction> = mutableListOf()

  fun addDeposit(amount: Int) {
    transactions.add(Transaction(amount))
  }

  fun addWithdrawal(amount: Int) {
    TODO("Not yet implemented")
  }

  fun isAvailable(amount: Int): Boolean {
    TODO("Not yet implemented")
  }

  fun listAll(): List<Transaction> {
    return transactions.toList()
  }
}
