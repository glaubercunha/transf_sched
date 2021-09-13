package br.com.gc.transfsched.domain.service;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import br.com.gc.transfsched.domain.exception.InvalidDateException;
import br.com.gc.transfsched.domain.exception.TransactionNotFoundException;
import br.com.gc.transfsched.domain.strategy.TransactionStrategyContext;
import br.com.gc.transfsched.infrastructure.entity.Transfer;
import br.com.gc.transfsched.infrastructure.repository.TransferRepository;

@Service
@ComponentScan(basePackages = {"br.com.transf.infrastructure.repository"})
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    public List<Transfer> findAll() {
        List<Transfer> findAll = (List<Transfer>) this.transferRepository.findAll();
		return findAll;
    }
    
    public Transfer save(Transfer transfer) throws TransactionNotFoundException, InvalidDateException{
    	
    	TransactionStrategyContext tsContext = new TransactionStrategyContext();
    	Date dtScheduling = new Date();
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(dtScheduling);
    	cal.set(Calendar.HOUR_OF_DAY, 0);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MILLISECOND, 0);
		transfer.setDtScheduling(cal.getTime());
    	tsContext.calculateRate(transfer);
    	return this.transferRepository.save(transfer);
    }
}