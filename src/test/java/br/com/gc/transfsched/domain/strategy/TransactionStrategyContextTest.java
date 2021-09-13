package br.com.gc.transfsched.domain.strategy;



import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gc.transfsched.domain.exception.InvalidDateException;
import br.com.gc.transfsched.domain.exception.TransactionNotFoundException;
import br.com.gc.transfsched.domain.strategy.TransactionStrategyContext;
import br.com.gc.transfsched.infrastructure.entity.Transfer;

@RunWith(SpringRunner.class)
public class TransactionStrategyContextTest {

    @Test
    public void testA() throws ParseException, TransactionNotFoundException, InvalidDateException {

    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
    	Date dtScheduling = format.parse("01/09/2021");
    	Date dtTransfer = format.parse("01/09/2021");
        Long creditAccount = 2L;
        Long debitAccount = 1L;
        BigDecimal money = new BigDecimal(100);
        Transfer transfer= new Transfer(dtScheduling, dtTransfer, creditAccount,debitAccount,money, null); 

        TransactionStrategyContext tsc = new TransactionStrategyContext();
        tsc.calculateRate(transfer);
    	MathContext mc = new MathContext(1);
    	assertThat(transfer.getRate().round(mc)).isEqualTo(new BigDecimal(6).round(mc));
    }

    @Test
    public void testB() throws ParseException, TransactionNotFoundException, InvalidDateException {

    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
    	Date dtScheduling = format.parse("01/09/2021");
    	Date dtTransfer = format.parse("11/09/2021");
        Long creditAccount = 2L;
        Long debitAccount = 1L;
        BigDecimal money = new BigDecimal(100);
        Transfer transfer= new Transfer(dtScheduling, dtTransfer, creditAccount,debitAccount,money, null); 

        TransactionStrategyContext tsc = new TransactionStrategyContext();
        tsc.calculateRate(transfer);
    	MathContext mc = new MathContext(3);
    	assertThat(transfer.getRate().round(mc)).isEqualTo(new BigDecimal(120).round(mc));
    }
    

    @Test
    public void testC20() throws ParseException, TransactionNotFoundException, InvalidDateException {

    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
    	Date dtScheduling = format.parse("01/09/2021");
    	Date dtTransfer = format.parse("21/09/2021");
        Long creditAccount = 2L;
        Long debitAccount = 1L;
        BigDecimal money = new BigDecimal(100);
        Transfer transfer= new Transfer(dtScheduling, dtTransfer, creditAccount,debitAccount,money, null); 

        TransactionStrategyContext tsc = new TransactionStrategyContext();
        tsc.calculateRate(transfer);
    	MathContext mc = new MathContext(1);
    	assertThat(transfer.getRate().round(mc)).isEqualTo(new BigDecimal(8).round(mc));
    }  

    @Test
    public void testC30() throws ParseException, TransactionNotFoundException, InvalidDateException {

    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
    	Date dtScheduling = format.parse("01/09/2021");
    	Date dtTransfer = format.parse("01/10/2021");
        Long creditAccount = 2L;
        Long debitAccount = 1L;
        BigDecimal money = new BigDecimal(100);
        Transfer transfer= new Transfer(dtScheduling, dtTransfer, creditAccount,debitAccount,money, null); 

        TransactionStrategyContext tsc = new TransactionStrategyContext();
        tsc.calculateRate(transfer);
    	MathContext mc = new MathContext(1);
    	assertThat(transfer.getRate().round(mc)).isEqualTo(new BigDecimal(6).round(mc));
    }  

    @Test
    public void testC40() throws ParseException, TransactionNotFoundException, InvalidDateException {

    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
    	Date dtScheduling = format.parse("01/09/2021");
    	Date dtTransfer = format.parse("11/10/2021");
        Long creditAccount = 2L;
        Long debitAccount = 1L;
        BigDecimal money = new BigDecimal(100);
        Transfer transfer= new Transfer(dtScheduling, dtTransfer, creditAccount,debitAccount,money, null); 

        TransactionStrategyContext tsc = new TransactionStrategyContext();
        tsc.calculateRate(transfer);
    	MathContext mc = new MathContext(1);
    	assertThat(transfer.getRate().round(mc)).isEqualTo(new BigDecimal(4).round(mc));
    }    

    @Test
    public void testC40More() throws ParseException, TransactionNotFoundException, InvalidDateException {

    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
    	Date dtScheduling = format.parse("01/09/2021");
    	Date dtTransfer = format.parse("12/10/2021");
        Long creditAccount = 2L;
        Long debitAccount = 1L;
        BigDecimal money = new BigDecimal(101000);
        Transfer transfer= new Transfer(dtScheduling, dtTransfer, creditAccount,debitAccount,money, null); 

        TransactionStrategyContext tsc = new TransactionStrategyContext();
        tsc.calculateRate(transfer);
        MathContext mc = new MathContext(4);
    	assertThat(transfer.getRate().round(mc)).isEqualTo(new BigDecimal(2020).round(mc));
    }   

    @Test(expected = TransactionNotFoundException.class)
    public void testC40Invalid() throws ParseException, TransactionNotFoundException, InvalidDateException {
        
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
    	Date dtScheduling = format.parse("01/09/2021");
    	Date dtTransfer = format.parse("12/10/2021");
        Long creditAccount = 2L;
        Long debitAccount = 1L;
        BigDecimal money = new BigDecimal(100000);
        Transfer transfer= new Transfer(dtScheduling, dtTransfer, creditAccount,debitAccount,money, null); 

        TransactionStrategyContext tsc = new TransactionStrategyContext();
        tsc.calculateRate(transfer);
    }       
}