import java.util.Stack;

/**
 * Treatment History Stack.
 * Stores completed treatment records using LIFO (Last-In, First-Out)
 * ordering: the most recently completed treatment is the first one
 * you see when popping / viewing the top.
 *
 * Implemented using Java's built-in Stack class, restricted to
 * push/pop/peek/display style operations.
 */
public class TreatmentStack {

    private Stack<TreatmentRecord> stack;

    public TreatmentStack() {
        stack = new Stack<>();
    }

    // Add a completed treatment record to the top of the stack.
    public void push(TreatmentRecord record) {
        stack.push(record);
        System.out.println("Treatment record added for Patient " + record.getPatientId() + ".");
    }

    // Remove and return the most recently completed treatment record.
    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("Treatment history is empty. Nothing to pop.");
            return null;
        }
        TreatmentRecord popped = stack.pop();
        System.out.println("Removed most recent treatment record: " + popped);
        return popped;
    }

    // View the most recent record without removing it.
    public TreatmentRecord peek() {
        if (isEmpty()) {
            System.out.println("Treatment history is empty.");
            return null;
        }
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    // Display all treatment records, most recent first.
    public void displayHistory() {
        if (isEmpty()) {
            System.out.println("No treatment records found.");
            return;
        }
        System.out.println("---- Treatment History (most recent first) ----");
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.println(stack.get(i));
        }
    }
}
