//Luong Tuan Kiet - s3980288

package Classes;

import Classes.Customer;
import Classes.Dependent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PolicyHolder extends Customer {
    private List<Dependent> dependents;

    public PolicyHolder(String id, String insuredPerson, String cardNumber, List<Dependent> dependents, Claim[] claims) {
        super(id, insuredPerson, cardNumber, claims);
        this.dependents = dependents;
    }

    public List<Dependent> getDependents() {
        return dependents;
    }

    public void setDependents(List<Dependent> dependents) {
        this.dependents = dependents;
    }


}