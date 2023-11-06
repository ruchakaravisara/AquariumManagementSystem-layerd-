package lk.ijse.aquarium.dto;

public class CustomerPaymentDTO {
    private  String id;
    private  Double value;
    private  String cId;

    public CustomerPaymentDTO(String id) {}

    public CustomerPaymentDTO(String id, Double value, String cId) {
        this.id = id;
        this.value = value;
        this.cId = cId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    @Override
    public String toString() {
        return "CustomerPayment{" +
                "id='" + id + '\'' +
                ", value=" + value +
                ", cId='" + cId + '\'' +
                '}';
    }
}
