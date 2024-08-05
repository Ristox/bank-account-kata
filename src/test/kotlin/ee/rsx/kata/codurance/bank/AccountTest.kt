package ee.rsx.kata.codurance.bank

import ee.rsx.kata.codurance.bank.api.BankAccount
import ee.rsx.kata.codurance.bank.core.Account
import ee.rsx.kata.codurance.bank.core.statement.StatementPrinter
import ee.rsx.kata.codurance.bank.core.transaction.Transaction
import ee.rsx.kata.codurance.bank.core.transaction.TransactionStorage
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
internal class AccountTest {

  @Mock
  private lateinit var transactions: TransactionStorage

  @Mock
  private lateinit var statementPrinter: StatementPrinter

  private lateinit var account: BankAccount

  @BeforeEach
  fun setup() {
    account = Account(transactions, statementPrinter)
  }

  @Test
  fun `depositing an amount adds a new deposit with the given amount`() {
    account.deposit(789)

    verify(transactions).addDeposit(789)
  }

  @Test
  fun `withdrawing an amount does not add a new withdrawal, when the amount isn't available for withdrawal`() {
    whenever(transactions.isAvailable(333)).thenReturn(false)

    account.withdraw(333)

    verify(transactions, never()).addWithdrawal(any())
  }

  @Test
  fun `withdrawn amount is stored as a new transaction, when the amount is available for withdrawal`() {
    whenever(transactions.isAvailable(333)).thenReturn(true)

    account.withdraw(333)

    verify(transactions).addWithdrawal(333)
  }

  @Test
  fun `printing a statement prints all stored transactions`() {
    val storedTransactions = listOf(Transaction(123))
    whenever(transactions.listAll()).thenReturn(storedTransactions)

    account.printStatement()

    verify(statementPrinter).print(storedTransactions)
  }
}
