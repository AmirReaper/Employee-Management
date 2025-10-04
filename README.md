# 👥 Employee Management System v2.1.0 – Exceptions

This release introduces **comprehensive exception handling** to the project.

## ✨ New Features
- All OOP features from v2.0.0
- **Custom Exception Classes**:
    - `EmployeeNotFoundException` (unchecked, runtime)
    - `ExportException` (checked, for file operations)
- **File Export System**: Export company reports to text files
- **Enhanced Input Validation**: Better error handling in user interactions
- **Try-Catch Blocks**: Proper exception handling throughout the application

## 🛡️ Exception Handling Features
- **Custom Exceptions**: Domain-specific error types for better error management
- **Checked vs Unchecked**: Practical demonstration of both exception types
- **Resource Safety**: Try-with-resources in file export operations
- **User-Friendly Errors**: Clear error messages for end users
- **Graceful Recovery**: Continue operation after handling exceptions

## 📚 Learning Goals
- Understand the difference between checked and unchecked exceptions
- Implement custom exception classes for specific domain errors
- Practice try-catch blocks and exception propagation
- Learn file I/O operations with proper exception handling
- Master defensive programming techniques

---

## 🏗️ Project Structure (v2.1.0)

```

src/com/example/
├── exception/           # NEW - Custom exceptions
│   ├── EmployeeNotFoundException.java
│   └── ExportException.java
├── service/            # NEW - Business services
│   ├── EmployeeReportExporter.java
│   └── Exportable.java
├── model/              # Enhanced models
│   ├── Employee.java
│   ├── FullTimeEmployee.java
│   ├── PartTimeEmployee.java
│   ├── InternEmployee.java
│   └── ReportableEmployee.java
├── Company.java        # Updated with exception handling
├── Main.java           # Updated with try-catch blocks
├── InputHelper.java    # Enhanced input validation
└── HRPolicy.java

````

For detailed changes, see the [CHANGELOG.md](./CHANGELOG.md).

---

# 🆕 New Menu Options

- Export Company Report: Generates and saves a detailed company report to file
- Enhanced Error Handling: Better user experience with meaningful error messages

## 🛠️ Requirements

- Java 17 or higher  
- IDE (IntelliJ IDEA, Eclipse, or VS Code) or simply `javac` / `java` from the terminal  

---

## 🚀 How to Run
```bash
# Compile
javac -d out src/com/example/*.java src/com/example/exception/*.java src/com/example/service/*.java src/com/example/model/*.java

# Run
java -cp out com.example.Main

---

## 📜 License

This project is licensed under the [MIT License](./LICENSE).

---

## 👨‍💻 Author

**Amir Reza Tabrizi**

* [GitHub Profile](https://github.com/AmirReaper)

⭐ If you found this project useful, please give it a star!

```