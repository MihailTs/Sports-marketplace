package bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.payment;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentDto(
        Long id,
        String paymentMethod,
        String status,
        LocalDateTime paymentDate,
        BigDecimal amount
) {
    public static PaymentDto fromEntity(Payment payment) {
        return new PaymentDto(
                payment.getId(),
                payment.getPaymentMethod(),
                payment.getStatus(),
                payment.getPaymentDate(),
                payment.getAmount()
        );
    }
}
