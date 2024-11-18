package sinrefactoryzar;

public class InsuranceDecorator extends ReservationDecorator {
    public InsuranceDecorator(Reservation reservation) {
        super(reservation);
    }

    @Override
    public String confirmReservation() {
        return super.confirmReservation() + " with insurance.";  // Agrega el seguro a la confirmación
    }
}
