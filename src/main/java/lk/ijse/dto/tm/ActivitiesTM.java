package lk.ijse.dto.tm;

public class ActivitiesTM {

    private String id;
    private String name;

    public ActivitiesTM() {
    }

    public ActivitiesTM(String id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "ActivitiesTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
