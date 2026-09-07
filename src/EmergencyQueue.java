import java.util.LinkedList;

/**
 * Emergency Patient Queue.
 * Manages patients waiting for emergency treatment using FIFO
 * (First-In, First-Out) ordering: the first patient enqueued
 * is the first one dequeued for treatment.
 *
 * Implemented internally using a LinkedList as the underlying
 * storage, but only queue-style operations (enqueue/dequeue) are exposed.
 */
public class EmergencyQueue {

    private LinkedList<Patient> queue;

    public EmergencyQueue() {
        queue = new LinkedList<>();
    }

    // Add a patient to the back of the waiting queue.
    public void enqueue(Patient patient) {
        queue.addLast(patient);
        System.out.println("Patient " + patient.getPatientId() + " (" + patient.getName() + ") added to emergency queue.");
    }

    // Remove and return the patient at the front of the queue (next for treatment).
    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty. No patient to treat.");
            return null;
        }
        Patient next = queue.removeFirst();
        System.out.println("Patient " + next.getPatientId() + " (" + next.getName() + ") is now being treated.");
        return next;
    }

    // View the next patient without removing them.
    public Patient peek() {
        if (isEmpty()) {
            System.out.println("Emergency queue is empty.");
            return null;
        }
        return queue.getFirst();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No patients currently waiting.");
            return;
        }
        System.out.println("---- Patients Waiting (front -> back) ----");
        int position = 1;
        for (Patient p : queue) {
            System.out.println(position + ". " + p);
            position++;
        }
    }
}
