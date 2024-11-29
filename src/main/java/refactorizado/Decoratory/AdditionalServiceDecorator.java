package refactorizado.Decoratory;

import refactorizado.Reservation.Reservation;

// Decorador para añadir servicios adicionales
// Mejora: Patrón Decorator para extender funcionalidades sin modificar clases base
public class AdditionalServiceDecorator implements Reservation {
    private final Reservation baseReservation;  // Reserva original
    private final String additionalService;     // Servicio adicional
    private final double additionalCost;        // Costo adicional

    // Constructor que envuelve una reserva existente
    public AdditionalServiceDecorator(Reservation reservation,
                                      String additionalService,
                                      double additionalCost) {
        this.baseReservation = reservation;
        this.additionalService = additionalService;
        this.additionalCost = additionalCost;
    }

    // Métodos delegados que mantienen el contrato original
    @Override
    public String getType() {
        return baseReservation.getType();
    }

    // Mejora: Añade servicios adicionales sin modificar la reserva original
    @Override
    public String confirmReservation() {
        return baseReservation.confirmReservation() +
                " | Servicio adicional: " + additionalService;
    }

    // Calcula el costo total incluyendo servicios adicionales
    @Override
    public double getCost() {
        return baseReservation.getCost() + additionalCost;
    }
}