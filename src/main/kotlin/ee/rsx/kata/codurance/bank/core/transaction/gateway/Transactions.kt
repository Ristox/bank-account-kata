package ee.rsx.kata.codurance.bank.core.transaction.gateway

import ee.rsx.kata.codurance.bank.core.transaction.Transaction

interface Transactions {

  fun addDeposit(amount: Int)
  fun addWithdrawal(amount: Int)
  fun isAvailable(amount: Int): Boolean
  fun listAll(): List<Transaction>
}
