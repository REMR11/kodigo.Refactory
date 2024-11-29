package refactorizado.Reservation;

// Clase base abstracta que implementa comportamientos comunes para todas las reservas
// Mejora: Reduce la duplicación de código y proporciona una implementación base
public abstract class BaseReservation implements Reservation {
    private String type;     // Tipo de reserva
    private double cost;     // Costo de la reserva

    // Constructor para establecer tipo y costo
    public BaseReservation(String type, double cost) {
        this.type = type;
        this.cost = cost;
    }

    // Implementación por defecto de métodos de la interfaz
    @Override
    public String getType() {
        return type;
    }

    @Override
    public double getCost() {
        return cost;
    }

    // Método abstracto que cada subclase debe implementar
    // Mejora: Permite personalización específica para cada tipo de reserva
    public abstract String specificConfirmation();

    // Implementación base de confirmación con detalles comunes
    @Override
    public String confirmReservation() {
        return specificConfirmation() +
                " - Tipo: " + getType() +
                " - Costo: $" + getCost();
    }
}