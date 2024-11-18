package sinrefactoryzar;

public class HotelReservation implements Reservation {

    @Override
    public String confirmReservation() {
        return "Hotel reservation confirmed.";
    }
}
