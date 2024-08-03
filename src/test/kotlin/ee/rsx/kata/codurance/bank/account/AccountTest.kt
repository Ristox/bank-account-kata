package ee.rsx.kata.codurance.bank.account

import ee.rsx.kata.codurance.bank.Console
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.verify
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
internal class AccountTest {

  @Mock
  private lateinit var transactions: TransactionStorage

  @Mock
  private lateinit var console: Console

  @Test
  fun `deposited amount is printed out as a statement line`() {
    val account = Account(console, transactions)

    account.deposit(789)

    verify(transactions).add(Transaction(789))
  }
}
