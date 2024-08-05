package feature

import ee.rsx.kata.codurance.bank.Account
import ee.rsx.kata.codurance.bank.api.BankAccount
import ee.rsx.kata.codurance.bank.statement.Console
import ee.rsx.kata.codurance.bank.statement.StatementPrinter
import ee.rsx.kata.codurance.bank.transaction.TransactionStorage
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.inOrder
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.given
import java.time.Clock
import java.time.LocalDate
import java.time.LocalDate.of
import java.time.ZoneOffset.UTC
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
internal class PrintStatementFeature {

  @Mock
  private lateinit var clock: Clock

  @Mock
  private lateinit var console: Console

  private lateinit var account: BankAccount

  @BeforeEach
  fun setup() {
    account = Account(TransactionStorage(clock), StatementPrinter(console))
  }

  @Test
  fun `prints account statement containing all of its transactions to the console`() {
    given(clock.instant())
      .willReturn(
        of(2024, 4, 1).instant(),
        of(2024, 4, 2).instant(),
        of(2024, 4, 10).instant(),
      )

    account.deposit(1000)
    account.withdraw(100)
    account.deposit(500)

    account.printStatement()

    console.let {
      inOrder(it).apply {
        verify(it).printLine("DATE        | AMOUNT   | BALANCE  |")
        verify(it).printLine("10/04/2024  | 500.00   | 1400.00   |")
        verify(it).printLine("02/04/2024  | -100.00   | 900.00   |")
        verify(it).printLine("01/04/2024  | 1000.00   | 1000.00   |")
      }
    }
  }

  private fun LocalDate.instant() = atStartOfDay().toInstant(UTC)
}
