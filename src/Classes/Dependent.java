//Luong Tuan Kiet - s3980288

package Classes;

import Classes.Customer;

import java.util.Date;
import java.util.List;

public class Dependent extends Customer {

    private String policyHolderId;
    public Dependent(String id, String insuredPerson, String cardNumber, Claim claims[]) {
        super(id, insuredPerson, cardNumber , claims);
    }

    public String getPolicyHolderId() {
        return policyHolderId;
    }

    public void setPolicyHolderId(String policyHolderId) {
        this.policyHolderId = policyHolderId;
    }
}