package ee.rsx.kata.codurance.bank.account

import ee.rsx.kata.codurance.bank.AccountService
import ee.rsx.kata.codurance.bank.Console

class Account(
  private val console: Console,
  private val transactions: TransactionStorage,
  private val statementPrinter: StatementPrinter
) : AccountService {

  override fun deposit(amount: Int) {
    transactions.addDeposit(amount)
  }

  override fun withdraw(amount: Int) {
    if (transactions.isAvailable(amount)) {
      transactions.addWithdrawal(amount)
    }
  }

  override fun printStatement() {
    statementPrinter.print(transactions.listAll())
  }
}
