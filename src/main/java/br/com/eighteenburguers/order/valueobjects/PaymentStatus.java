package br.com.eighteenburguers.order.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatus {

	PENDING("Pendente (o pagamento foi iniciado, mas ainda não foi concluído)."),
	PROCESSING("Processando (o pagamento está sendo processado)."),
	COMPLETED("Concluído (o pagamento foi processado com sucesso)."),
	FAILED("Falhou (o pagamento não pôde ser concluído por algum motivo)."),
	CANCELLED("Cancelado (o pagamento foi cancelado antes de ser concluído)."),
	REFUNDED("Reembolsado (o pagamento foi devolvido ao pagador)."),
	AUTHORIZED("Autorizado (o pagamento foi autorizado, mas ainda não foi capturado)."),
	CAPTURED("Capturado (o pagamento autorizado foi capturado e concluído)."),
	VOIDED("Anulado (o pagamento autorizado foi anulado antes de ser capturado).");
	
	private String message;
}
