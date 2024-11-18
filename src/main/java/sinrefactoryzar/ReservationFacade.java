package sinrefactoryzar;

public class ReservationFacade {
    private ReservationFactory factory;

    public ReservationFacade() {
        factory = new ReservationFactory();
    }
    public String makeReservation(String type) {
        Reservation reservation = factory.createReservation(type);
        return reservation.confirmReservation();
    }

}
