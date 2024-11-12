import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class SwingCalculator {
    private JFrame frame;
    private JPanel mainPanel;
    private JTextField textField;
    private JButton[] numberButtons = new JButton[10];
    private JButton addButton, subtractButton, multiplyButton, divideButton;
    private JButton clearButton, deleteButton, equalsButton, percentButton, ceButton;
    private JButton signChangeButton, decimalButton;

    private double num1, num2, result;
    private String operator;

    public SwingCalculator() {
        // Set up the frame with user-defined layout
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(400, 100, 500, 600);
        frame.setLayout(null);  // User-defined layout

        // Text field positioned above the panel
        textField = new JTextField(25);
        textField.setBounds(20, 20, 440, 50);  // Position and size
        frame.add(textField);

        // Main panel with predefined layout for buttons
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 4, 10, 10));  // 5 rows, 4 columns, with spacing
        mainPanel.setBounds(20, 80, 440, 450);  // Position and size
        frame.add(mainPanel);

        // Initialize operator and function buttons
        percentButton = new JButton("%");
        ceButton = new JButton("CE");
        deleteButton = new JButton("<-");
        divideButton = new JButton("÷");
        multiplyButton = new JButton("X");
        subtractButton = new JButton("-");
        addButton = new JButton("+");
        equalsButton = new JButton("=");
        clearButton = new JButton("C");
        signChangeButton = new JButton("+/-");
        decimalButton = new JButton(".");

        // Initialize number buttons
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
        }

        // Add action listeners to the buttons
        ButtonActionListener actionListener = new ButtonActionListener();
        addButton.addActionListener(actionListener);
        subtractButton.addActionListener(actionListener);
        multiplyButton.addActionListener(actionListener);
        divideButton.addActionListener(actionListener);
        clearButton.addActionListener(actionListener);
        deleteButton.addActionListener(actionListener);
        equalsButton.addActionListener(actionListener);
        percentButton.addActionListener(actionListener);
        ceButton.addActionListener(actionListener);
        signChangeButton.addActionListener(actionListener);
        decimalButton.addActionListener(actionListener);

        for (JButton button : numberButtons) {
            button.addActionListener(actionListener);
        }

        // Add buttons to the main panel in the desired order
        mainPanel.add(percentButton);
        mainPanel.add(ceButton);
        mainPanel.add(deleteButton);
        mainPanel.add(divideButton);

        mainPanel.add(numberButtons[7]);
        mainPanel.add(numberButtons[8]);
        mainPanel.add(numberButtons[9]);
        mainPanel.add(multiplyButton);

        mainPanel.add(numberButtons[4]);
        mainPanel.add(numberButtons[5]);
        mainPanel.add(numberButtons[6]);
        mainPanel.add(subtractButton);

        mainPanel.add(numberButtons[1]);
        mainPanel.add(numberButtons[2]);
        mainPanel.add(numberButtons[3]);
        mainPanel.add(addButton);

        mainPanel.add(signChangeButton);
        mainPanel.add(numberButtons[0]);
        mainPanel.add(decimalButton);
        mainPanel.add(equalsButton);

        // Finalize frame
        frame.setVisible(true);
    }

    private class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();

            if (source == clearButton || source == ceButton) {
                textField.setText("");
                num1 = 0;
                num2 = 0;
                result = 0;
                operator = "";
            } else if (source == deleteButton) {
                String currentText = textField.getText();
                if (currentText.length() > 0) {
                    textField.setText(currentText.substring(0, currentText.length() - 1));
                }
            } else if (source == equalsButton) {
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "X":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            textField.setText("Error: Division by zero");
                            return;
                        }
                        result = num1 / num2;
                        break;
                }
                textField.setText(String.valueOf(result));
            } else if (source == addButton || source == subtractButton || source == multiplyButton || source == divideButton) {
                num1 = Double.parseDouble(textField.getText());
                operator = source.getText();
                textField.setText("");
            } else if (source == signChangeButton) {
                double currentValue = Double.parseDouble(textField.getText());
                textField.setText(String.valueOf(-currentValue));
            } else if (source == percentButton) {
                double currentValue = Double.parseDouble(textField.getText());
                textField.setText(String.valueOf(currentValue / 100));
            } else if (source == decimalButton) {
                if (!textField.getText().contains(".")) {
                    textField.setText(textField.getText() + ".");
                }
            } else {
                textField.setText(textField.getText() + source.getText());
            }
        }
    }
}

public class Calculator {
    public static void main(String[] args) {
        new SwingCalculator();
    }
}
