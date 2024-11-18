package sinrefactoryzar;

public class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public PaymentContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    public String executePayment(double amount) {
        return paymentStrategy.processPayment(amount);
    }

}
