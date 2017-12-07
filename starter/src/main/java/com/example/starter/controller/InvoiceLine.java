package com.example.starter.controller;

public class InvoiceLine {
	String invoiceNumber;
	long lineamount;
	String customer;
	String product;
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public long getLineamount() {
		return lineamount;
	}
	public void setLineamount(long lineamount) {
		this.lineamount = lineamount;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "InvoiceLine [invoiceNumber=" + invoiceNumber + ", lineamount=" + lineamount + ", customer=" + customer
				+ "]";
	}
}
