package refactorizado.Reservation;

// Interfaz que define el contrato básico para todas las reservas
// Mejora: Permite una abstracción clara y flexible para diferentes tipos de reservas
public interface Reservation {
    String getType();        // Obtener el tipo de reserva
    String confirmReservation(); // Método para confirmar la reserva
    double getCost();        // Obtener el costo de la reserva
}