package br.com.gc.transfsched.domain.strategy;

import java.math.BigDecimal;

import br.com.gc.transfsched.infrastructure.entity.Transfer;

public class TransactionAStrategy extends TransactionStrategyAbstract{

    public TransactionAStrategy() {
		super(0);
	}

	@Override
	public void calculateRate(Transfer transfer) {
		BigDecimal rate = new BigDecimal(3);
		BigDecimal valuePercent = transfer.getMoney().multiply(new BigDecimal(0.03));
		rate.add(valuePercent);
		transfer.setRate(rate.add(valuePercent));
	}
}
