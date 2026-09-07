/**
 * Binary Search Tree (BST) used to store Patient records.
 * The Patient ID is used as the key, so the tree stays ordered
 * by Patient ID at all times (left subtree < node < right subtree).
 *
 * Supports: insert, search, delete, in-order traversal.
 */
public class PatientBST {

    // A single node of the tree. Kept private/inner since only
    // PatientBST needs to know about the tree's internal structure.
    private class Node {
        Patient data;
        Node left, right;

        Node(Patient data) {
            this.data = data;
        }
    }

    private Node root;

    // ---------- INSERT ----------

    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private Node insertRec(Node current, Patient patient) {
        if (current == null) {
            return new Node(patient);
        }

        if (patient.getPatientId() < current.data.getPatientId()) {
            current.left = insertRec(current.left, patient);
        } else if (patient.getPatientId() > current.data.getPatientId()) {
            current.right = insertRec(current.right, patient);
        } else {
            System.out.println("Patient ID " + patient.getPatientId() + " already exists. Insert skipped.");
        }
        return current;
    }

    // ---------- SEARCH ----------

    public Patient search(int patientId) {
        Node result = searchRec(root, patientId);
        return (result == null) ? null : result.data;
    }

    private Node searchRec(Node current, int patientId) {
        if (current == null || current.data.getPatientId() == patientId) {
            return current;
        }
        if (patientId < current.data.getPatientId()) {
            return searchRec(current.left, patientId);
        }
        return searchRec(current.right, patientId);
    }

    // ---------- DELETE ----------

    public void delete(int patientId) {
        root = deleteRec(root, patientId);
    }

    private Node deleteRec(Node current, int patientId) {
        if (current == null) {
            System.out.println("Patient ID " + patientId + " not found. Nothing deleted.");
            return null;
        }

        if (patientId < current.data.getPatientId()) {
            current.left = deleteRec(current.left, patientId);
        } else if (patientId > current.data.getPatientId()) {
            current.right = deleteRec(current.right, patientId);
        } else {
            // Node found - this is the node to delete.

            // Case 1: no children
            if (current.left == null && current.right == null) {
                return null;
            }
            // Case 2: one child
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }
            // Case 3: two children -> find the in-order successor
            // (smallest value in the right subtree), copy it here,
            // then delete that successor from the right subtree.
            Node successor = findMin(current.right);
            current.data = successor.data;
            current.right = deleteRec(current.right, successor.data.getPatientId());
        }
        return current;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // ---------- IN-ORDER TRAVERSAL ----------
    // Visiting left -> node -> right on a BST always
    // produces values in ascending order of the key (Patient ID).

    public void displayInOrder() {
        if (root == null) {
            System.out.println("No patient records found.");
            return;
        }
        inOrderRec(root);
    }

    private void inOrderRec(Node current) {
        if (current != null) {
            inOrderRec(current.left);
            System.out.println(current.data);
            inOrderRec(current.right);
        }
    }

    public boolean isEmpty() {
        return root == null;
    }
}
