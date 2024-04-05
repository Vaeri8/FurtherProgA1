//Luong Tuan Kiet - s3980288

import java.util.ArrayList;
import java.util.List;

class PolicyHolder extends Customer {
    List<Dependent> dependents;

    public PolicyHolder(String id, String fullName, String insuranceCard) {
        super(id, fullName, insuranceCard);
        this.dependents = new ArrayList<>();
    }

    public List<Dependent> getDependents() {
        return dependents;
    }

    public void addDependent(Dependent dependent) {
        this.dependents.add(dependent);
    }
}