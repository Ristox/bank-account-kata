package ee.rsx.kata.codurance.bank.account

import ee.rsx.kata.codurance.bank.Console
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
  private lateinit var console: Console

  @Test
  fun `deposited amount is stored as a new transaction with the amount`() {
    val account = Account(console, transactions)

    account.deposit(789)

    verify(transactions).store(Transaction(789))
  }

  @Test
  fun `withdrawn amount is not stored as a new transaction, when the amount isn't available for withdrawal`() {
    val account = Account(console, transactions)
    whenever(transactions.isAvailable(333)).thenReturn(false)

    account.withdraw(333)

    verify(transactions, never()).store(any())
  }

  @Test
  fun `withdrawn amount is stored as a new transaction, when the amount is available for withdrawal`() {
    val account = Account(console, transactions)
    whenever(transactions.isAvailable(333)).thenReturn(true)

    account.withdraw(333)

    verify(transactions).store(Transaction(-333))
  }
}
