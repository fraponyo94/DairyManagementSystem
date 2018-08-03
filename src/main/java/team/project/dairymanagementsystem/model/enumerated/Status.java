package team.project.dairymanagementsystem.model.enumerated;

/**
 * An enum for the various stages a contract application can be in
 * PENDING - new applications
 * APPROVED - approved applications that were previously pending
 * DENIED - denied applications that were previously pending
 * CANCELLED - cancelled applications that were previously approved
 */
public enum Status {
    PENDING, APPROVED, DENIED, CANCELLED;
}
