# Java Advanced Scientific Calculator (v3.0-PRO)

[![Java Version](https://img.shields.io/badge/Java-21%2B%20%2F%2027-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Contributors](https://img.shields.io/badge/Contributors-Mithun%20%26%20Rakshitha-success.svg)](#-collaborative-activity-division)

An active, feature-rich, object-oriented **Scientific Calculator** engineered in Java. Built as a collaborative academic project divided between **Mithun Gowda M** and **Rakshitha R**, it incorporates comprehensive mathematical computing modules, numerical calculus, arbitrary matrix algebra, algebraic equation solvers, statistical regression, programmer base conversions, discrete mathematics, engineering unit conversions, and session memory registers.

---

## 👥 Collaborative Activity Division

The codebase is partitioned between two contributors into two packages following strict access control and static/final architectural standards:

| Contributor | Module / Role | Package | Key Implemented Features |
| :--- | :--- | :--- | :--- |
| **Mithun Gowda M**<br>([@Mithun-gowda-M](https://github.com/Mithun-gowda-M)) | **Part 1: Core Engine, Programmer & Discrete Suite** | `calculator` | • **Interactive CLI Engine (`Calc.java`)**: Real-time status banner displaying angle mode, active memory register, and last answer (`Ans`).<br>• **Live Memory System**: Full calculator memory registers ($MS$, $MR$, $M+$, $M-$, $MC$) and calculation history buffer.<br>• **Programmer Calculator (`BaseCalc.java`)**: Decimal, Binary (`0b`), Octal (`0o`), and Hexadecimal (`0x`) conversions; bitwise operations (`AND`, `OR`, `XOR`, `NOT`, bit shifts, Hamming weight).<br>• **Discrete Math & Combinatorics (`DiscreteMath.java`)**: Euclidean GCD, LCM, Primality testing, Prime factorization, Permutations ($nPr$), Combinations ($nCr$), and Fibonacci generation.<br>• **Engineering Converter & Constants (`UnitConverter.java`)**: Length, Temperature, Mass conversions; Fundamental physical constants ($c, h, G, N_A, k, e$). |
| **Rakshitha R**<br>([@rakshitha-205](https://github.com/rakshitha-205)) | **Part 2: Advanced Computational Math & Analytics** | `calculator.advanced` | • **Trigonometric & Calculus Suite (`Trig.java`)**: Dynamic Degrees/Radians mode; Standard (`sin`, `cos`, `tan`), Reciprocal (`cosec`, `sec`, `cot`), Inverse (`asin`, `acos`, `atan`), Hyperbolic (`sinh`, `cosh`, `tanh`, `asinh`, `acosh`, `atanh`); Numerical Differentiation ($f'(x)$); Numerical Integration ($\int_a^b f(x)dx$ via Simpson's 1/3 Rule).<br>• **Matrix Algebra (`Matrix.java`)**: Arbitrary $N \times M$ and square $N \times N$ operations: Addition, Subtraction, Multiplication ($A \times B$), Scalar Multiplication, Transpose ($A^T$), Trace ($\text{Tr}(A)$), Recursive Laplace Determinant ($|A|$), and Adjugate Matrix Inversion ($A^{-1}$).<br>• **Equation Solvers (`Eqn.java`)**: Linear ($ax + b = 0$), Quadratic ($ax^2 + bx + c = 0$) with real and complex conjugate roots, Cubic equations via Cardano's analytical method, $2 \times 2$ Linear Systems, and $3 \times 3$ Linear Systems via Cramer's Rule.<br>• **Statistics & Analytics (`Statistics.java`)**: Mean, Median, Mode, Sample/Population Variance, Standard Deviation, Range, and Bivariate Linear Regression ($y = mx + c$, $R^2$). |

---

## 📐 Academic Requisites Demonstrated

### 1. Access Control
- **`public`**: Public classes and methods exposing clean, well-defined mathematical APIs across packages.
- **`private`**: 
  - Utility class constructors (`private Trig()`, `private Matrix()`, `private Eqn()`, `private BaseCalc()`, etc.) preventing unauthorized instantiation.
  - Internal algorithmic subroutines (`getSubmatrix`, `replaceColumn`, `validateSameDimensions`) encapsulated within their classes.
  - State variables (`memoryRegister`, `history`, `sc`) hidden from external mutation.
- **Package Separation**: Separation between the core interface package `calculator` and the specialized math engine `calculator.advanced`.

### 2. Static & Final
- **`final` Classes**: Mathematical utility classes marked `final` to prohibit subclassing.
- **`final` Constants**: Immutable mathematical and physical constants (`PI`, `E`, `SPEED_OF_LIGHT`, `PLANCK_CONSTANT`, `APP_TITLE`, etc.).
- **`final` Parameters**: Protects operands, matrices, and arrays from unintended mutation during calculations.
- **`static` Pure Methods**: Stateless mathematical procedures designed to be invoked without object allocation overhead.

---

## 🏗️ Project Structure

```text
Java-Scientific-Calculator/
├── src/
│   ├── calculator/
│   │   ├── Calc.java               # [Mithun] Main interactive driver & memory system
│   │   ├── BaseCalc.java           # [Mithun] Programmer base converter & bitwise suite
│   │   ├── DiscreteMath.java       # [Mithun] GCD/LCM, primes, combinatorics, Fibonacci
│   │   ├── UnitConverter.java      # [Mithun] Engineering unit converter & physical constants
│   │   └── advanced/
│   │       ├── Trig.java           # [Rakshitha] Trig, inverse, hyperbolic & calculus (diff/integral)
│   │       ├── Matrix.java         # [Rakshitha] NxN Matrix algebra, inversion, determinants
│   │       ├── Eqn.java            # [Rakshitha] Linear, quadratic, cubic, 2x2 & 3x3 Cramer solvers
│   │       └── Statistics.java     # [Rakshitha] Descriptive statistics & linear regression
├── bin/                            # Pre-compiled bytecode (.class files)
├── run.bat                         # One-click Windows runner script
├── .gitignore                      # Git ignore rules
└── README.md                       # Comprehensive documentation
```

---

## 🚀 How to Run

### Method 1: Double-Click Runner (Windows)
Double-click `run.bat` or run:
```powershell
.\run.bat
```

### Method 2: Command Line
```powershell
# Compile all source files into bin:
javac -d bin src/calculator/*.java src/calculator/advanced/*.java

# Launch the interactive calculator:
java -cp bin calculator.Calc
```

### Method 3: In VS Code / Antigravity IDE
Open [`Calc.java`](src/calculator/Calc.java) and click **Run** (or press `F5`).

---

## 💻 Sample Feature Walkthrough

### 1. Active Status Bar & Main Menu
```text
===============================================================================
                   JAVA ADVANCED SCIENTIFIC CALCULATOR (v3.0-PRO)
             Collaborative Project: Mithun Gowda M & Rakshitha R
===============================================================================

-------------------------------------------------------------------------------
[ STATUS ] Angle Mode: DEGREES | Memory (M): 25.00    | Last Ans: 120.0000 
-------------------------------------------------------------------------------
 1. Basic Arithmetic (+, -, *, /, %)
 2. Scientific Math (Powers, Roots, Logs, Abs)
 3. Trigonometry, Hyperbolic & Calculus Suite [Rakshitha]
 4. Matrix Algebra, Determinants & Inversion [Rakshitha]
 5. Equation Solvers (Linear, Quadratic, Cubic, Systems) [Rakshitha]
 6. Statistics & Linear Regression [Rakshitha]
 7. Programmer Base Converter & Bitwise Operations [Mithun]
 8. Discrete Math, GCD/LCM, Primes & Combinatorics [Mithun]
 9. Unit Conversions & Physical Constants [Mithun]
10. Memory Registers (MS, MR, M+, M-, MC) & History [Mithun]
11. About Project & Collaborative Split
12. Exit
-------------------------------------------------------------------------------
```

### 2. Numerical Calculus (Numerical Derivative & Integral)
```text
Evaluating derivative of f(x) = x^2 at x = 3:
Result = 6.000000000838668

Evaluating Simpson's Integral of f(x) = x^2 from [0 to 3]:
Result = 9.000000000000000
```

### 3. Matrix Inversion & Arbitrary Determinants
```text
Determinant |M| = -2.000

Inverse Matrix (A^-1):
[   -2.000    1.000 ]
[    1.500   -0.500 ]
```

### 4. Quadratic & Cubic Solvers
```text
Solving x^2 + 2x + 5 = 0:
Root 1 = -1.0000 + 2.0000i
Root 2 = -1.0000 - 2.0000i (Complex Conjugates)
```

---

## 📄 License
This project is open-source and created for academic coursework and collaborative software engineering demonstration.
