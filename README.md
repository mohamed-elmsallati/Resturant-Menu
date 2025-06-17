# Restaurant Menu Java Application

Welcome! This project is a simple Java application for managing a restaurant menu. It is designed to be developed and run in Visual Studio Code.

## Project Structure

- `src/` – Java source code
- `lib/` – External dependencies (JAR files)
- `bin/` – Compiled output (generated automatically)

You can customize the folder structure in `.vscode/settings.json` if needed.

## Getting Started

1. **Open the project in VS Code.**
2. **Install the Java Extension Pack** (if not already installed).
3. **Build and run the application** using the built-in VS Code commands.

## Dependency Management

Manage your dependencies using the `JAVA PROJECTS` view in VS Code.
For more information, see the [VS Code Java Dependency Guide](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

---

## UML

![UML Diagram](./uml.png)

- **RestaurantSystem**: The core class managing orders, inventories, and the menu.
- **Order**: Represents a customer order, containing multiple menu items.
- **Menu**: Holds a list of available menu items.
- **MenuItem**: Represents an individual item on the menu, with details like name, price, category, and quantity.
- **Inventory**: Manages stock levels for ingredients or items, supporting weekly, monthly, and yearly tracking.
- **Category**: Groups menu items by type (e.g., appetizers, main courses).
