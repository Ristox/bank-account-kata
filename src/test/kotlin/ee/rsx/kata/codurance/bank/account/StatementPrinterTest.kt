package ee.rsx.kata.codurance.bank.account

import ee.rsx.kata.codurance.bank.Console
import ee.rsx.kata.codurance.bank.account.StatementPrinter.Companion.DATE_FORMAT
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
class StatementPrinterTest {

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
  fun `prints the added transaction to console, after the statement header`() {
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
}
