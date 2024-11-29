package refactorizado.factory;

import refactorizado.Reservation.CarRentalReservation;
import refactorizado.Reservation.FlightReservation;
import refactorizado.Reservation.HotelReservation;
import refactorizado.Reservation.Reservation;

// Fábrica de reservas mejorada
// Mejora: Implementa patrón Factory con manejo de errores y flexibilidad
public class ReservationFactory {
    // Método estático para crear reservas según el tipo
    public static Reservation createReservation(String type, double cost) {
        // Uso de switch expressions (Java 14+) para mayor legibilidad
        // Mejora: Manejo explícito de tipos de reserva desconocidos
        return switch (type.toUpperCase()) {
            case "HOTEL" -> new HotelReservation(cost);
            case "VUELO" -> new FlightReservation(cost);
            case "COCHE" -> new CarRentalReservation(cost);
            default -> throw new IllegalArgumentException("Tipo de reserva no soportado: " + type);
        };
    }
}