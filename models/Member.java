package models;

public class Member {
    private final String memberID;
    private final String name;
    private final String email;
    private double fineAmount;

    public Member(String memberID, String name, String email) {
        this.memberID = memberID;
        this.name = name;
        this.email = email;
        this.fineAmount = 0;
    }

    public String getMemberID() {
        return memberID;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void addFine(double amount) {
        this.fineAmount += amount;
    }

    public void clearFine() {
        this.fineAmount = 0;
    }

    @Override
    public String toString() {
        return "ID: " + memberID + " | Name: " + name + " | Email: " + email + " | Fine: $" + fineAmount;
    }
}
