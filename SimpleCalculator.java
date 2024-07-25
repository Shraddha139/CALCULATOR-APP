import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SimpleCalculator extends JFrame {
    private JTextField display;
    private StringBuilder currentInput = new StringBuilder();

    public SimpleCalculator() {
        // Set up the frame
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create and set up the display
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 30));
        add(display, BorderLayout.NORTH);

        // Create the button panel and add buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }

    // Inner Class: ButtonClickListener

    private class ButtonClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("=")) {
                try {
                    String expression = currentInput.toString();
                    double result = evaluate(expression);
                    display.setText(Double.toString(result));
                } catch (ArithmeticException ex) {
                    display.setText("Error");
                } catch (Exception ex) {
                    display.setText("Invalid Expression");
                }
                currentInput.setLength(0);
            } else if (command.equals("C")) {
                currentInput.setLength(0);
                display.setText("");
            } else {
                currentInput.append(command);
                display.setText(currentInput.toString());
            }
        }

        private double evaluate(String expression) throws Exception {
            expression = expression.replaceAll(" ", ""); // Remove spaces
            return evaluateExpression(expression);
        }

        private double evaluateExpression(String expression) throws Exception {
            // First handle multiplication (*) and division (/)
            String[] parts = expression.split("(?=[*/])|(?<=[*/])");
            if (parts.length == 1) return evaluateAdditionSubtraction(parts[0]);

            double result = evaluateAdditionSubtraction(parts[0]);
            for (int i = 1; i < parts.length; i += 2) {
                char operator = parts[i].charAt(0);
                double nextValue = evaluateAdditionSubtraction(parts[i + 1]);
                switch (operator) {
                    case '*':
                        result *= nextValue;
                        break;
                    case '/':
                        if (nextValue == 0) throw new ArithmeticException("Division by zero");
                        result /= nextValue;
                        break;
                    default:
                        throw new Exception("Invalid Expression");
                }
            }
            return result;
        }

        private double evaluateAdditionSubtraction(String expression) throws Exception {
            // Process addition (+) and subtraction (-)
            String[] parts = expression.split("(?=[+-])|(?<=[+-])");
            double result = Double.parseDouble(parts[0]);
            for (int i = 1; i < parts.length; i += 2) {
                char operator = parts[i].charAt(0);
                double nextValue = Double.parseDouble(parts[i + 1]);
                switch (operator) {
                    case '+':
                        result += nextValue;
                        break;
                    case '-':
                        result -= nextValue;
                        break;
                    default:
                        throw new Exception("Invalid Expression");
                }
            }
            return result;
        }
    }
    // Main Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleCalculator calculator = new SimpleCalculator();
            calculator.setVisible(true);
        });
    }
}

