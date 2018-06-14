package team.project.dairymanagementsystem.auth;

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
