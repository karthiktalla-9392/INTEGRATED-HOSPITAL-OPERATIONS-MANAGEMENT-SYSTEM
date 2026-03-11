import java.util.*;

public class HospitalManagementSystem {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<User> users = new ArrayList<>();
    static ArrayList<Medicine> medicines = new ArrayList<>();
    static ArrayList<Appointment> appointments = new ArrayList<>();
    static ArrayList<Medicine> cart = new ArrayList<>();

    public static void main(String[] args) {

        seedMedicines();

        while (true) {

            System.out.println("\n===== INTEGRATED HOSPITAL OPERATIONS MANAGEMENT SYSTEM =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    register();
                    break;

                case 2:
                    login();
                    break;

                case 3:
                    System.out.println("System Closed.");
                    System.exit(0);
            }
        }
    }

    // REGISTER
    static void register() {

        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        users.add(new User(name, email, pass));

        System.out.println("Registration Successful!");
    }

    // LOGIN
    static void login() {

        sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        for (User u : users) {

            if (u.email.equals(email) && u.password.equals(pass)) {

                System.out.println("\nLogin Successful. Welcome " + u.name);
                dashboard();
                return;
            }
        }

        System.out.println("Invalid Credentials.");
    }

    // DASHBOARD
    static void dashboard() {

        while (true) {

            System.out.println("\n===== DASHBOARD =====");

            System.out.println("1. Pharmacy");
            System.out.println("2. Book Appointment");
            System.out.println("3. View Appointments");
            System.out.println("4. AI Symptom Checker");
            System.out.println("5. Lab Reports");
            System.out.println("6. Billing");
            System.out.println("7. Logout");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    pharmacy();
                    break;

                case 2:
                    bookAppointment();
                    break;

                case 3:
                    viewAppointments();
                    break;

                case 4:
                    aiChecker();
                    break;

                case 5:
                    labReports();
                    break;

                case 6:
                    billing();
                    break;

                case 7:
                    return;
            }
        }
    }

    // PHARMACY
    static void pharmacy() {

        while (true) {

            System.out.println("\n----- PHARMACY -----");

            for (int i = 0; i < medicines.size(); i++) {

                Medicine m = medicines.get(i);

                System.out.println((i + 1) + ". " + m.name + " - ₹" + m.price);
            }

            System.out.println("0. Back");

            System.out.print("Select Medicine: ");
            int n = sc.nextInt();

            if (n == 0)
                return;

            cart.add(medicines.get(n - 1));

            System.out.println("Added to Cart.");
        }
    }

    // BOOK APPOINTMENT WITH 10 DOCTORS
    static void bookAppointment() {

        sc.nextLine();

        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine();

        System.out.println("\nSelect Doctor:");
        System.out.println("1. Dr Rajesh Sharma (Cardiology)");
        System.out.println("2. Dr Priya Patel (Neurology)");
        System.out.println("3. Dr Amit Singh (Orthopedic)");
        System.out.println("4. Dr Sarah Miller (Psychiatry)");
        System.out.println("5. Dr Samuel Lee (Dermatology)");
        System.out.println("6. Dr Linda Wu (Gastroenterology)");
        System.out.println("7. Dr David Kim (Pulmonology)");
        System.out.println("8. Dr Maria Garcia (Pediatrics)");
        System.out.println("9. Dr Ahmed Khan (General Medicine)");
        System.out.println("10. Dr Elena Vance (Endocrinology)");

        int d = sc.nextInt();

        String doctor = "";

        switch (d) {

            case 1:
                doctor = "Dr Rajesh Sharma (Cardiology)";
                break;

            case 2:
                doctor = "Dr Priya Patel (Neurology)";
                break;

            case 3:
                doctor = "Dr Amit Singh (Orthopedic)";
                break;

            case 4:
                doctor = "Dr Sarah Miller (Psychiatry)";
                break;

            case 5:
                doctor = "Dr Samuel Lee (Dermatology)";
                break;

            case 6:
                doctor = "Dr Linda Wu (Gastroenterology)";
                break;

            case 7:
                doctor = "Dr David Kim (Pulmonology)";
                break;

            case 8:
                doctor = "Dr Maria Garcia (Pediatrics)";
                break;

            case 9:
                doctor = "Dr Ahmed Khan (General Medicine)";
                break;

            case 10:
                doctor = "Dr Elena Vance (Endocrinology)";
                break;

            default:
                System.out.println("Invalid Doctor Selection.");
                return;
        }

        appointments.add(new Appointment(name, doctor));

        System.out.println("Appointment Booked Successfully with " + doctor);
    }

    // VIEW APPOINTMENTS
    static void viewAppointments() {

        System.out.println("\n--- APPOINTMENTS ---");

        if (appointments.size() == 0) {

            System.out.println("No Appointments.");
            return;
        }

        for (Appointment a : appointments) {

            System.out.println(a.patient + " -> " + a.doctor);
        }
    }

    // AI SYMPTOM CHECKER
    static void aiChecker() {

        sc.nextLine();

        System.out.print("Enter Symptoms: ");

        String s = sc.nextLine().toLowerCase();

        if (s.contains("chest")) {

            System.out.println("AI Suggestion: Possible Heart Issue. Visit Cardiologist.");

        } else if (s.contains("head")) {

            System.out.println("AI Suggestion: Possible Migraine.");

        } else if (s.contains("fever")) {

            System.out.println("AI Suggestion: Possible Viral Infection.");

        } else {

            System.out.println("AI Suggestion: Please consult a doctor.");
        }
    }

    // LAB REPORTS
    static void labReports() {

        System.out.println("\n--- LAB REPORTS ---");

        System.out.println("1. Blood Test");
        System.out.println("2. MRI Scan");
        System.out.println("3. Lipid Profile");

        int r = sc.nextInt();

        if (r == 1)
            System.out.println("Blood Test Result: Normal");

        else if (r == 2)
            System.out.println("MRI Scan: No abnormality detected");

        else if (r == 3)
            System.out.println("Lipid Profile: Cholesterol slightly high");
    }

    // BILLING
    static void billing() {

        int total = 0;

        System.out.println("\n--- BILLING ---");

        if (cart.size() == 0) {

            System.out.println("Cart Empty.");
            return;
        }

        for (Medicine m : cart) {

            System.out.println(m.name + " - ₹" + m.price);
            total += m.price;
        }

        System.out.println("Total = ₹" + total);

        System.out.println("\nSelect Payment Method");
        System.out.println("1. UPI");
        System.out.println("2. Cash");

        int p = sc.nextInt();

        if (p == 1)
            System.out.println("UPI Payment Successful.");

        else
            System.out.println("Cash Payment Selected.");

        cart.clear();
    }

    // SAMPLE MEDICINES
    static void seedMedicines() {

        medicines.add(new Medicine("Paracetamol", 40));
        medicines.add(new Medicine("Amoxicillin", 120));
        medicines.add(new Medicine("Metformin", 95));
        medicines.add(new Medicine("Ibuprofen", 55));
        medicines.add(new Medicine("Cetirizine", 35));
        medicines.add(new Medicine("Atorvastatin", 210));
        medicines.add(new Medicine("Azithromycin", 180));
    }
}

// USER CLASS
class User {

    String name;
    String email;
    String password;

    User(String n, String e, String p) {

        name = n;
        email = e;
        password = p;
    }
}

// MEDICINE CLASS
class Medicine {

    String name;
    int price;

    Medicine(String n, int p) {

        name = n;
        price = p;
    }
}

// APPOINTMENT CLASS
class Appointment {

    String patient;
    String doctor;

    Appointment(String p, String d) {

        patient = p;
        doctor = d;
    }
}