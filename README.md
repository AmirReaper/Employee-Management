# Employee Management System

A simple **Java console-based application** that demonstrates core **Object-Oriented Programming (OOP)** principles such as **inheritance, polymorphism, abstraction, encapsulation, overriding, upcasting & downcasting, interfaces, and final classes**.  

This project has evolved from version **1.0.0** (basic OOP) into version **2.0.0**, which refactors the design into a more robust and extensible system.

---

## 🚀 Features

- Add **Full-Time** and **Part-Time** employees.  
- View all employees in the company.  
- Calculate wages with or without overtime.  
- Find the highest-paid employee.  
- Show company statistics (employee count, total employees created).  
- Demonstrates **advanced OOP concepts**:
  - Abstract classes  
  - Method overriding  
  - Interfaces (simulating multiple inheritance)  
  - Encapsulation with getters/setters  
  - Polymorphism (treating employees uniformly)  
  - Upcasting & downcasting  
  - `final` usage for immutability  

---

## 📂 Project Structure

```

src/
└── com/example/
├── Main.java              # Entry point, interactive menu
├── Company.java           # Manages employees
├── InputHelper.java       # Handles user input safely
├── Employee.java          # Abstract base class
├── FullTimeEmployee.java  # Subclass for full-time employees
├── PartTimeEmployee.java  # Subclass for part-time employees
└── BenefitEligible.java   # Interface for multiple inheritance demo

````

---

## 🆕 Version 2.0.0 (2025-09-02)

- Refactored `Employee` into an **abstract class**.  
- Added `FullTimeEmployee` and `PartTimeEmployee` subclasses.  
- Introduced `BenefitEligible` interface.  
- Implemented polymorphism, overriding, and input-driven employee creation.  
- Enhanced design with encapsulation and access modifiers.  

For detailed changes, see the [CHANGELOG.md](./CHANGELOG.md).

---

## 🛠️ Requirements

- Java 17 or higher  
- IDE (IntelliJ IDEA, Eclipse, or VS Code) or simply `javac` / `java` from the terminal  

---

## ▶️ How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/employee-management.git
````

2. Compile the project:

   ```bash
   javac src/com/example/*.java
   ```
3. Run the program:

   ```bash
   java com.example.Main
   ```

---

## 📜 License

This project is licensed under the [MIT License](./LICENSE).

---

## ✨ Future Improvements

* Add persistence (save/load employees from a file or database).
* Implement unit tests for core functionality.
* Add support for departments and managers.
* Create a GUI version using JavaFX or Swing.

```