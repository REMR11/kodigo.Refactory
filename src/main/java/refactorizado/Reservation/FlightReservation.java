package refactorizado.Reservation;

// Similarmente para otras reservas...
public class FlightReservation extends BaseReservation {
    public FlightReservation(double cost) {
        super("VUELO", cost);
    }

    @Override
    public String specificConfirmation() {
        return "Reserva de vuelo confirmada";
    }
}