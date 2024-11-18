package sinrefactoryzar;

public class CarRentalReservation implements Reservation {
    @Override
    public String confirmReservation() {
        return "Flight reservation confirmed.";
    }
}
