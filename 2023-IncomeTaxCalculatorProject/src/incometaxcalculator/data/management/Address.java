package incometaxcalculator.data.management;

public class Address {

  private final String country;
  private final String city;
  private final String street;
  private final int number;

  public Address(String country, String city, String street, int number) {
    this.country = country;
    this.city = city;
    this.street = street;
    this.number = number;
  }

  public String getCountry() {
    return country;
  }

  public String getCity() {
    return city;
  }

  public String getStreet() {
    return street;
  }

  public int getNumber() {
    return number;
  }

  public String toString() {
    return (country + " " + city + " " + street + " " + number);
  }
}