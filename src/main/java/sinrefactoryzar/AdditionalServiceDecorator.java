package sinrefactoryzar;

public class AdditionalServiceDecorator extends ReservationDecorator{
    public AdditionalServiceDecorator(Reservation reservation) {
        super(reservation);
    }
    @Override
    public String confirmReservation() {
        return super.confirmReservation() + " with additional services.";  // Agrega los servicios adicionales
    }
}
