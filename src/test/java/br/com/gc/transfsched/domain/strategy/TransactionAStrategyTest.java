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
import br.com.gc.transfsched.infrastructure.entity.Transfer;

@RunWith(SpringRunner.class)
public class TransactionAStrategyTest {

    @Test
    public void validTest() throws ParseException, InvalidDateException {

    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
    	Date dtScheduling = format.parse("01/09/2021");
    	Date dtTransfer = format.parse("01/09/2021");
        Long creditAccount = 2L;
        Long debitAccount = 1L;
        BigDecimal money = new BigDecimal(100);
        Transfer transfer= new Transfer(dtScheduling, dtTransfer, creditAccount,debitAccount,money, null); 

        TransactionAStrategy ts = new TransactionAStrategy();
        ts.calculateRate(transfer);
    	assertThat(ts.isValid(transfer)).isTrue();
    	MathContext mc = new MathContext(1);
    	assertThat(transfer.getRate().round(mc)).isEqualTo(new BigDecimal(6));
    }
    

    @Test
    public void testTransactionAStrategyInvalid() throws ParseException, InvalidDateException {
    	
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
    	Date dtScheduling = format.parse("01/09/2021");
    	Date dtTransfer = format.parse("02/09/2021");
        Long creditAccount = 2L;
        Long debitAccount = 1L;
        BigDecimal money = new BigDecimal(100);
        Transfer transfer= new Transfer(dtScheduling, dtTransfer, creditAccount,debitAccount,money, null); 
        
    	TransactionAStrategy ts = new TransactionAStrategy();
    	assertThat(ts.isValid(transfer)).isFalse();
    }
}