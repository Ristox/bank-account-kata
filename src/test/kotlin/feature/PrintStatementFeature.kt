package feature

import ee.rsx.kata.codurance.bank.AccountService
import ee.rsx.kata.codurance.bank.Console
import ee.rsx.kata.codurance.bank.account.Account
import ee.rsx.kata.codurance.bank.account.StatementPrinter
import ee.rsx.kata.codurance.bank.account.TransactionStorage
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.inOrder
import org.mockito.junit.jupiter.MockitoExtension
import java.time.Clock
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
internal class PrintStatementFeature {

  @Mock
  private lateinit var clock: Clock

  @Mock
  private lateinit var console: Console

  private lateinit var account: AccountService

  @BeforeEach
  fun setup() {
    account = Account(TransactionStorage(clock), StatementPrinter(console))
  }

  @Test
  fun `prints account statement containing all of its transactions to the console`() {
    account.deposit(1000)
    account.withdraw(100)
    account.deposit(500)

    account.printStatement()

    console.let {
      inOrder(it).apply {
        verify(it).printLine("DATE        | AMOUNT   | BALANCE  |")
        verify(it).printLine("10/04/2024  | 500.00   | 1400.00  |")
        verify(it).printLine("02/04/2024  | -100.00  | 900.00   |")
        verify(it).printLine("01/04/2024  | 1000.00  | 1000.00  |")
      }
    }
  }
}
