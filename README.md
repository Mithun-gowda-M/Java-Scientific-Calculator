# Java Scientific Calculator

[![Java Version](https://img.shields.io/badge/Java-21%2B%20%2F%2027-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

A modular, robust, object-oriented **Scientific Calculator** developed in Java. The project implements comprehensive scientific computing features including core arithmetic, powers and roots, logarithms, combinatorics, trigonometric suites, matrix algebra, and linear/quadratic equation solvers.

---

## 👥 Activity Division & Contribution Split

This project was developed as a collaborative pair programming activity, divided into two distinct modular parts between **Mithun Gowda M** and **Rakshitha R**:

| Contributor | Part / Role | Package | Key Components & Implemented Features |
| :--- | :--- | :--- | :--- |
| **Mithun Gowda M**<br>([@Mithun-gowda-M](https://github.com/Mithun-gowda-M)) | **Part 1: Core Calculator & Architecture** | `calculator` | • Application driver & Interactive CLI Menu (`Calc.java`)<br>• Basic Arithmetic: `+`, `-`, `*`, `/`, `%` with zero-division safety<br>• Scientific Math: Exponentiation ($x^y$), Square/Cube Roots, Factorial ($n!$), Common Log ($\log_{10}$), Natural Log ($\ln$), Absolute Value<br>• History & Memory Buffer: Session calculation logging with capacity management<br>• Encapsulated access control & resilient input validation |
| **Rakshitha R**<br>([@rakshitha-205](https://github.com/rakshitha-205)) | **Part 2: Advanced Scientific Suite** | `calculator.advanced` | • **Trigonometry (`Trig.java`)**: Standard (`sin`, `cos`, `tan`), Reciprocal (`cosec`, `sec`, `cot`), Inverse (`asin`, `acos`, `atan`), Hyperbolic (`sinh`, `cosh`, `tanh`), Constants ($\pi, e$)<br>• **Matrix Algebra (`Matrix.java`)**: Addition, Subtraction, Matrix Multiplication ($A \times B$), Scalar Multiplication, Transpose ($A^T$), 2x2 & 3x3 Determinants ($|A|$), Formatted Pretty-Printing<br>• **Equation Solvers (`Eqn.java`)**: Linear ($ax + b = 0$), Quadratic ($ax^2 + bx + c = 0$) with real & complex roots, 2-Variable Linear System via Cramer's Rule |

---

## 📐 Key Academic Requisites Demonstrated

### 1. Access Control
- **`public`**: Exposed API endpoints (`Calc.main`, `Trig.sin`, `Matrix.multiply`, `Eqn.solveQuadratic`, etc.) allowing seamless inter-package communication.
- **`private`**:
  - Utility class constructors (`private Trig()`, `private Matrix()`, `private Eqn()`) preventing unauthorized instantiation.
  - Internal validation and helper methods (`validateSameDimensions`, `validateSquareMatrix`, `readMatrix2x2`, etc.) hidden from external packages.
  - Private encapsulated state variables (`sc`, `history`, `historyCount`).
- **Package Separation**: Separation between the core driver package `calculator` and the specialized math engine `calculator.advanced`.

### 2. Static & Final
- **`final` Classes**: Mathematical utility classes (`Trig`, `Matrix`, `Eqn`) declared `final` to prevent subclassing.
- **`final` Constants**: Immutable mathematical and application constants (`public static final double PI`, `E`, `APP_TITLE`, `VERSION`, `MAX_HISTORY`).
- **`final` Method Parameters**: Protects incoming calculation operands and matrices against accidental mutation.
- **`static` Methods**: Operations designed as pure, stateless mathematical utilities invoked directly via class references (e.g., `Trig.cos(...)`, `Matrix.add(...)`).

---

## 🏗️ Project Architecture & Directory Structure

```text
Java-Scientific-Calculator/
├── src/
│   └── calculator/
│       ├── Calc.java                   # [Part 1 - Mithun] Main interactive driver, basic math & history
│       └── advanced/
│           ├── Trig.java               # [Part 2 - Rakshitha] Full trigonometric suite & constants
│           ├── Matrix.java             # [Part 2 - Rakshitha] 2x2 and 3x3 Matrix operations & determinants
│           └── Eqn.java                # [Part 2 - Rakshitha] Linear, Quadratic, and 2-Variable equation solvers
├── bin/                                # Compiled bytecode (.class files, gitignored)
├── .gitignore                          # Git ignore rules for build artifacts
└── README.md                           # Documentation and activity split
```

---

## 🚀 Compilation and Execution

### Prerequisites
- Java Development Kit (JDK 17 or higher, Java 21 / 27 supported)

### Steps

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Mithun-gowda-M/Java-Scientific-Calculator.git
   cd Java-Scientific-Calculator
   ```

2. **Compile all Java source files:**
   ```bash
   # Windows (PowerShell / Command Prompt)
   javac -d bin src/calculator/*.java src/calculator/advanced/*.java

   # Linux / macOS
   javac -d bin src/calculator/*.java src/calculator/advanced/*.java
   ```

3. **Run the Scientific Calculator:**
   ```bash
   java -cp bin calculator.Calc
   ```

---

## 💻 Sample Feature Walkthrough

### 1. Main Menu
```text
=================================================
      JAVA SCIENTIFIC CALCULATOR (v2.0)
   Developed by: Mithun Gowda M & Rakshitha R
=================================================

---------------- MAIN MENU ----------------
1. Basic Arithmetic (+, -, *, /, %)
2. Scientific Math (Power, Root, Log, Fact, Abs)
3. Trigonometry (sin, cos, tan, inv, hyp)
4. Matrix Operations (+, -, *, Transpose, Det)
5. Equation Solvers (Linear, Quadratic, 2x2 System)
6. Calculation History & Memory
7. About Project & Team Division
8. Exit
-------------------------------------------
```

### 2. Quadratic Equation Solver (Handling Complex Roots)
```text
Solving ax^2 + bx + c = 0:
Enter coefficient a: 1
Enter coefficient b: 2
Enter constant c: 5
Quadratic Equation Roots:
Root 1 = -1.0000 + 2.0000i
Root 2 = -1.0000 - 2.0000i (Complex Conjugates)
```

### 3. Matrix Algebra (Addition & Determinants)
```text
Matrix Addition Result:
[     6.00     8.00 ]
[    10.00    12.00 ]

Determinant |M| = -2.00
```

### 4. Calculation History & Memory Buffer
```text
--- CALCULATION HISTORY ---
[01] 15.5 + 4.5 = 20.0
[02] 5! = 120
[03] Trig overview for 30.0 deg
[04] Solved 1.0x^2 + -5.0x + 6.0 = 0
[05] Solved 1.0x^2 + 2.0x + 5.0 = 0
```

---

## 📄 License
This project is open-source and created for educational and academic coursework demonstration.
