# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).

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
