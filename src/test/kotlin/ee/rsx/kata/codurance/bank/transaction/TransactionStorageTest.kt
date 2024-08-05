package ee.rsx.kata.codurance.bank.transaction

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import java.time.Clock
import java.time.LocalDate
import java.time.Month.FEBRUARY
import java.time.ZoneOffset.UTC
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
internal class TransactionStorageTest {

  @Mock
  private lateinit var clock: Clock

  private lateinit var transactionStorage: TransactionStorage

  @BeforeEach
  fun setup() {
    transactionStorage = TransactionStorage(clock)
    whenTodayIs(LocalDate.now())
  }

  @Test
  fun `adding a deposit adds a new positive transaction of the given amount`() {
    transactionStorage.addDeposit(580)

    assertThat(transactionStorage.listAll())
      .containsExactly(Transaction(580))
  }

  @Test
  fun `adding two deposits adds a two new positive transaction of the given amount`() {
    with(transactionStorage) {
      addDeposit(580)
      addDeposit(799)
    }

    assertThat(transactionStorage.listAll())
      .containsExactly(Transaction(580), Transaction(799))
  }

  @Test
  fun `amount is available when running balance of transactions is greater than amount`() {
    with(transactionStorage) {
      addDeposit(300)
      addDeposit(201)
    }

    assertThat(transactionStorage.isAvailable(500))
      .isTrue()
  }

  @Test
  fun `amount is not available, when running balance of transactions is less than amount`() {
    with(transactionStorage) {
      addDeposit(300)
      addDeposit(199)
    }

    assertThat(transactionStorage.isAvailable(500))
      .isFalse()
  }

  @Test
  fun `amount is available, when running balance of transactions is equal to amount`() {
    with(transactionStorage) {
      addDeposit(300)
      addDeposit(100)
    }

    assertThat(transactionStorage.isAvailable(400))
      .isTrue()
  }

  @Test
  fun `adding withdrawal adds a new negative transaction of the given amount`() {
    transactionStorage.addWithdrawal(477)

    assertThat(transactionStorage.listAll())
      .containsExactly(Transaction(-477))
  }

  @Test
  fun `adding deposits and withdrawals results in expected running balance of all transactions`() {
    with(transactionStorage) {
      addDeposit(645)
      addWithdrawal(200)
      addDeposit(555)
      addWithdrawal(150)
      addDeposit(200)
    }

    val allTransactions = transactionStorage.listAll()

    val expectedRunningBalance = 645 - 200 + 555 - 150 + 200
    assertThat(allTransactions.sumOf { it.amount })
      .isEqualTo(expectedRunningBalance)
  }

  @Test
  fun `adds current date to each transaction`() {
    val expectedDate = LocalDate.now()
    with(transactionStorage) {
      addDeposit(450)
      addDeposit(1000)
      addWithdrawal(850)
      addDeposit(200)
      addDeposit(75)
    }

    assertThat(transactionStorage.listAll().map { it.date })
      .allMatch { it == expectedDate }
  }

  @Test
  fun `adds a custom date taken from Clock, to each transaction`() {
    val expectedDate = LocalDate.of(2022, FEBRUARY, 22)
    whenTodayIs(expectedDate)
    with(transactionStorage) {
      addDeposit(450)
      addDeposit(1000)
      addWithdrawal(850)
      addDeposit(200)
      addDeposit(75)
    }

    assertThat(transactionStorage.listAll().map { it.date })
      .allMatch { it == expectedDate }
  }

  private fun whenTodayIs(date: LocalDate) {
    whenever(clock.instant()).thenReturn(date.atStartOfDay().toInstant(UTC))
  }
}
