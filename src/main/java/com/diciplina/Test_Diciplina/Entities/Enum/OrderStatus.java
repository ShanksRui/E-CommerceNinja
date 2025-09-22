package com.diciplina.Test_Diciplina.Entities.Enum;

public enum OrderStatus {

	PENDING(1),
	PROCESSING(2),
	SHIPPED(3),
	DELIVERED(4);
	
	private int code;
	
	OrderStatus(int code){
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static  OrderStatus ValueOfStatusCode(int code) {
		for(OrderStatus s: OrderStatus.values()) {
			if(s.getCode() == code) {
				return s;
			}			
		}
		throw new IllegalArgumentException("invalid code");
	}	
}
