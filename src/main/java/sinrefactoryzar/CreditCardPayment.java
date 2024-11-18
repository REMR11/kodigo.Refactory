package sinrefactoryzar;

public class CreditCardPayment implements PaymentStrategy {
    @Override
    public String processPayment(double amount) {
        return "Pago " + amount + " Con Tarjeta.";
    }
}
