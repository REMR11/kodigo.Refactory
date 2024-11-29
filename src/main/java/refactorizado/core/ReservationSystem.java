package refactorizado.core;

import refactorizado.Reservation.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

// Sistema de reservas mejorado
// Mejora: Implementación thread-safe del patrón Singleton
public class ReservationSystem {
    // Uso de volatile para garantizar visibilidad entre hilos
    private static volatile ReservationSystem instance;

    // CopyOnWriteArrayList para operaciones thread-safe
    private final List<Reservation> reservations;

    // Logger para seguimiento de operaciones
    private static final Logger LOGGER = Logger.getLogger(ReservationSystem.class.getName());

    // Constructor privado para prevenir instanciación directa
    private ReservationSystem() {
        this.reservations = new CopyOnWriteArrayList<>();
    }

    // Método getInstance thread-safe con doble comprobación
    // Mejora: Evita problemas de concurrencia en la creación de singleton
    public static ReservationSystem getInstance() {
        // Primera comprobación sin bloqueo
        if (instance == null) {
            // Bloqueo sincronizado solo si es necesario
            synchronized (ReservationSystem.class) {
                // Segunda comprobación dentro del bloque sincronizado
                if (instance == null) {
                    instance = new ReservationSystem();
                }
            }
        }
        return instance;
    }

    // Método para añadir reservas con validación
    public void addReservation(Reservation reservation) {
        // Mejora: Validación de nulidad y logging
        if (reservation != null) {
            reservations.add(reservation);
            LOGGER.info("Reserva añadida: " + reservation.getType());
        }
    }

    // Método para obtener reservas
    // Mejora: Devuelve una copia para preservar encapsulamiento
    public List<Reservation> getReservations() {
        return new ArrayList<>(reservations);
    }
}

