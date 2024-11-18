package sinrefactoryzar;

import java.util.ArrayList;
import java.util.List;

public class ReservationSystem {
    private static ReservationSystem instance;
    private List<Reservation> reservas = new ArrayList<>();

    private ReservationSystem() {
    }

    public static ReservationSystem getInstance() {
        if (instance == null) {
            instance = new ReservationSystem();
        }
        return instance;
    }

    public void addReservation(Reservation reservation) {
        reservas.add(reservation);
    }

    public List<Reservation> getReservations() {
        return reservas;
    }

}
