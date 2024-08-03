package ee.rsx.kata.codurance.bank.feature

import ee.rsx.kata.codurance.bank.AccountService
import ee.rsx.kata.codurance.bank.Console
import ee.rsx.kata.codurance.bank.account.Account
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
class PrintStatementFeature() {

  @Mock
  private lateinit var console: Console

  private lateinit var account: AccountService

  @BeforeEach
  fun setup() {
    account = Account(console)
  }

  @Test
  fun `prints account statement containing all transactions to the console`() {
    account.deposit(1000)
    account.withdraw(100)
    account.deposit(500)

    account.printStatement()

    verify(console).printLine("DATE        | AMOUNT   | BALANCE  |")
    verify(console).printLine("10/04/2024  | 500.00   | 1400.00  |")
    verify(console).printLine("02/04/2024  | -100.00  | 900.00   |")
    verify(console).printLine("01/04/2024  | 1000.00  | 1000.00  |")
  }
}
