package lk.ijse.dto;

public class BranchDTO {

    private String id;
    private String name;
    private String admin_id;

    public BranchDTO() {
    }

    public BranchDTO(String id, String name, String admin_id) {
        this.id = id;
        this.name = name;
        this.admin_id = admin_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    @Override
    public String toString() {
        return "BranchDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", admin_id='" + admin_id + '\'' +
                '}';
    }
}
