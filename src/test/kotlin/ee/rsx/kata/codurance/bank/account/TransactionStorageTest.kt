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

  @Test
  fun `amount is available when running balance of transactions is greater than amount`() {
    transactionStorage.addDeposit(300)
    transactionStorage.addDeposit(201)

    assertThat(transactionStorage.isAvailable(500))
      .isTrue()
  }

  @Test
  fun `amount is not available, when running balance of transactions is less than amount`() {
    transactionStorage.addDeposit(300)
    transactionStorage.addDeposit(199)

    assertThat(transactionStorage.isAvailable(500))
      .isFalse()
  }

  @Test
  fun `amount is available, when running balance of transactions is equal to amount`() {
    transactionStorage.addDeposit(300)
    transactionStorage.addDeposit(100)

    assertThat(transactionStorage.isAvailable(400))
      .isTrue()
  }

  @Test
  fun `adding withdrawal adds a new negative transaction of the given amount`() {
    transactionStorage.addWithdrawal(477)

    assertThat(transactionStorage.listAll())
      .containsExactly(Transaction(-477))
  }
}
