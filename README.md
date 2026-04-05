# 📊 Report Generator Refactor (Java)

## 📌 Overview

This project consists of the refactoring and enhancement of a legacy report generator implemented in Java.

The original implementation followed a **procedural approach**, with little to no object-oriented design, making the code difficult to maintain, extend, and understand. The main goal of this project was to transform that implementation into a **clean, modular, and extensible object-oriented system**, while preserving compatibility with existing constraints.

> ⚠️ **Note:** Although the codebase (class names, variables, methods) is written in **Portuguese**, this documentation is provided in **English** for clarity and accessibility.

---

## 🎯 Objectives

- Refactor a legacy, non-OOP implementation into a structured OOP design  
- Improve readability, maintainability, and scalability  
- Introduce design patterns and better separation of responsibilities  
- Preserve compatibility with pre-existing components that could not be modified  

---

## 📂 Project Structure
/depreciado/
    ClasseAntigaSemStrategy.java   # Legacy implementation (non-OOP)

/saidas/
    saida<number>.html     # Program outputs

/src/
    GeradorDeRelatorios.java       # Refactored entry point
    ...                            # Additional classes created during refactor

Produto.java                       # Pre-existing interface (unchanged)
ProdutoPadrao.java                # Pre-existing class (unchanged)

## 🧱 Legacy Implementation

The original version of the report generator can be found at:
/depreciado/ClasseAntigaSemStrategy.java


### Characteristics:

- Procedural structure  
- High coupling  
- Low cohesion  
- Difficult to extend (e.g., adding new report formats)  
- Logic concentrated in a single class  
- No use of design patterns  

This file was intentionally preserved to allow **direct comparison** with the refactored version.

---

## 🔄 Refactored Implementation

The new implementation starts at:

GeradorDeRelatorios.java 

Unlike the legacy version, this class is no longer monolithic. It has been **decomposed into multiple classes**, each with a clear responsibility.

### Improvements:

- ✅ Applied **Object-Oriented Principles**:
  - Encapsulation  
  - Abstraction  
  - Separation of concerns  

- ✅ Introduced **modular architecture**  
- ✅ Reduced code duplication  
- ✅ Improved naming and readability  
- ✅ Easier to extend and maintain  

---

## 🧩 Design Constraints

Two existing components from the original system were **not allowed to be modified**:

- `Produto.java` (interface)  
- `ProdutoPadrao.java` (default implementation)  

These were preserved as-is and integrated into the new architecture without changes.

---

## 🧠 Design Decisions

To improve flexibility and extensibility, the refactored version introduces:

- Separation between **data processing** and **report generation**  
- Extensible design to support future report formats  
- Decoupling of responsibilities across classes  

---

## ✨ Key Benefits

- 📈 Improved maintainability  
- 🔧 Easier to add new features (e.g., new report formats)  
- 🧪 Better testability due to modular design  
- 📚 Clearer code organization  
- 🔍 Easier comparison with legacy implementation  

---

## 🚀 Purpose of This Project

This repository demonstrates how a poorly structured legacy system can be transformed into a **well-designed object-oriented application**, even under constraints such as immutable components.

It serves as a practical example of:

- Refactoring techniques  
- Applying OOP principles in real scenarios  
- Improving legacy codebases without breaking existing contracts  

---

## 📎 Notes

- Code is written in **Portuguese**  
- Documentation is written in **English**  
- Legacy and refactored versions coexist for educational and comparison purposes  