package br.com.gc.transfsched.domain.strategy;

import br.com.gc.transfsched.domain.exception.InvalidDateException;
import br.com.gc.transfsched.infrastructure.entity.Transfer;

public interface TransactionStrategyIf {

	public void calculateRate(Transfer transfer) throws InvalidDateException;

	public boolean isValid(Transfer transfer) throws InvalidDateException;

}