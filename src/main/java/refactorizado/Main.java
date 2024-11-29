package refactorizado;

import refactorizado.Decoratory.AdditionalServiceDecorator;
import refactorizado.Reservation.Reservation;
import refactorizado.core.ReservationSystem;
import refactorizado.factory.ReservationFactory;
import refactorizado.payment.CreditCardPayment;
import refactorizado.strategy.PayPalPayment;
import refactorizado.strategy.PaymentStrategy;

import java.util.List;
import java.util.Scanner;

/**
 * Clase principal que gestiona el sistema de reservas.
 */
public class Main {
    /**
     * Método principal que inicia la aplicación de reservas.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        // Instancia única del sistema de reservas (Singleton)
        ReservationSystem reservationSystem = ReservationSystem.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMainMenu(); // Mostrar el menú principal

            int option = getUserInputAsInt(scanner, "Seleccione una opción: ");

            switch (option) {
                case 1 -> createReservation(scanner, reservationSystem);
                case 2 -> addAdditionalService(scanner, reservationSystem);
                case 3 -> listAllReservations(reservationSystem);
                case 4 -> processPayment(scanner, reservationSystem);
                case 5 -> {
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    return; // Terminar el programa
                }
                default -> System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    /**
     * Muestra el menú principal de opciones del sistema de reservas.
     */
    private static void displayMainMenu() {
        System.out.println("\n===== Sistema de Reservas =====");
        System.out.println("1. Crear una nueva reserva");
        System.out.println("2. Añadir un servicio adicional a una reserva existente");
        System.out.println("3. Listar todas las reservas");
        System.out.println("4. Procesar pago de una reserva");
        System.out.println("5. Salir");
    }

    /**
     * Solicita una entrada numérica del usuario.
     *
     * @param scanner Scanner para leer la entrada del usuario.
     * @param prompt  Mensaje que se mostrará al usuario.
     * @return El número ingresado por el usuario.
     */
    private static int getUserInputAsInt(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor ingrese un número.");
            scanner.next(); // Limpiar la entrada inválida
        }
        return scanner.nextInt();
    }

    /**
     * Crea una nueva reserva en el sistema.
     *
     * @param scanner          Scanner para leer la entrada del usuario.
     * @param reservationSystem Sistema de reservas donde se añadirá la nueva reserva.
     */
    private static void createReservation(Scanner scanner, ReservationSystem reservationSystem) {
        System.out.print("Ingrese el tipo de reserva (HOTEL, VUELO, COCHE): ");
        String type = scanner.next().toUpperCase();
        double cost = getUserInputAsDouble(scanner, "Ingrese el costo de la reserva: ");

        try {
            Reservation newReservation = ReservationFactory.createReservation(type, cost);
            reservationSystem.addReservation(newReservation);
            System.out.println("Reserva creada exitosamente: " + newReservation.confirmReservation());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Añade un servicio adicional a una reserva existente.
     *
     * @param scanner          Scanner para leer la entrada del usuario.
     * @param reservationSystem Sistema de reservas donde se encuentra la reserva.
     */
    private static void addAdditionalService(Scanner scanner, ReservationSystem reservationSystem) {
        List<Reservation> reservations = reservationSystem.getReservations();

        if (reservations.isEmpty()) {
            System.out.println("No hay reservas disponibles para añadir servicios.");
            return;
        }

        displayReservations(reservations);
        int reservationIndex = getUserInputAsInt(scanner, "Seleccione el número de la reserva: ") - 1;

        if (isValidIndex(reservationIndex, reservations.size())) {
            System.out.print("Ingrese el nombre del servicio adicional: ");
            String serviceName = scanner.next();
            double serviceCost = getUserInputAsDouble(scanner, "Ingrese el costo del servicio adicional: ");

            Reservation decoratedReservation = new AdditionalServiceDecorator(
                    reservations.get(reservationIndex), serviceName, serviceCost);
            reservations.set(reservationIndex, decoratedReservation); // Actualizar la lista
            System.out.println("Servicio adicional añadido: " + decoratedReservation.confirmReservation());
        } else {
            System.out.println("Número de reserva inválido.");
        }
    }

    /**
     * Lista todas las reservas en el sistema.
     *
     * @param reservationSystem Sistema de reservas del cual se listarán las reservas.
     */
    private static void listAllReservations(ReservationSystem reservationSystem) {
        List<Reservation> reservations = reservationSystem.getReservations();

        if (reservations.isEmpty()) {
            System.out.println("No hay reservas en el sistema.");
        } else {
            System.out.println("=== Reservas actuales ===");
            reservations.forEach(reservation -> System.out.println(reservation.confirmReservation()));
        }
    }

    /**
     * Procesa el pago de una reserva seleccionada.
     *
     * @param scanner          Scanner para leer la entrada del usuario.
     * @param reservationSystem Sistema de reservas donde se encuentra la reserva.
     */
    private static void processPayment(Scanner scanner, ReservationSystem reservationSystem) {
        List<Reservation> reservations = reservationSystem.getReservations();

        if (reservations.isEmpty()) {
            System.out.println("No hay reservas disponibles para procesar pagos.");
            return;
        }

        displayReservations(reservations);
        int reservationIndex = getUserInputAsInt(scanner, "Seleccione el número de la reserva: ") - 1;

        if (isValidIndex(reservationIndex, reservations.size())) {
            Reservation selectedReservation = reservations.get(reservationIndex);

            System.out.print("Seleccione método de pago (1. PayPal, 2. Tarjeta de Crédito): ");
            int paymentOption = getUserInputAsInt(scanner, "");

            PaymentStrategy paymentStrategy = switch (paymentOption) {
                case 1 -> new PayPalPayment();
                case 2 -> new CreditCardPayment();
                default -> null;
            };

            if (paymentStrategy != null) {
                boolean success = paymentStrategy.processPayment(selectedReservation.getCost());
                System.out.println(success ? "Pago procesado exitosamente." : "El pago falló.");
            } else {
                System.out.println("Opción de método de pago inválida.");
            }
        } else {
            System.out.println("Número de reserva inválido.");
        }
    }

    /**
     * Muestra la lista numerada de reservas.
     *
     * @param reservations Lista de reservas a mostrar.
     */
    private static void displayReservations(List<Reservation> reservations) {
        System.out.println("=== Lista de reservas ===");
        for (int i = 0; i < reservations.size(); i++) {
            System.out.println((i + 1) + ". " + reservations.get(i).confirmReservation());
        }
    }

    /**
     * Valida que un índice esté dentro de los límites de una lista.
     *
     * @param index Índice a validar.
     * @param size  Tamaño de la lista.
     * @return true si el índice es válido, false en caso contrario.
     */
    private static boolean isValidIndex(int index, int size) {
        return index >= 0 && index < size;
    }

    /**
     * Solicita una entrada numérica en formato decimal del usuario.
     *
     * @param scanner Scanner para leer la entrada del usuario.
     * @param prompt  Mensaje que se mostrará al usuario.
     * @return El número decimal ingresado por el usuario.
     */
    private static double getUserInputAsDouble(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Entrada inválida. Por favor ingrese un número.");
            scanner.next(); // Limpiar la entrada inválida
        }
        return scanner.nextDouble();
    }
}
