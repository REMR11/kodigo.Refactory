package refactorizado.Reservation;


// Implementaciones concretas de reservas
// Mejora: Cada clase tiene una implementación específica y clara
public class HotelReservation extends BaseReservation {
    public HotelReservation(double cost) {
        // Llama al constructor padre con tipo fijo y costo variable
        super("HOTEL", cost);
    }

    @Override
    public String specificConfirmation() {
        // Mensaje específico para reservas de hotel
        return "Reserva de hotel confirmada";
    }
}
