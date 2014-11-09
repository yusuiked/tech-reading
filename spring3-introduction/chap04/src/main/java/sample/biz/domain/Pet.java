package sample.biz.domain;

import java.util.Date;

public class Pet {
    private Long petId;
    private String petName;
    private String ownerName;
    private int price;
    private Date birthDate;

    public Pet() {
    }

    public Pet(Long petId, String petName) {
        this.petId = petId;
        this.petName = petName;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petId=" + petId +
                ", petName='" + petName + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", price=" + price +
                ", birthDate=" + birthDate +
                '}';
    }
}
