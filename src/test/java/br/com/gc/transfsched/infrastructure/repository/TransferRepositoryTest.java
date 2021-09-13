package br.com.gc.transfsched.infrastructure.repository;



import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gc.transfsched.infrastructure.entity.Transfer;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TransferRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private TransferRepository repository;
       
  
    @Test
    public void test() throws Exception {
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); 
    	Date date = format.parse("08/09/2021");
        Long creditAccount = 2L;
        Long debitAccount = 1L;
        BigDecimal money = new BigDecimal(10);
        BigDecimal rate = new BigDecimal(1);

        Transfer transfer= new Transfer(date, date, creditAccount,debitAccount,money, rate); 
        transfer = this.entityManager.persist(transfer);
        transfer = this.repository.findById(transfer.getId()).orElse(new Transfer());
    	assertThat(transfer.getDtScheduling()).isEqualTo(date);
    	assertThat(transfer.getDtTransfer()).isEqualTo(date);
    	assertThat(transfer.getCreditAccount()).isEqualTo(creditAccount);
    	assertThat(transfer.getDebitAccount()).isEqualTo(debitAccount);
    	assertThat(transfer.getMoney()).isEqualTo(money);
    	assertThat(transfer.getRate()).isEqualTo(rate);
    }
}