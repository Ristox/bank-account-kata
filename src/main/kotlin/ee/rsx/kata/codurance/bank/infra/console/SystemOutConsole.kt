package ee.rsx.kata.codurance.bank.infra.console

import ee.rsx.kata.codurance.bank.core.statement.gateway.Console

internal class SystemOutConsole: Console {
  override fun printLine(text: String) = println(text)
}
