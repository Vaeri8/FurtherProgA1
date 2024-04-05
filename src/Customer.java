import java.util.ArrayList;
import java.util.List;

class Customer {
    String id;
    String fullName;
    String insuranceCard;
    List<Claim> claims;

    public Customer(String id, String fullName, String insuranceCard) {
        this.id = id;
        this.fullName = fullName;
        this.insuranceCard = insuranceCard;
        this.claims = new ArrayList<>();
    }

    public List<Claim> getClaims() {
        return claims;
    }

    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }
}