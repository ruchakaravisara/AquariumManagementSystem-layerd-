package lk.ijse.aquarium.dto;

public class OrdersDTO {
    private String id;

    private String cid;
    private String did;

    public OrdersDTO(String id) {
        this.id = id;
    }

    public OrdersDTO(String id, String cid, String did) {
        this.id = id;
        this.cid = cid;
        this.did = did;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + id + '\'' +
                ", cid='" + cid + '\'' +
                ", did='" + did + '\'' +
                '}';
    }
}
