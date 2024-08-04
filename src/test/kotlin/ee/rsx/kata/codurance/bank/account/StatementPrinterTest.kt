package ee.rsx.kata.codurance.bank.account

import ee.rsx.kata.codurance.bank.Console
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.verify

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
}
