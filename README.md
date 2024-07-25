# Simple Calculator

## Overview

This Java application is a basic calculator that performs addition, subtraction, multiplication, and division. It provides a graphical user interface (GUI) using `Swing` components.

## Features

- **Basic Arithmetic Operations**: Addition (+), Subtraction (-), Multiplication (*), Division (/).
- **Error Handling**: Handles division by zero and invalid expressions gracefully.
- **Clear Functionality**: Clears the current input.

## Design Choices

- **User Interface**: Built using `Swing` components (`JFrame`, `JTextField`, `JPanel`, `JButton`). The calculator features a display area at the top and a grid of buttons for digits and operations.
- **Input Handling**: Utilizes a `StringBuilder` to build the current input expression. Buttons for digits and operations update this expression.
- **Expression Evaluation**: 
  - **Order of Operations**: Multiplication and division are handled before addition and subtraction.
  - **Algorithm**: The `evaluateExpression` method splits the expression by multiplication and division operators and evaluates them. The `evaluateAdditionSubtraction` method handles addition and subtraction.

## Algorithms Used

1. **Expression Parsing**:
   - The `evaluateExpression` method splits the input expression by `*` and `/` and processes these operators first.
   - The `evaluateAdditionSubtraction` method processes addition and subtraction on the remaining expression.

2. **Error Handling**:
   - Checks for division by zero and invalid expressions to provide appropriate feedback.

## Compilation and Execution

1. **Install Java Development Kit (JDK)**:
   Ensure that you have the JDK installed on your system. You can download it from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html).

2. **Set Up VS Code for Java**:
   - Install the Java Extension Pack for VS Code. You can find it in the Extensions view (`Ctrl+Shift+X`) by searching for "Java Extension Pack".
   - Ensure that VS Code recognizes your JDK installation.

3. **Save the File**:
   Save the code in your VS Code workspace.

4. **Compile the Java File**:
   - Open the terminal in VS Code (`Ctrl+` `).
   - Navigate to the directory where it is saved.

5. **Run the Application**:
   After successful compilation, run the application using the following command:
   ```sh
   java SimpleCalculator
