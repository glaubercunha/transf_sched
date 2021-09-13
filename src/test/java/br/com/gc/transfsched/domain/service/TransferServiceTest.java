package br.com.gc.transfsched.domain.service;



import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gc.transfsched.domain.exception.InvalidDateException;
import br.com.gc.transfsched.domain.exception.TransactionNotFoundException;
import br.com.gc.transfsched.domain.strategy.TransactionStrategyAbstract;
import br.com.gc.transfsched.infrastructure.entity.Transfer;
import br.com.gc.transfsched.infrastructure.repository.TransferRepository;

@RunWith(SpringRunner.class)
public class TransferServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(TransferServiceTest.class);

	@Mock
    private TransferRepository repository;

	@InjectMocks
    private TransferService service;

    
	@Test
    public void testA() throws Exception {
		Date dtTransfer = getDate(0);
        Long creditAccount = 2L;
        Long debitAccount = 1L;
        BigDecimal money = new BigDecimal(100);
        Transfer transfer= new Transfer(dtTransfer, creditAccount,debitAccount,money); 

        this.service.save(transfer);
    	MathContext mc = new MathContext(1);
    	assertThat(transfer.getRate().round(mc)).isEqualTo(new BigDecimal(6).round(mc));
    }

    @Test
    public void testB() throws ParseException, TransactionNotFoundException, InvalidDateException {

    	Date dtTransfer = getDate(10);
        Long creditAccount = 2L;
        Long debitAccount = 1L;
        BigDecimal money = new BigDecimal(100);
        Transfer transfer= new Transfer(dtTransfer, creditAccount,debitAccount,money); 

        this.service.save(transfer);
    	MathContext mc = new MathContext(3);
    	assertThat(transfer.getRate().round(mc)).isEqualTo(new BigDecimal(120).round(mc));
    }
    

    @Test
    public void testC20() throws ParseException, TransactionNotFoundException, InvalidDateException {

    	Date dtTransfer = getDate(20);
        Long creditAccount = 2L;
        Long debitAccount = 1L;
        BigDecimal money = new BigDecimal(100);
        Transfer transfer = new Transfer(dtTransfer, creditAccount,debitAccount,money); 

        this.service.save(transfer);
    	MathContext mc = new MathContext(1);
    	assertThat(transfer.getRate().round(mc)).isEqualTo(new BigDecimal(8).round(mc));
    }  

    @Test
    public void testC30() throws ParseException, TransactionNotFoundException, InvalidDateException {

    	Date dtTransfer = getDate(30);
        Long creditAccount = 2L;
        Long debitAccount = 1L;
        BigDecimal money = new BigDecimal(100);
        Transfer transfer= new Transfer(dtTransfer, creditAccount,debitAccount,money); 

        this.service.save(transfer);
    	MathContext mc = new MathContext(1);
    	assertThat(transfer.getRate().round(mc)).isEqualTo(new BigDecimal(6).round(mc));
    }  

    @Test
    public void testC40() throws ParseException, TransactionNotFoundException, InvalidDateException {

    	Date dtTransfer = getDate(40);
        Long creditAccount = 2L;
        Long debitAccount = 1L;
        BigDecimal money = new BigDecimal(100);
        Transfer transfer= new Transfer(dtTransfer, creditAccount,debitAccount,money); 

        this.service.save(transfer);
    	MathContext mc = new MathContext(1);
    	assertThat(transfer.getRate().round(mc)).isEqualTo(new BigDecimal(4).round(mc));
    }    

    @Test
    public void testC40More() throws ParseException, TransactionNotFoundException, InvalidDateException {

    	Date dtTransfer = getDate(41);
        Long creditAccount = 2L;
        Long debitAccount = 1L;
        BigDecimal money = new BigDecimal(101000);
        Transfer transfer= new Transfer(dtTransfer, creditAccount,debitAccount,money); 

        this.service.save(transfer);
        MathContext mc = new MathContext(4);
    	assertThat(transfer.getRate().round(mc)).isEqualTo(new BigDecimal(2020).round(mc));
    }   

    @Test(expected = TransactionNotFoundException.class)
    public void testC40Invalid() throws ParseException, TransactionNotFoundException, InvalidDateException {
        
    	Date dtTransfer = getDate(41);
        Long creditAccount = 2L;
        Long debitAccount = 1L;
        BigDecimal money = new BigDecimal(100000);
        Transfer transfer = new Transfer(dtTransfer, creditAccount,debitAccount,money); 
       	this.service.save(transfer);
    }

	private Date getDate(Integer addDays) {
		Date date = new Date();
		Calendar cal = Calendar.getInstance(); // locale-specific
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, addDays);
		return cal.getTime();
	}	
}