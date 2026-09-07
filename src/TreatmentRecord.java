/**
 * Represents a completed treatment record.
 * Pushed onto the TreatmentStack once a patient's treatment is finished.
 */
public class TreatmentRecord {
    private int patientId;
    private String patientName;
    private String treatmentSummary;

    public TreatmentRecord(int patientId, String patientName, String treatmentSummary) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.treatmentSummary = treatmentSummary;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getTreatmentSummary() {
        return treatmentSummary;
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId +
                " | Name: " + patientName +
                " | Treatment: " + treatmentSummary;
    }
}
