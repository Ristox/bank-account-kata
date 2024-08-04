package ee.rsx.kata.codurance.bank.account

class TransactionStorage {
  fun addDeposit(amount: Int) {
    // TODO
  }

  fun addWithdrawal(amount: Int) {
    TODO("Not yet implemented")
  }

  fun isAvailable(amount: Int): Boolean {
    TODO("Not yet implemented")
  }

  fun listAll(): List<Transaction> {
    return listOf(Transaction(580))
  }
}
