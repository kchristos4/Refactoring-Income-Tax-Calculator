package incometaxcalculator.data.management;

import java.util.HashMap;
import java.util.Map.Entry;

import incometaxcalculator.exceptions.WrongReceiptKindException;

public abstract class Taxpayer {

  private String Type = [HOH,MFJ,MFS,S]
  protected final String fullname;
  protected final int taxRegistrationNumber;
  protected final float income;
  private float amountPerReceiptsKind[] = new float[5];
  private int totalReceiptsGathered = 0;
  private HashMap<Integer, Receipt> receiptHashMap = new HashMap<Integer, Receipt>(0);
  private HashMap<String,Integer> receiptTypeHashMap = new HashMap<String,Integer>();
  /*private static final short ENTERTAINMENT = 0;
  private static final short BASIC = 1;
  private static final short TRAVEL = 2;
  private static final short HEALTH = 3;
  private static final short OTHER = 4;*/
  
  

  public double calculateBasicTax(double[] constants) {
    if (income < constants[0]) {
      return  constants[1]* income;
    } else if (income < constants[2]) {
      return  constants[3]+constants[4]  * (income - constants[5]);
    } else if (income < constants[6]) {
      return  constants[7]+ constants[8] * (income - constants[9]);
    } else if (income < constants[10]) {
      return  constants[11]+constants[12]  * (income - constants[13]);
    } else {
      return  constants[14]+ constants[15] * (income - constants[16]);
    }
  }

  protected Taxpayer(String fullname, int taxRegistrationNumber, float income) {
    this.fullname = fullname;
    this.taxRegistrationNumber = taxRegistrationNumber;
    this.income = income;
  }

  public void addReceipt(Receipt receipt) throws WrongReceiptKindException {
    //TODO delete to comment apo katw
    /*if (receipt.getKind().equals("Entertainment")) {
      amountPerReceiptsKind[ENTERTAINMENT] += receipt.getAmount();
    } else if (receipt.getKind().equals("Basic")) {
      amountPerReceiptsKind[BASIC] += receipt.getAmount();
    } else if (receipt.getKind().equals("Travel")) {
      amountPerReceiptsKind[TRAVEL] += receipt.getAmount();
    } else if (receipt.getKind().equals("Health")) {
      amountPerReceiptsKind[HEALTH] += receipt.getAmount();
    } else if (receipt.getKind().equals("Other")) {
      amountPerReceiptsKind[OTHER] += receipt.getAmount();
    } else {
      throw new WrongReceiptKindException();
    }
    */
    if (receiptTypeHashMap.isEmpty()){
      initializeReceiptTypeHashMap();
    }
    
    receiptHashMap.put(receipt.getId(), receipt);
    totalReceiptsGathered++;
    
    try {
      amountPerReceiptsKind[receiptTypeHashMap.get(receipt.getKind())]+= receipt.getAmount();
    }catch(Exception e){
      throw new WrongReceiptKindException();
    }
  }
  
  
  
  public void removeReceipt(int receiptId) throws WrongReceiptKindException {
    //TODO delete to comment apo katw
    Receipt receipt = receiptHashMap.get(receiptId);
    /*if (receipt.getKind().equals("Entertainment")) {
      amountPerReceiptsKind[ENTERTAINMENT] -= receipt.getAmount();
    } else if (receipt.getKind().equals("Basic")) {
      amountPerReceiptsKind[BASIC] -= receipt.getAmount();
    } else if (receipt.getKind().equals("Travel")) {
      amountPerReceiptsKind[TRAVEL] -= receipt.getAmount();
    } else if (receipt.getKind().equals("Health")) {
      amountPerReceiptsKind[HEALTH] -= receipt.getAmount();
    } else if (receipt.getKind().equals("Other")) {
      amountPerReceiptsKind[OTHER] -= receipt.getAmount();
    } else {
      throw new WrongReceiptKindException();
    }*/
    totalReceiptsGathered--;
    receiptHashMap.remove(receiptId);
    
    if (receiptTypeHashMap.isEmpty()){
      initializeReceiptTypeHashMap();
    }
    
    try {
      amountPerReceiptsKind[receiptTypeHashMap.get(receipt.getKind())]-= receipt.getAmount();
    }catch(Exception e){
      throw new WrongReceiptKindException();
    }
  }

  public void initializeReceiptTypeHashMap() {
    receiptTypeHashMap.put("Entertainment",0);
    receiptTypeHashMap.put("Basic",1);
    receiptTypeHashMap.put("Travel",2);
    receiptTypeHashMap.put("Health",3);
    receiptTypeHashMap.put("Other",4);
  }
  
  public String getFullname() {
    return fullname;
  }

  public int getTaxRegistrationNumber() {
    return taxRegistrationNumber;
  }

  public float getIncome() {
    return income;
  }

  public HashMap<Integer, Receipt> getReceiptHashMap() {
    return receiptHashMap;
  }

  public double getVariationTaxOnReceipts(myType ) {
    float totalAmountOfReceipts = getTotalAmountOfReceipts();
    /*if (totalAmountOfReceipts < 0.2 * income) {
      return calculateBasicTax() * 0.08;
    } else if (totalAmountOfReceipts < 0.4 * income) {
      return calculateBasicTax() * 0.04;
    } else if (totalAmountOfReceipts < 0.6 * income) {
      return -calculateBasicTax() * 0.15;
    } else {
      return -calculateBasicTax() * 0.3;
    }*/
    
    HashMap<Double,Double>  Variation = new HashMap<Double,Double>();
    Variation.put(0.2*income,calculateBasicTax(getConstants())*0.08);
    Variation.put(0.4*income,calculateBasicTax(getConstants())*0.04);
    Variation.put(0.6*income,-calculateBasicTax()*0.15);
    //Variation.put(1.0*income,-calculateBasicTax()*0.3);
    for(Entry<Double,Double> d : Variation.entrySet()) {
      if (totalAmountOfReceipts<d.getKey()) {
        return d.getValue();
      }
    }
    return -calculateBasicTax()*0.3;
  }

  private float getTotalAmountOfReceipts() {
    int sum = 0;
    for (int i = 0; i < 5; i++) {
      sum += amountPerReceiptsKind[i];
    }
    return sum;
  }

  public int getTotalReceiptsGathered() {
    return totalReceiptsGathered;
  }

  public float getAmountOfReceiptKind(short kind) {
    return amountPerReceiptsKind[kind];
  }

  public double getTotalTax() {
    return calculateBasicTax() + getVariationTaxOnReceipts();
  }

  public double getBasicTax() {
    return calculateBasicTax();
  }

}