package sinrefactoryzar;

public class ReservationDecorator implements Reservation{
    protected Reservation reservation;
    public ReservationDecorator(Reservation reservation) {
        this.reservation = reservation;
    }
    @Override
    public String confirmReservation() {
        return reservation.confirmReservation();  // Llama al método de la reserva base
    }
}
