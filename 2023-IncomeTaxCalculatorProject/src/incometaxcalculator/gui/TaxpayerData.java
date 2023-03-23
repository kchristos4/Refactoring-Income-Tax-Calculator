package incometaxcalculator.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import incometaxcalculator.data.management.Receipt;
import incometaxcalculator.data.management.TaxpayerManager;
import incometaxcalculator.exceptions.ReceiptAlreadyExistsException;
import incometaxcalculator.exceptions.WrongFileFormatException;
import incometaxcalculator.exceptions.WrongReceiptDateException;
import incometaxcalculator.exceptions.WrongReceiptKindException;

public class TaxpayerData extends JFrame {

  private static final short ENTERTAINMENT = 0;
  private static final short BASIC = 1;
  private static final short TRAVEL = 2;
  private static final short HEALTH = 3;
  private static final short OTHER = 4;
  private JPanel contentPane;

  public TaxpayerData(int taxRegistrationNumber, TaxpayerManager taxpayerManager) {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(200, 100, 450, 420);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    DefaultListModel<Integer> receiptsModel = new DefaultListModel<Integer>();

    JList<Integer> receiptsList = new JList<Integer>(receiptsModel);
    receiptsList.setBackground(new Color(153, 204, 204));
    receiptsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    receiptsList.setSelectedIndex(0);
    receiptsList.setVisibleRowCount(3);

    JScrollPane receiptsListScrollPane = new JScrollPane(receiptsList);
    receiptsListScrollPane.setSize(150, 200);
    receiptsListScrollPane.setLocation(100, 170);
    contentPane.add(receiptsListScrollPane);

    HashMap<Integer, Receipt> receipts = taxpayerManager.getReceiptHashMap(taxRegistrationNumber);
    Iterator<HashMap.Entry<Integer, Receipt>> iterator = receipts.entrySet().iterator();

    while (iterator.hasNext()) {
      HashMap.Entry<Integer, Receipt> entry = iterator.next();
      Receipt receipt = entry.getValue();
      receiptsModel.addElement(receipt.getId());
    }

    JButton btnAddReceipt = new JButton("Add Receipt");
    btnAddReceipt.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JPanel receiptImporterPanel = new JPanel(new GridLayout(9, 2));
        JTextField receiptID = new JTextField(16);
        JTextField date = new JTextField(16);
        JTextField kind = new JTextField(16);
        JTextField amount = new JTextField(16);
        JTextField company = new JTextField(16);
        JTextField country = new JTextField(16);
        JTextField city = new JTextField(16);
        JTextField street = new JTextField(16);
        JTextField number = new JTextField(16);
        int receiptIDValue, numberValue;
        float amountValue;
        String dateValue, kindValue, companyValue, countryValue;
        String cityValue, streetValue;
        receiptImporterPanel.add(new JLabel("Receipt ID:"));
        receiptImporterPanel.add(receiptID);
        receiptImporterPanel.add(new JLabel("Date:"));
        receiptImporterPanel.add(date);
        receiptImporterPanel.add(new JLabel("Kind:"));
        receiptImporterPanel.add(kind);
        receiptImporterPanel.add(new JLabel("Amount:"));
        receiptImporterPanel.add(amount);
        receiptImporterPanel.add(new JLabel("Company:"));
        receiptImporterPanel.add(company);
        receiptImporterPanel.add(new JLabel("Country:"));
        receiptImporterPanel.add(country);
        receiptImporterPanel.add(new JLabel("City:"));
        receiptImporterPanel.add(city);
        receiptImporterPanel.add(new JLabel("Street:"));
        receiptImporterPanel.add(street);
        receiptImporterPanel.add(new JLabel("Number:"));
        receiptImporterPanel.add(number);
        int op = JOptionPane.showConfirmDialog(null, receiptImporterPanel, "",
            JOptionPane.OK_CANCEL_OPTION);
        if (op == 0) {
          receiptIDValue = Integer.parseInt(receiptID.getText());
          dateValue = date.getText();
          kindValue = kind.getText();
          amountValue = Float.parseFloat(amount.getText());
          companyValue = company.getText();
          countryValue = country.getText();
          cityValue = city.getText();
          streetValue = street.getText();
          numberValue = Integer.parseInt(number.getText());
          try {
            taxpayerManager.addReceipt(receiptIDValue, dateValue, amountValue, kindValue,
                companyValue, countryValue, cityValue, streetValue, numberValue,
                taxRegistrationNumber);
            receiptsModel.addElement(receiptIDValue);
          } catch (IOException e1) {
            JOptionPane.showMessageDialog(null,
                "Problem with opening file ." + receiptIDValue + "_INFO.txt");
          } catch (WrongReceiptKindException e1) {
            JOptionPane.showMessageDialog(null, "Please check receipts kind and try again.");
          } catch (WrongReceiptDateException e1) {
            JOptionPane.showMessageDialog(null,
                "Please make sure your date " + "is DD/MM/YYYY and try again.");
          } catch (ReceiptAlreadyExistsException e1) {
            JOptionPane.showMessageDialog(null, "Receipt ID already exists.");
          }
        }
      }
    });
    btnAddReceipt.setBounds(0, 0, 102, 23);
    contentPane.add(btnAddReceipt);

    JButton btnDeleteReceipt = new JButton("Delete Receipt");
    btnDeleteReceipt.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JPanel receiptRemoverPanel = new JPanel(new GridLayout(2, 2));
        JTextField receiptID = new JTextField(16);
        receiptRemoverPanel.add(new JLabel("Receipt ID:"));
        receiptRemoverPanel.add(receiptID);
        int op = JOptionPane.showConfirmDialog(null, receiptRemoverPanel, "",
            JOptionPane.OK_CANCEL_OPTION);
        if (op == 0) {
          int receiptIDValue = Integer.parseInt(receiptID.getText());
          try {
            taxpayerManager.removeReceipt(receiptIDValue);
            receiptsModel.removeElement(receiptIDValue);
          } catch (IOException e1) {
            JOptionPane.showMessageDialog(null,
                "Problem with opening file ." + receiptIDValue + "_INFO.txt");
          } catch (WrongReceiptKindException e1) {
            JOptionPane.showMessageDialog(null, "Please check receipt's kind and try again.");
          }
        }
      }
    });
    btnDeleteReceipt.setBounds(100, 0, 120, 23);
    contentPane.add(btnDeleteReceipt);

    JButton btnViewReport = new JButton("View Report");
    btnViewReport.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ChartDisplay.createBarChart(taxpayerManager.getTaxpayerBasicTax(taxRegistrationNumber),
            taxpayerManager.getTaxpayerVariationTaxOnReceipts(taxRegistrationNumber),
            taxpayerManager.getTaxpayerTotalTax(taxRegistrationNumber));
        ChartDisplay.createPieChart(
            taxpayerManager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, ENTERTAINMENT),
            taxpayerManager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, BASIC),
            taxpayerManager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, TRAVEL),
            taxpayerManager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, HEALTH),
            taxpayerManager.getTaxpayerAmountOfReceiptKind(taxRegistrationNumber, OTHER));
      }
    });
    btnViewReport.setBounds(214, 0, 109, 23);
    contentPane.add(btnViewReport);

    JButton btnSaveData = new JButton("Save Data");
    btnSaveData.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JPanel fileLoaderPanel = new JPanel(new BorderLayout());
        JPanel boxPanel = new JPanel(new BorderLayout());
        JPanel taxRegistrationNumberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        JLabel TRN = new JLabel("Choose file format.");
        // JTextField taxRegistrationNumberField = new JTextField(20);
        taxRegistrationNumberPanel.add(TRN);
        // taxRegistrationNumberPanel.add(taxRegistrationNumberField);
        JPanel loadPanel = new JPanel(new GridLayout(1, 2));
        loadPanel.add(taxRegistrationNumberPanel);
        fileLoaderPanel.add(boxPanel, BorderLayout.NORTH);
        fileLoaderPanel.add(loadPanel, BorderLayout.CENTER);
        JCheckBox txtBox = new JCheckBox("Txt file");
        JCheckBox xmlBox = new JCheckBox("Xml file");

        txtBox.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            xmlBox.setSelected(false);
          }
        });

        xmlBox.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            txtBox.setSelected(false);
          }
        });
        txtBox.doClick();
        boxPanel.add(txtBox, BorderLayout.WEST);
        boxPanel.add(xmlBox, BorderLayout.EAST);

        int answer = JOptionPane.showConfirmDialog(null, fileLoaderPanel, "",
            JOptionPane.OK_CANCEL_OPTION);
        if (answer == 0) {
          int trn = 0;
          String taxRegistrationNumberFile;
          try {
            if (txtBox.isSelected()) {
              try {
                taxpayerManager.saveLogFile(taxRegistrationNumber, "txt");
              } catch (IOException e1) {
                JOptionPane.showMessageDialog(null,
                    "Problem with opening file ." + taxRegistrationNumber + "_LOG.txt");
              } catch (WrongFileFormatException e1) {
                JOptionPane.showMessageDialog(null, "Wrong file format");
              }
            } else {
              try {
                taxpayerManager.saveLogFile(taxRegistrationNumber, "xml");
              } catch (IOException e1) {
                JOptionPane.showMessageDialog(null,
                    "Problem with opening file ." + taxRegistrationNumber + "_LOG.xml");
              } catch (WrongFileFormatException e1) {
                JOptionPane.showMessageDialog(null, "Wrong file format");
              }
            }
          } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null,
                "The tax registration number must have only digits.");
          }
        }

      }
    });
    btnSaveData.setBounds(322, 0, 102, 23);
    contentPane.add(btnSaveData);

    JTextPane txtpnName = new JTextPane();
    txtpnName.setEditable(false);
    txtpnName.setText("Name :");
    txtpnName.setBounds(10, 34, 92, 20);
    contentPane.add(txtpnName);

    JTextPane txtpnTrn = new JTextPane();
    txtpnTrn.setEditable(false);
    txtpnTrn.setText("TRN :");
    txtpnTrn.setBounds(10, 65, 92, 20);
    contentPane.add(txtpnTrn);

    JTextPane txtpnStatus = new JTextPane();
    txtpnStatus.setEditable(false);
    txtpnStatus.setText("Status :");
    txtpnStatus.setBounds(10, 96, 92, 20);
    contentPane.add(txtpnStatus);

    JTextPane txtpnIncome = new JTextPane();
    txtpnIncome.setEditable(false);
    txtpnIncome.setText("Income :");
    txtpnIncome.setBounds(10, 127, 92, 20);
    contentPane.add(txtpnIncome);

    JTextArea taxpayerName = new JTextArea();
    taxpayerName.setFont(new Font("Tahoma", Font.PLAIN, 11));
    taxpayerName.setEditable(false);
    taxpayerName.setBounds(110, 34, 213, 20);
    taxpayerName.setText(taxpayerManager.getTaxpayerName(taxRegistrationNumber));
    contentPane.add(taxpayerName);

    JTextArea taxpayerTRN = new JTextArea();
    taxpayerTRN.setFont(new Font("Tahoma", Font.PLAIN, 11));
    taxpayerTRN.setEditable(false);
    taxpayerTRN.setBounds(110, 65, 213, 20);
    taxpayerTRN.setText(taxRegistrationNumber + "");
    contentPane.add(taxpayerTRN);

    JTextArea taxpayerStatus = new JTextArea();
    taxpayerStatus.setFont(new Font("Tahoma", Font.PLAIN, 11));
    taxpayerStatus.setEditable(false);
    taxpayerStatus.setBounds(110, 96, 213, 20);
    taxpayerStatus.setText(taxpayerManager.getTaxpayerStatus(taxRegistrationNumber));
    contentPane.add(taxpayerStatus);

    JTextArea taxpayerIncome = new JTextArea();
    taxpayerIncome.setFont(new Font("Tahoma", Font.PLAIN, 11));
    taxpayerIncome.setEditable(false);
    taxpayerIncome.setBounds(112, 127, 213, 20);
    taxpayerIncome.setText(taxpayerManager.getTaxpayerIncome(taxRegistrationNumber));
    contentPane.add(taxpayerIncome);

    JTextPane txtpnReceipts = new JTextPane();
    txtpnReceipts.setEditable(false);
    txtpnReceipts.setText("Receipts :");
    txtpnReceipts.setBounds(10, 170, 80, 20);
    contentPane.add(txtpnReceipts);
  }
}