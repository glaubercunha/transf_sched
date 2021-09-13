package br.com.gc.transfsched.domain.exception;

public class TransactionNotFoundException extends Exception {

	public TransactionNotFoundException() {
		super("Transaction not found");
	}
}
