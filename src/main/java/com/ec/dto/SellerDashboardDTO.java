package com.ec.dto;

public class SellerDashboardDTO {
	
	private Long totalProduct;
	
	private Long totalOrders;
	
	private Double totalRevenue;
	
	private Long totalCustomers;

	public Long getTotalProduct() {
		return totalProduct;
	}

	public void setTotalProduct(Long totalProduct) {
		this.totalProduct = totalProduct;
	}

	public Long getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(Long totalOrders) {
		this.totalOrders = totalOrders;
	}

	public Double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(Double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public Long getTotalCustomers() {
		return totalCustomers;
	}

	public void setTotalCustomers(Long totalCustomers) {
		this.totalCustomers = totalCustomers;
	}

}
