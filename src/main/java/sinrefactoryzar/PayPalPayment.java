package sinrefactoryzar;

public class PayPalPayment implements PaymentStrategy{
    @Override
    public String processPayment(double amount) {
        return "Pago " + amount + " Con PayPal.";
    }
}
