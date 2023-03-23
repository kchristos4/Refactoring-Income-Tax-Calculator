package incometaxcalculator.data.management;

public class MarriedFilingJointlyTaxpayer extends Taxpayer {

  public MarriedFilingJointlyTaxpayer(String fullname, int taxRegistrationNumber, float income) {
    super(fullname, taxRegistrationNumber, income);
  }

  public double calculateBasicTax() {
    if (income < 36080) {
      return 0.0535 * income;
    } else if (income < 90000) {
      return 1930.28 + 0.0705 * (income - 36080);
    } else if (income < 143350) {
      return 5731.64 + 0.0705 * (income - 90000);
    } else if (income < 254240) {
      return 9492.82 + 0.0785 * (income - 143350);
    } else {
      return 18197.69 + 0.0985 * (income - 254240);
    }
  }

}