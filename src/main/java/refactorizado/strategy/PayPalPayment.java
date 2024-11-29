package refactorizado.strategy;

// Implementaciones concretas de estrategias de pago
public class PayPalPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        // Simulación simple de pago
        return amount > 0;
    }
}