package incometaxcalculator.data.management;

public class Company {

  private final String name;
  private final Address address;

  public Company(String name, String country, String city, String street, int number) {
    this.name = name;
    this.address = new Address(country, city, street, number);
  }

  public String getName() {
    return name;
  }

  // poy xreiazomaste thn getAddress? jo
  public String getAddress() {
    return address.toString();
  }

  public String getCountry() {
    return address.getCountry();
  }

  public String getCity() {
    return address.getCity();
  }

  public String getStreet() {
    return address.getStreet();
  }

  public int getNumber() {
    return address.getNumber();
  }
}