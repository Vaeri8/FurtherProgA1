//Luong Tuan Kiet - s3980288

import java.util.ArrayList;
import java.util.List;

class Customer {
    String id;
    String fullName;
    String insuranceCard;
    List<Claim> claims;
    private boolean name;

    public Customer(String id, String fullName, String insuranceCard) {
        this.id = id;
        this.fullName = fullName;
        this.insuranceCard = insuranceCard;
        this.claims = new ArrayList<>();
    }

    public boolean getName() {
        return name;
    }

    public void setName(boolean name) {
        this.name = name;
    }

    public String getinsuranceCard() {
        return insuranceCard;
    }

    public void setInsuranceCard(boolean address) {
        this.insuranceCard = insuranceCard;
    }

    public List<Claim> getClaims() {
        return claims;
    }

    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }

}