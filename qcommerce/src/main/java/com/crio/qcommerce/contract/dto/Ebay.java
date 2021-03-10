package com.crio.qcommerce.contract.dto;

import com.opencsv.bean.CsvBindByName;

// txn_id,username,transaction_status,transaction_date,amount
public class Ebay {

    @CsvBindByName(column = "txn_id")
	private String transId;

    @CsvBindByName(column = "username")
    private String userId;

    @CsvBindByName(column = "transaction_status")
	private String status;

	@CsvBindByName(column = "transaction_date")
	private String date;
	
	@CsvBindByName(column = "amount")
	private String amount;

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
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
    
}