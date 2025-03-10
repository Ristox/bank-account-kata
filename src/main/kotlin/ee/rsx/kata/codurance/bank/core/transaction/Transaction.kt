package ee.rsx.kata.codurance.bank.core.transaction

import java.time.LocalDate

data class Transaction(
  val amount: Int,
  val date: LocalDate = LocalDate.now()
)
