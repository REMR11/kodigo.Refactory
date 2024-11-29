package refactorizado.Reservation;

public class CarRentalReservation extends BaseReservation {
    public CarRentalReservation(double cost) {
        super("ALQUILER_COCHE", cost);
    }

    @Override
    public String specificConfirmation() {
        return "Reserva de alquiler de coche confirmada";
    }
}
