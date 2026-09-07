import java.util.Scanner;

/**
 * Mini Hospital Emergency Management System
 * CIT300 - Data Structures and Algorithms - Individual Mid Assignment
 *
 * Ties together four data structures:
 *  1. PatientBST     - stores patient records, keyed by Patient ID
 *  2. EmergencyQueue  - FIFO queue of patients waiting for treatment
 *  3. TreatmentStack  - LIFO stack of completed treatment records
 *  4. VisitHistory    - singly linked list of each patient's past visits
 */
public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static PatientBST patientBST = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentStack = new TreatmentStack();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> patientRecordsMenu();
                case 2 -> emergencyQueueMenu();
                case 3 -> treatmentHistoryMenu();
                case 4 -> visitHistoryMenu();
                case 0 -> {
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }

    // ============================================================
    // MAIN MENU
    // ============================================================
    private static void printMainMenu() {
        System.out.println("\n===== MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM =====");
        System.out.println("1. Patient Records (BST)");
        System.out.println("2. Emergency Patient Queue");
        System.out.println("3. Treatment History (Stack)");
        System.out.println("4. Patient Visit History (Linked List)");
        System.out.println("0. Exit");
    }

    // ============================================================
    // 1. PATIENT RECORDS - BST
    // ============================================================
    private static void patientRecordsMenu() {
        System.out.println("\n--- Patient Records (BST) ---");
        System.out.println("1. Insert Patient");
        System.out.println("2. Search Patient");
        System.out.println("3. Delete Patient");
        System.out.println("4. Display All Patients (In-order)");
        System.out.println("0. Back");

        int choice = readInt("Enter your choice: ");
        switch (choice) {
            case 1 -> {
                int id = readInt("Enter Patient ID: ");
                String name = readString("Enter Name: ");
                int age = readInt("Enter Age: ");
                String contact = readString("Enter Contact Number: ");
                String condition = readString("Enter Medical Condition: ");
                patientBST.insert(new Patient(id, name, age, contact, condition));
                System.out.println("Patient inserted successfully.");
            }
            case 2 -> {
                int id = readInt("Enter Patient ID to search: ");
                Patient found = patientBST.search(id);
                System.out.println(found != null ? "Found -> " + found : "Patient not found.");
            }
            case 3 -> {
                int id = readInt("Enter Patient ID to delete: ");
                patientBST.delete(id);
            }
            case 4 -> patientBST.displayInOrder();
            case 0 -> { /* return to main menu */ }
            default -> System.out.println("Invalid choice.");
        }
    }

    // ============================================================
    // 2. EMERGENCY PATIENT QUEUE
    // ============================================================
    private static void emergencyQueueMenu() {
        System.out.println("\n--- Emergency Patient Queue ---");
        System.out.println("1. Enqueue Patient (must already exist in BST)");
        System.out.println("2. Dequeue Patient (send to treatment)");
        System.out.println("3. Display Waiting Queue");
        System.out.println("0. Back");

        int choice = readInt("Enter your choice: ");
        switch (choice) {
            case 1 -> {
                int id = readInt("Enter Patient ID to add to queue: ");
                Patient p = patientBST.search(id);
                if (p == null) {
                    System.out.println("Patient not found in records. Please register the patient first (BST).");
                } else {
                    emergencyQueue.enqueue(p);
                }
            }
            case 2 -> emergencyQueue.dequeue();
            case 3 -> emergencyQueue.displayQueue();
            case 0 -> { /* return to main menu */ }
            default -> System.out.println("Invalid choice.");
        }
    }

    // ============================================================
    // 3. TREATMENT HISTORY - STACK
    // ============================================================
    private static void treatmentHistoryMenu() {
        System.out.println("\n--- Treatment History (Stack) ---");
        System.out.println("1. Push Completed Treatment");
        System.out.println("2. Pop Most Recent Treatment");
        System.out.println("3. Display Treatment History");
        System.out.println("0. Back");

        int choice = readInt("Enter your choice: ");
        switch (choice) {
            case 1 -> {
                int id = readInt("Enter Patient ID: ");
                Patient p = patientBST.search(id);
                if (p == null) {
                    System.out.println("Patient not found in records.");
                    return;
                }
                String summary = readString("Enter Treatment Summary: ");
                treatmentStack.push(new TreatmentRecord(id, p.getName(), summary));
            }
            case 2 -> treatmentStack.pop();
            case 3 -> treatmentStack.displayHistory();
            case 0 -> { /* return to main menu */ }
            default -> System.out.println("Invalid choice.");
        }
    }

    // ============================================================
    // 4. PATIENT VISIT HISTORY - SINGLY LINKED LIST
    // ============================================================
    private static void visitHistoryMenu() {
        int id = readInt("Enter Patient ID: ");
        Patient p = patientBST.search(id);
        if (p == null) {
            System.out.println("Patient not found in records.");
            return;
        }

        System.out.println("\n--- Visit History for " + p.getName() + " ---");
        System.out.println("1. Add Visit");
        System.out.println("2. Remove Visit");
        System.out.println("3. Search Visit");
        System.out.println("4. Display Visit History");
        System.out.println("0. Back");

        int choice = readInt("Enter your choice: ");
        VisitHistory history = p.getVisitHistory();

        switch (choice) {
            case 1 -> {
                int visitId = readInt("Enter Visit ID: ");
                String date = readString("Enter Visit Date (e.g. 2026-01-15): ");
                String doctor = readString("Enter Doctor Name: ");
                String diagnosis = readString("Enter Diagnosis: ");
                String treatment = readString("Enter Treatment: ");
                history.addVisit(new Visit(visitId, date, doctor, diagnosis, treatment));
                System.out.println("Visit added.");
            }
            case 2 -> {
                int visitId = readInt("Enter Visit ID to remove: ");
                history.removeVisit(visitId);
            }
            case 3 -> {
                int visitId = readInt("Enter Visit ID to search: ");
                Visit v = history.searchVisit(visitId);
                System.out.println(v != null ? "Found -> " + v : "Visit not found.");
            }
            case 4 -> history.displayHistory();
            case 0 -> { /* return to main menu */ }
            default -> System.out.println("Invalid choice.");
        }
    }

    // ============================================================
    // INPUT HELPERS
    // ============================================================
    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            System.out.print(prompt);
            sc.next();
        }
        int value = sc.nextInt();
        sc.nextLine(); // consume leftover newline
        return value;
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }
}
