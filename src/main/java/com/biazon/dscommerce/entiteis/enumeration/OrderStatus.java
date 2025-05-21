package com.biazon.dscommerce.entiteis.enumeration;

import lombok.Getter;

public enum OrderStatus {
	
	WAITING_PAYMENT("Aguardando Pagamento"),
	PAID("Pago"),
	SHIPPED("Enviado"),
	DELIVERED("Entregue"),
	CANCELED("Cancelado");

	@Getter
	private String label;
	
	OrderStatus(String label) {
		this.label = label;
	}
}
