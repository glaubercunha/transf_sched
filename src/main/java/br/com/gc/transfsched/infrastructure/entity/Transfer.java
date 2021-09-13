package br.com.gc.transfsched.infrastructure.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transfer {

    @Id
   	@GeneratedValue(strategy=GenerationType.SEQUENCE)   
    private Long id;

    private Date dtScheduling;

    private Date dtTransfer;

    private Long creditAccount;
    
    private Long debitAccount;
    
    private BigDecimal money;

    private BigDecimal rate;

    public Transfer(){
    }
    
	public Transfer(Long id, Date dtScheduling, Date dtTransfer, Long creditAccount, Long debitAccount,
			BigDecimal money, BigDecimal rate) {
		
		this(dtScheduling, dtTransfer, creditAccount, debitAccount, money, rate);
		this.id = id;
	}
    
	public Transfer(Date dtScheduling, Date dtTransfer, Long creditAccount, Long debitAccount,
			BigDecimal money, BigDecimal rate) {
		
		this(dtTransfer, creditAccount, debitAccount, money);

		this.dtScheduling = dtScheduling;
		this.rate = rate;
	}	
    
	public Transfer(Date dtTransfer, Long creditAccount, Long debitAccount,
			BigDecimal money) {
		this.dtTransfer = dtTransfer;
		this.creditAccount = creditAccount;
		this.debitAccount = debitAccount;
		this.money = money;
	}	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDtScheduling() {
		return dtScheduling;
	}

	public void setDtScheduling(Date dtScheduling) {
		this.dtScheduling = dtScheduling;
	}

	public Date getDtTransfer() {
		return dtTransfer;
	}

	public void setDtTransfer(Date dtTranferencia) {
		this.dtTransfer = dtTranferencia;
	}

	public Long getCreditAccount() {
		return creditAccount;
	}

	public void setCreditAccount(Long creditAccount) {
		this.creditAccount = creditAccount;
	}

	public Long getDebitAccount() {
		return debitAccount;
	}

	public void setDebitAccount(Long debitAccount) {
		this.debitAccount = debitAccount;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transfer other = (Transfer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transfer [id=" + id + ", dtScheduling=" + dtScheduling + ", dtTransfer=" + dtTransfer
				+ ", creditAccount=" + creditAccount + ", debitAccount=" + debitAccount + ", money=" + money + ", rate="
				+ rate + "]";
	}

    
    
}