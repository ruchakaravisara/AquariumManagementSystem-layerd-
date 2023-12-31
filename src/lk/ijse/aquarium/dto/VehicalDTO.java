package lk.ijse.aquarium.dto;

public class VehicalDTO {
    private String id;
    private String name;
    private String empid;

    public VehicalDTO(String id) {}

    public VehicalDTO(String id, String name, String empid) {
        this.id = id;
        this.name = name;
        this.empid = empid;
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

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    @Override
    public String toString() {
        return "Vehical{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", empid='" + empid + '\'' +
                '}';
    }
}
