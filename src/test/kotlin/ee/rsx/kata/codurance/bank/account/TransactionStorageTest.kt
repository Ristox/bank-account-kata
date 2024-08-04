package ee.rsx.kata.codurance.bank.account

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class TransactionStorageTest {

  private lateinit var transactionStorage: TransactionStorage

  @BeforeEach
  fun setup() {
    transactionStorage = TransactionStorage()
  }

  @Test
  fun `adding a deposit adds a new positive transaction of the given amount`() {
    transactionStorage.addDeposit(580)

    assertThat(transactionStorage.listAll())
      .containsExactly(Transaction(580))
  }

  @Test
  fun `adding two deposits adds a two new positive transaction of the given amount`() {
    transactionStorage.addDeposit(580)
    transactionStorage.addDeposit(799)

    assertThat(transactionStorage.listAll())
      .containsExactly(Transaction(580), Transaction(799))
  }
}
