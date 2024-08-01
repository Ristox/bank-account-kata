package ee.rsx.kata.codurance.bank

import ee.rsx.kata.codurance.bank.account.Account
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class AccountTest {

  private lateinit var account: AccountService

  @BeforeEach
  fun setup() {
    account = Account()
  }

  @Test
  fun `test`() {
    TODO("fails right now")
  }
}
