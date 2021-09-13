package br.com.gc.transfsched.domain.strategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.gc.transfsched.domain.exception.InvalidDateException;
import br.com.gc.transfsched.domain.exception.TransactionNotFoundException;
import br.com.gc.transfsched.infrastructure.entity.Transfer;

public class TransactionStrategyContext {

    private List<TransactionStrategyIf> transactions;
	
	public TransactionStrategyContext(){
		transactions = new ArrayList<TransactionStrategyIf>();
		transactions.add(new TransactionAStrategy());
		transactions.add(new TransactionBStrategy());
		transactions.add(new TransactionCStrategy(20, new BigDecimal(0.08)));
		transactions.add(new TransactionCStrategy(30, new BigDecimal(0.06)));
		transactions.add(new TransactionCStrategy(40, new BigDecimal(0.04)));
		transactions.add(new TransactionC40MoreStrategy());
	}
	public void calculateRate(Transfer transfer) throws TransactionNotFoundException, InvalidDateException{
		for (TransactionStrategyIf transaction : transactions) {
			if(transaction.isValid(transfer)){
				transaction.calculateRate(transfer);
				break;
			}
		}
		if (transfer.getRate() == null){
			throw new TransactionNotFoundException();
		}	
	}
}