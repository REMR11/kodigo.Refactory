package refactorizado.strategy;

// Interfaz de estrategia de pago
// Mejora: Patrón Strategy para desacoplar los algoritmos de pago
public interface PaymentStrategy {
    // Método genérico para procesar pagos
    boolean processPayment(double amount);
}