package br.com.gc.transfsched.domain.strategy;

import java.time.Duration;

import br.com.gc.transfsched.domain.exception.InvalidDateException;
import br.com.gc.transfsched.infrastructure.entity.Transfer;

public abstract class TransactionStrategyAbstract implements TransactionStrategyIf{

	private Integer validDays;

	public TransactionStrategyAbstract(Integer validDays) {
		this.validDays = validDays;
	}

	public Long compareDates(Transfer transfer) throws InvalidDateException {
		Duration d = Duration.between(    transfer.getDtScheduling().toInstant()
										, transfer.getDtTransfer().toInstant()
			    					 );
		long days = d.toDays();
		if ( days < 0){
			throw new InvalidDateException();
		}
		return days;
	}
	
	public Integer getValidDays() {
		return validDays;
	}

	public boolean isValid(Transfer transfer) throws InvalidDateException{
		return compareDates(transfer) <= this.validDays;
	}

}