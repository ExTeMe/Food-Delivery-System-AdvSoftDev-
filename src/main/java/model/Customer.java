package model;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String dateOfBirth;

    private String streetNumber;
    private String streetName;
    private String postcode;
    private String state;
    private String country;

    private String cardNumber;
    private String cardExpiration;
    private int cardPin;
    private String cardName;

    public Customer(String firstName, String lastName, String email, String phone, String password, String dateOfBirth, String streetNumber, String streetName, String postcode, String state, String country, String cardNumber, String cardExpiration, int cardPin, String cardName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password; 
        this.dateOfBirth = dateOfBirth;

        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postcode = postcode;
        this.state = state;
        this.country = country;

        this.cardNumber = cardNumber;
        this.cardExpiration = cardExpiration;
        this.cardPin = cardPin;
        this.cardName = cardName;
    }

    public Customer(String firstName, String lastName, String email, String phone, String password, String dateOfBirth, String streetNumber, String streetName, String postcode, String state, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password; 
        this.dateOfBirth = dateOfBirth;

        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.postcode = postcode;
        this.state = state;
        this.country = country;
    }


    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStreetNumber() {
        return this.streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpiration() {
        return this.cardExpiration;
    }

    public void setCardExpiration(String cardExpiration) {
        this.cardExpiration = cardExpiration;
    }

    public int getCardPin() {
        return this.cardPin;
    }

    public void setCardPin(int cardPin) {
        this.cardPin = cardPin;
    }

    public String getCardName() {
        return this.cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    

}
