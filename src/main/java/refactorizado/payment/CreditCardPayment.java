package refactorizado.payment;

public class CreditCardPayment implements PaymentStrategy, refactorizado.strategy.PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        return amount > 0;
    }
}