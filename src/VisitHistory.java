/**
 * Singly Linked List that stores a single patient's visit history.
 * Each Patient object owns exactly one VisitHistory instance.
 *
 * Supports: add visit, remove visit, search visit, display history.
 * Built as a custom linked list (not java.util.LinkedList) so that
 * the node structure is explicit, as usually expected in this assignment.
 */
public class VisitHistory {

    // A single node in the singly linked list.
    // "next" points only forward - hence "singly" linked.
    private class VisitNode {
        Visit data;
        VisitNode next;

        VisitNode(Visit data) {
            this.data = data;
        }
    }

    private VisitNode head;

    // ---------- ADD ----------
    // Adds a new visit to the end of the list (most recent visit last).
    public void addVisit(Visit visit) {
        VisitNode newNode = new VisitNode(visit);
        if (head == null) {
            head = newNode;
            return;
        }
        VisitNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // ---------- REMOVE ----------
    // Removes the visit with the given visitId, if it exists.
    public boolean removeVisit(int visitId) {
        if (head == null) {
            System.out.println("Visit history is empty. Nothing to remove.");
            return false;
        }

        // Special case: removing the head node.
        if (head.data.getVisitId() == visitId) {
            head = head.next;
            System.out.println("Visit ID " + visitId + " removed.");
            return true;
        }

        VisitNode current = head;
        while (current.next != null && current.next.data.getVisitId() != visitId) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Visit ID " + visitId + " not found.");
            return false;
        }

        // Bypass the node to be removed.
        current.next = current.next.next;
        System.out.println("Visit ID " + visitId + " removed.");
        return true;
    }

    // ---------- SEARCH ----------
    public Visit searchVisit(int visitId) {
        VisitNode current = head;
        while (current != null) {
            if (current.data.getVisitId() == visitId) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    // ---------- DISPLAY ----------
    public void displayHistory() {
        if (head == null) {
            System.out.println("No visit history found for this patient.");
            return;
        }
        VisitNode current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }
}
