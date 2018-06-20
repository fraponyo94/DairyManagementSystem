package team.project.dairymanagementsystem.model.enums;

public enum Status {
    PENDING("pending"), APPROVED("approved"), DENIED("denied"), CANCELLED("cancelled");
    private String status;

    private Status(String status){
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
