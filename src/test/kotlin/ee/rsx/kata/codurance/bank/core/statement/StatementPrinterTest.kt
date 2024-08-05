package ee.rsx.kata.codurance.bank.core.statement

import ee.rsx.kata.codurance.bank.core.statement.StatementPrinter.Companion.DATE_FORMAT
import ee.rsx.kata.codurance.bank.core.statement.gateway.Console
import ee.rsx.kata.codurance.bank.core.transaction.Transaction
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.verify
import java.time.LocalDate.now
import java.time.format.DateTimeFormatter.ofPattern

@ExtendWith(MockitoExtension::class)
internal class StatementPrinterTest {

  @Mock
  private lateinit var console: Console
  
  private lateinit var statementPrinter: StatementPrinter

  @BeforeEach
  fun setup() {
    statementPrinter = StatementPrinter(console)
  }
  
  @Test
  fun `prints only the statement header to console in case of empty transactions list`() {
    statementPrinter.print(transactions = emptyList())
    
    verify(console).printLine("DATE        | AMOUNT   | BALANCE  |")
  }

  @Test
  fun `prints the provided single transaction to console, after the statement header`() {
    val transactions = listOf(Transaction(555))

    statementPrinter.print(transactions)

    val todaysDatePrinted = now().format(ofPattern(DATE_FORMAT))
    console.let {
      inOrder(it).apply {
        verify(it).printLine("DATE        | AMOUNT   | BALANCE  |")
        verify(it).printLine("$todaysDatePrinted  | 555.00   | 555.00   |")
      }
    }
  }

  @Test
  fun `prints all provided transactions to console in descending order, after the statement header`() {
    val transactions = listOf(
      Transaction(780),
      Transaction(-180),
      Transaction(2200),
      Transaction(0),
      Transaction(150),
      Transaction(-499)
    )

    statementPrinter.print(transactions)

    val todaysDatePrinted = now().format(ofPattern(DATE_FORMAT))
    console.let {
      inOrder(it).apply {
        verify(it).printLine("DATE        | AMOUNT   | BALANCE  |")
        verify(it).printLine("$todaysDatePrinted  | -499.00   | 2451.00   |")
        verify(it).printLine("$todaysDatePrinted  | 150.00   | 2950.00   |")
        verify(it).printLine("$todaysDatePrinted  | 0.00   | 2800.00   |")
        verify(it).printLine("$todaysDatePrinted  | 2200.00   | 2800.00   |")
        verify(it).printLine("$todaysDatePrinted  | -180.00   | 600.00   |")
        verify(it).printLine("$todaysDatePrinted  | 780.00   | 780.00   |")
        verifyNoMoreInteractions()
      }
    }
  }
}
