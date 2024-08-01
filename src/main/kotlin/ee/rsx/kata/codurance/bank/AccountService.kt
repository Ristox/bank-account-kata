package ee.rsx.kata.codurance.bank

interface AccountService {

  fun deposit(amount: Int)
  fun withdraw(amount: Int)
  fun printStatement()
}
