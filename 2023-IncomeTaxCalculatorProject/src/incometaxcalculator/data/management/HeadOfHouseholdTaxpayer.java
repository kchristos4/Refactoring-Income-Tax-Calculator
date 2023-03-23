package incometaxcalculator.data.management;

public class HeadOfHouseholdTaxpayer extends Taxpayer {

  public HeadOfHouseholdTaxpayer(String fullname, int taxRegistrationNumber, float income) {
    super(fullname, taxRegistrationNumber, income);
  }

  public double calculateBasicTax() {
    if (income < 30390) {
      return 0.0535 * income;
    } else if (income < 90000) {
      return 1625.87 + 0.0705 * (income - 30390);
    } else if (income < 122110) {
      return 5828.38 + 0.0705 * (income - 90000);
    } else if (income < 203390) {
      return 8092.13 + 0.0785 * (income - 122110);
    } else {
      return 14472.61 + 0.0985 * (income - 203390);
    }
  }

}
