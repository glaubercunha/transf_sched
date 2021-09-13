package br.com.gc.transfsched.domain.strategy;

import java.math.BigDecimal;

import br.com.gc.transfsched.domain.exception.InvalidDateException;
import br.com.gc.transfsched.infrastructure.entity.Transfer;

public class TransactionC40MoreStrategy extends TransactionCStrategy{

    public TransactionC40MoreStrategy() {
		super(40, new BigDecimal(0.02));
	}

	@Override
	public boolean isValid(Transfer transfer) throws InvalidDateException {
		boolean more40Days = compareDates(transfer) > getValidDays();
		boolean more100k = new BigDecimal(100000).compareTo(transfer.getMoney()) < 0;
		
		return more40Days && more100k;
	}	
}
