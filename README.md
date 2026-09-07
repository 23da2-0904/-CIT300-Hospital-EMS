# Mini Hospital Emergency Management System

CIT300 - Data Structures and Algorithms - Individual Mid Assignment
Sri Lanka Technology Campus

## Overview
A console-based Java application that simulates core hospital emergency
operations using four classic data structures.

## Data Structures Used

| Feature | Data Structure | File(s) |
|---|---|---|
| Patient Records | Binary Search Tree (BST), keyed by Patient ID | `PatientBST.java`, `Patient.java` |
| Emergency Patient Queue | Queue (FIFO) | `EmergencyQueue.java` |
| Treatment History | Stack (LIFO) | `TreatmentStack.java`, `TreatmentRecord.java` |
| Patient Visit History | Singly Linked List | `VisitHistory.java`, `Visit.java` |

## How It Works
- **Patient Records (BST):** Every patient registered in the system is
  inserted into a Binary Search Tree using their Patient ID as the key.
  This allows fast insert/search/delete and an in-order traversal that
  naturally lists patients in ascending Patient ID order.
- **Emergency Queue:** When a registered patient arrives at the emergency
  unit, they are enqueued. Staff dequeue the patient at the front of the
  line for treatment, following First-In-First-Out order.
- **Treatment Stack:** Once a treatment is completed, a treatment record
  is pushed onto a stack. The most recently completed treatment is always
  on top (Last-In-First-Out), useful for quickly reviewing the latest cases.
- **Visit History (Linked List):** Each `Patient` object owns its own
  singly linked list of `Visit` records, representing that patient's past
  hospital visits (visit date, doctor, diagnosis, treatment).

## How to Run
1. Open the project folder in VS Code (with the Java Extension Pack installed).
2. Open `src/Main.java`.
3. Run the file (Run ▶ button, or `Run Java` from the command palette).
4. Follow the on-screen menu.

### Run from terminal (alternative)
```bash
cd src
javac *.java
java Main
```

## Project Structure
```
HospitalEMS/
├── src/
│   ├── Main.java              # Menu-driven entry point
│   ├── Patient.java           # Patient record model
│   ├── PatientBST.java        # BST for patient records
│   ├── EmergencyQueue.java    # FIFO queue for waiting patients
│   ├── TreatmentRecord.java   # Completed treatment model
│   ├── TreatmentStack.java    # LIFO stack for treatment history
│   ├── Visit.java             # Single visit record model
│   └── VisitHistory.java      # Singly linked list of a patient's visits
└── README.md
```

## Sample Usage Flow
1. Insert a patient via **Patient Records > Insert Patient**.
2. Add that patient to the **Emergency Queue**.
3. Dequeue them for treatment, then log a **Treatment Record** on the Stack.
4. Add a **Visit** entry to their personal visit history.

## Author
 HH.HAFSA 23DA2-0904
