package incometaxcalculator.data.management;

public class SingleTaxpayer extends Taxpayer {

  private double[] constants = {24680,0.0535,81080,1320.38,0.0705,24680,90000
      ,5296.58,0.0785,81080,152540,5996.80,0.0785,90000,10906.19,0.0985,152540};
  public SingleTaxpayer(String fullname, int taxRegistrationNumber, float income) {
    super(fullname, taxRegistrationNumber, income);
  }

  public double[] getConstants() {
    return constants;
  }

}