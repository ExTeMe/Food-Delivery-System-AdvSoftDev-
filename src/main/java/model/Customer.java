package model;

import java.util.Date;

public class Customer {
    private Integer customerId;
    private Long cardNumber;
    private Date cardExpiration;
    private Integer cardPin;
    private String cardName;
    private User user;

    public Customer(){}
    public Customer(Integer customerId, Long cardNumber, Date cardExpiration, Integer cardPin, String cardName, User user) {
        this.customerId = customerId;
        this.cardNumber = cardNumber;
        this.cardExpiration = cardExpiration;
        this.cardPin = cardPin;
        this.cardName = cardName;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", cardNumber=" + cardNumber +
                ", cardExpiration=" + cardExpiration +
                ", cardPin=" + cardPin +
                ", cardName='" + cardName + '\'' +
                ", user=" + user +
                '}';
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getCardExpiration() {
        return cardExpiration;
    }

    public void setCardExpiration(Date cardExpiration) {
        this.cardExpiration = cardExpiration;
    }

    public Integer getCardPin() {
        return cardPin;
    }

    public void setCardPin(Integer cardPin) {
        this.cardPin = cardPin;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
