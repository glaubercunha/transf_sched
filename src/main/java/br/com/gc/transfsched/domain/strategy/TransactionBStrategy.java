package br.com.gc.transfsched.domain.strategy;

import java.math.BigDecimal;

import br.com.gc.transfsched.domain.exception.InvalidDateException;
import br.com.gc.transfsched.infrastructure.entity.Transfer;

public class TransactionBStrategy extends TransactionStrategyAbstract{

    public TransactionBStrategy() {
		super(10);
	}

	@Override
	public void calculateRate(Transfer transfer) throws InvalidDateException {

		BigDecimal days = new BigDecimal(compareDates(transfer));
		BigDecimal bigDecimal = new BigDecimal(12);
		transfer.setRate(bigDecimal.multiply(days));
	}
}
