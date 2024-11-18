package sinrefactoryzar;

public class ReservationAdapterImpl implements ReservationAdapter{
    private Reservation reservation;

    public ReservationAdapterImpl(Reservation reservation) {
        this.reservation = reservation;
    }


    @Override
    public String confirm() {
        return reservation.confirmReservation();
    }
}
