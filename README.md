## 📄 README.md

```markdown
# Employee Management System

A simple **Java console-based application** for managing employees in a company.  
This project demonstrates the use of **Object-Oriented Programming (OOP)** concepts such as encapsulation, abstraction, and static fields, along with user input validation.

---

## 🚀 Features
- Add new employees with name, base salary, and hourly rate
- View a list of all employees
- Calculate wages (with or without extra hours)
- Find the highest-paid employee
- Show company statistics (employee count, total employees created)
- Input validation to avoid invalid entries

---

## 🛠️ Technologies Used
- **Java SE 17+** (standard edition)
- **ArrayList** for dynamic employee storage
- **Scanner** for user input
- **OOP principles** (Encapsulation, Static Fields, Method Overloading)

---

## 📂 Project Structure
```

src/
└── com/
└── example/
├── InputHelper.java   # Handles user input and validation
├── Employee.java      # Represents an employee
├── Company.java       # Manages employee list and operations
└── Main.java          # Main program with interactive menu

````

---

## ▶️ How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/AmirReaper/Employee-Management.git
````

2. Navigate into the project folder:

   ```bash
   cd employee-management/src
   ```

3. Compile and run:

   ```bash
   javac com/example/*.java
   java com.example.Main
   ```

---

## 📸 Example Run

```
=== Employee Management System ===
Enter company name: Example Inc.

--- Menu ---
1. Add Employee
2. View All Employees
3. Calculate Wage with Extra Hours
4. Find Highest Paid Employee
5. Show Statistics
6. Exit
```
