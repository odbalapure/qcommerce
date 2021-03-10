
package com.crio.qcommerce.contract.dto;

import com.opencsv.bean.CsvBindByName;

// transaction_id,ext_txn_id,user_id,status,date,amount
public class Amazon {
    
    @CsvBindByName(column = "transaction_id")
	private String transId;
	
	@CsvBindByName(column = "ext_txn_id")
	private String extTransId;
	
	@CsvBindByName(column = "user_id")
	private String userId;
    
    @CsvBindByName(column = "status")
	private String status;

	@CsvBindByName(column = "date")
	private String date;
	
	@CsvBindByName(column = "amount")
    private String amount;

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getExtTransId() {
        return extTransId;
    }

    public void setExtTransId(String extTransId) {
        this.extTransId = extTransId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Amazon [amount=" + amount + ", date=" + date + ", extTransId=" + extTransId + ", status=" + status
                + ", transId=" + transId + ", userId=" + userId + "]";
    }

}