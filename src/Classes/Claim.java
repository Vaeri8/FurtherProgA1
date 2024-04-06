//Luong Tuan Kiet - s3980288

package Classes;

import java.util.Date;
import java.util.List;
import Enum.*;

public class Claim {
    String id;
    Date claimDate;
    String insuredPerson;
    String cardNumber;
    Date examDate;
    List<String> documents;
    double claimAmount;
    ClaimStatus status;
    BankingInfo bankingInfo;

    public Claim(String id, Date claimDate, String insuredPerson, String cardNumber, Date examDate,
                 List<String> documents, double claimAmount, ClaimStatus status, BankingInfo bankingInfo) {
        this.id = id;
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = examDate;
        this.documents = documents;
        this.claimAmount = claimAmount;
        this.status = status;
        this.bankingInfo = bankingInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public void setInsuredPerson(String insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public String getInsuredPerson() {
        return insuredPerson;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

    public void setBankingInfo(BankingInfo bankingInfo) {
        this.bankingInfo = bankingInfo;
    }

    public BankingInfo getBankingInfo() {
        return bankingInfo;
    }
}