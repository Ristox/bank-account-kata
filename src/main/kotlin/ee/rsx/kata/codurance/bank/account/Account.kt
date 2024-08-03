package ee.rsx.kata.codurance.bank.account

import ee.rsx.kata.codurance.bank.AccountService
import ee.rsx.kata.codurance.bank.Console

class Account(
  private val console: Console
) : AccountService {

  override fun deposit(amount: Int) {
  }

  override fun withdraw(amount: Int) {
  }

  override fun printStatement() {
  }
}
