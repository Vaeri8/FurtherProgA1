//Luong Tuan Kiet - s3980288

package Classes;

import java.util.Date;

public class Customer {
    private String id;
    private String insuredPerson; // basically the name
    private String cardNumber;
    private Claim[] claims;
    private Dependent[] dependents;

    public Customer(String id, String insuredPerson, String cardNumber, Claim[] claims) {
        this.id = id;
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.claims = claims;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInsuredPerson() {
        return insuredPerson;
    }

    public void setInsuredPerson(String insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Claim[] getClaims() {
        return claims;
    }

    public void setClaims(Claim[] claims) {
        this.claims = claims;
    }

}