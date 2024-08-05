package ee.rsx.kata.codurance.bank.api

interface BankAccount {

  fun deposit(amount: Int)
  fun withdraw(amount: Int)
  fun printStatement()
}
