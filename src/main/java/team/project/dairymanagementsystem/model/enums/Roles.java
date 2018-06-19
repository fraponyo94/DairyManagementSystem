package team.project.dairymanagementsystem.model.enums;

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
