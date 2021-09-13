package br.com.gc.transfsched.domain.strategy;

import java.math.BigDecimal;

import br.com.gc.transfsched.infrastructure.entity.Transfer;

public class TransactionCStrategy extends TransactionStrategyAbstract{

	private BigDecimal percent;
	
	public TransactionCStrategy(Integer validDays, BigDecimal percent) {
		super(validDays);
		this.percent = percent;
	}

	@Override
	public void calculateRate(Transfer transfer) {
		transfer.setRate(transfer.getMoney().multiply(this.percent));
	}
}
