# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).

## [2.2.0] - 2025-09-28
### Added
- **Generic Repository Pattern**:
    - `Repository<T, ID>` interface with type parameters for entity and identifier
    - `EmployeeRepository` implementation with Integer ID and Employee type
    - Generic CRUD operations: add, findById, findAll, remove, exists
- **Generic Utility Classes**:
    - `CollectionUtils` with bounded type parameters and wildcards
    - `EmployeeComparators` factory for type-safe sorting strategies
    - Generic methods: `findMax`, `swap`, `calculateTotalMonthlyCost`
- **Advanced Generic Features**:
    - Bounded type parameters: `<T extends Employee>`
    - Wildcard types: `<? extends Employee>` for read-only operations
    - Multiple type parameters: `<T, U>` in utility methods
    - Type-safe filtering with `Class<T>` parameter
- **New Menu Options**:
    - "Generic Features Demo": Comprehensive generics demonstration
    - "Advanced Employee Search": Type-safe employee filtering and sorting
- **Enhanced Architecture**:
    - Generic repository layer replacing simple ArrayList
    - Type-safe employee operations throughout the system
    - Compile-time type checking for all data operations

### Enhanced
- **Company Class**:
    - Integrated `EmployeeRepository` for all data operations
    - Added `demonstrateGenericFeatures()` method showcasing all generics concepts
    - Implemented `findEmployeesByType(Class<T>)` for type-safe filtering
    - Added `getEmployeesSortedBy()` with generic comparator support
- **Main Application**:
    - New `advancedEmployeeSearch()` method with type-safe operations
    - Enhanced menu system with generics demonstrations
    - Improved error handling with generic repository
- **Type Safety**: Compile-time type checking throughout the application

### Learning Features
- **Generic Types**: Practical implementation of type parameters
- **Bounded Type Parameters**: Restricting generics to specific hierarchies
- **Wildcards**: Understanding extends and super wildcards
- **Generic Methods**: Methods with their own type parameters
- **Type Erasure**: Runtime behavior of generic types
- **Generic Interfaces**: Designing flexible, reusable APIs

---

## [2.1.0] - 2025-09-26
### Added
- **Exception Handling System**: Comprehensive exception management throughout the application
- **Custom Exception Classes**:
    - `EmployeeNotFoundException`: Unchecked runtime exception for employee lookup failures
    - `ExportException`: Checked exception for file export operation failures
- **File Export Feature**: Export company reports to text files with proper resource management
- **Training Management System**:
    - Enhanced `Trainable` interface with default and static methods
    - Training session management for employees
    - Interactive training menu with available topics
- **New Menu Options**:
    - "Export Company Report": Generate and save detailed company reports
    - "Manage Employee Training": Organize training sessions for trainable employees

### Enhanced
- **Input Validation**: Improved `InputHelper` with better error handling and new `readYesNo` method
- **Company Class**:
    - Added `exportCompanyReport()` method with try-with-resources
    - Enhanced `getEmployee()` and `findHighestPaidEmployee()` with custom exceptions
    - Added input validation in constructor
- **Employee Classes**:
    - `InternEmployee` and `FullTimeEmployee` now implement `Trainable` interface
    - Overridden default methods for custom training completion messages
- **Main Application**:
    - Comprehensive try-catch blocks for graceful error handling
    - New `manageEmployeeTraining()` method for interactive training sessions
    - Better user experience with meaningful error messages

### Architecture
- **Package Structure**: Organized into `exception/`, `service/`, and `model/` packages
- **Resource Management**: Proper file handling with try-with-resources
- **Interface Features**: Demonstration of default and static methods in interfaces
- **Type Safety**: Instanceof checks and safe casting for interface implementations

### Learning Features
- **Exception Types**: Practical demonstration of checked vs unchecked exceptions
- **Custom Exceptions**: Domain-specific error handling
- **Interface Advanced Features**: Default methods, static methods, and method overriding
- **File I/O**: Safe file operations with exception handling
- **User Interaction**: Interactive menu systems with validation

---

## [2.0.0] - 2025-09-02
### Added
- Introduced **abstract `Employee` class** to enforce a clear contract for different employee types.
- Added **`FullTimeEmployee`** and **`PartTimeEmployee`** classes to demonstrate inheritance and polymorphism.
- Implemented **method overriding** for `calculateWage()` in subclasses.
- Added **interface `BenefitEligible`** to simulate multiple inheritance via interfaces.
- Implemented **upcasting and downcasting** examples when handling employees in the company list.
- Added **comparison of employees** by overriding `equals()` and `hashCode()`.
- Introduced **`final` fields/methods** to protect immutable logic where necessary.

### Changed
- Refactored the old `Employee` class into an **abstract base class** for better OOP design.
- Updated `Company` class to handle different employee types via **polymorphism**.
- Improved encapsulation with proper **access modifiers (private/protected/public)**.
- Updated `Main` interactive loop to support creating both full-time and part-time employees.

### Removed
- Direct instantiation of the old generic `Employee` class (now abstract).

---

## [1.0.0] - 2025-08-28
### Added
- Initial version of the Employee Management System.
- Features included: add employees, view list, calculate wage with overtime, find highest-paid employee, show statistics.
- Implemented input validation using `InputHelper`.
- Introduced static employee counter.  
