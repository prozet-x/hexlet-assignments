package exercise;

class Address {
    // BEGIN
    @NotNull
    @MinLength(minLength = 5)
    // END
    private String country;

    // BEGIN
    @NotNull
    @MinLength(minLength = 6)
    // END
    private String city;

    // BEGIN
    @NotNull
    @MinLength(minLength = 7)
    // END
    private String street;

    // BEGIN
    @NotNull
    @MinLength()
    // END
    private String houseNumber;

    private String flatNumber;

    Address(String country, String city, String street, String houseNumber, String flatNumber) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }
}
