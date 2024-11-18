package sinrefactoryzar;

public class ReservationFactory {
    public static Reservation createReservation(String type) {
        switch (type) {
            case "hotel":
                return new HotelReservation();
            case "vuelo":
                return new FlightReservation();
            case "coche":
                return new CarRentalReservation();
            default:
                throw new IllegalArgumentException("Unknown reservation type");
        }
    }

}
