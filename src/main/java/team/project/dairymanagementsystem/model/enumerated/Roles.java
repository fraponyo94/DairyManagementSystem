package team.project.dairymanagementsystem.model.enumerated;

public enum Roles {
    USER("user"), ADMIN("admin"), SUPPLIER("supplier");

    private String roleName;

    private Roles(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return this.roleName;
    }
}
