
# Magento E-commerce Test Automation Project

https://magento.softwaretestingboard.com

Welcome to the **Magento E-commerce Test Automation** repository. This project automates various functional tests for the Magento online store, leveraging the latest testing frameworks and best practices. The aim is to ensure quality and reliability in e-commerce workflows through automated testing solutions.

## Table of Contents

1. [Key Tools & Technologies](#Key-Tools-&-Technologies)
2. [Requirements](#Requirements)
3. [Project Overview](#Project-Overview)
4. [Setup Instructions](#Setup-Instructions)
5. [Project Structure](#Project-Structure)
6. [Executing Tests](#Executing-Tests)
7. [Viewing Reports](#Viewing-Reports)
8. [Conclusion](#Conclusion)
9. [Contact Information](#Contact-Information)

## Key Tools & Technologies
<a href="https://selenium.dev"><img src="https://selenium.dev/images/selenium_logo_square_green.png" width="40" height="40" alt="Selenium"/></a>
<a href="https://testng.org/"><img src="https://github.com/user-attachments/assets/a7f77c73-ceb2-4936-bb68-7696b07dc092" width="40" height="40" alt="TestNG"/></a>
<a href="https://cucumber.io/"><img src="https://cucumber.io/img/cucumber-logo.png" width="40" height="40" alt="Cucumber"/></a>
<a href="https://allure.dev/"><img src="https://avatars.githubusercontent.com/u/5879127?s=200&v=4" width="40" height="40" alt="Allure Reports"/></a>

This automation project makes use of the following technologies:

- **Java**: The programming language for developing test scripts.
- **Selenium WebDriver (v4)**: For simulating browser actions and automating web interaction.
- **TestNG**: Organizes test cases and handles test execution.
- **Cucumber**: Implements behavior-driven testing with human-readable test scenarios.
- **Allure Reports**: Produces comprehensive and visually appealing reports.
- **Maven**: Handles project dependencies and builds.

## Requirements

Ensure the following are installed on your system:

- **JDK (version 8+)**
- **Maven** for managing dependencies.
- An IDE like **IntelliJ IDEA** or **Eclipse** with the TestNG and Maven plugins installed.
- A browser driver for Selenium (e.g., ChromeDriver, GeckoDriver).
- Allure for generating detailed test reports.

## Project Overview

This project is focused on automating key functional test scenarios for the Magento e-commerce platform. It includes browser-based interactions, user flows, and key feature verifications. Using **Selenium WebDriver** for browser automation, **TestNG** for structured testing, and **Cucumber** for BDD-style test writing, this project enables scalable and maintainable test development. **Allure Reports** ensures test results are presented in a user-friendly format.

## Setup Instructions

To set up the project locally, follow these steps:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Moaz-MM/Web-TestAutomation.git
   ```

2. **Open the project in your IDE** and make sure Maven is configured to handle project dependencies.

3. **Install dependencies**:
   Navigate to the project root and run:
   ```bash
   mvn clean install
   ```

## Project Structure

The project is organized into the following structure:

- `src/main/java`: Contains page classes and methods.
- `src/test/java`: Contains test scripts for automating various website features.

## Executing Tests

To run the automated tests:

**Run all tests**:
```bash
mvn test
```
   
## Viewing Reports

After test execution, you can generate and view the Allure report:

Generate and serve the Allure report locally:
```bash
mvn allure:serve
```

## Conclusion

This README provides detailed guidance on setting up and running automated tests for the Magento website. By following the instructions, you can easily execute the automation tests and generate comprehensive reports for different test cases.

Feel free to contribute, explore, and expand this project to cover more testing scenarios.

## Contact Information

If you have any questions or need further assistance, please contact me at:

**Email**: [moazmetawea@gmail.com](mailto:moazmetawea@gmail.com)
