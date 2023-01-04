package tests.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import entities.Account;
import tests.factory.AccountFactory;

public class AccountTest {

	@Test
	public void depositShouldIncreaseBalanceWhenPositiveAmount() {
		// Arrange
		Account account = AccountFactory.createEmptyAccount();
		double amount = 200.0;
		double expectedValue = 196.0;

		// Act
		account.deposit(amount);

		// Assert
		Assertions.assertEquals(expectedValue, account.getBalance());
	}

	@Test
	public void depositShouldDoNothingWhenNegativeAmount() {
		// Arrange
		Account account = AccountFactory.createAccount(100.0);
		double amount = -200.0;
		double expectedValue = 100.0;

		// Act
		account.deposit(amount);

		// Assert
		Assertions.assertEquals(expectedValue, account.getBalance());
	}

	@Test
	public void fullWithdrawShouldClearBalanceAndReturnFullBalance() {
		// Arrange
		double initialValue = 800.0;
		Account account = AccountFactory.createAccount(initialValue);
		double expectedValue = 0.0;

		// Act
		double withdrawnAmount = account.fullWithdraw();

		// Assert
		Assertions.assertEquals(expectedValue, account.getBalance());
		Assertions.assertEquals(withdrawnAmount, initialValue);
	}

	@Test
	public void withdrawShouldDecreaseBalanceWhenSufficientBalance() {
		// Arrange
		Account account = AccountFactory.createAccount(800.0);
		double amount = 500.0;
		double expectedValue = 300.0;

		// Act
		account.withdraw(amount);

		// Assert
		Assertions.assertEquals(expectedValue, account.getBalance());
	}

	@Test
	public void withDrawShouldThrowExceptionWhenInsufficientBalance() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Account account = AccountFactory.createAccount(800.0);
			account.withdraw(801.0);
		});
	}
}
