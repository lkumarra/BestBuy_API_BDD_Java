# BestBuy API BDD Java

## Overview

This repository provides a Behavior-Driven Development (BDD) framework for testing the BestBuy API using Java. The framework integrates BDD principles with API testing to create a structured and readable way of verifying API functionality.

## Features

- **BDD Style Testing**: Write API tests in a natural language style using Cucumber and Java.
- **API Testing**: Perform end-to-end testing of the BestBuy API endpoints.
- **Customizable**: Easily extend and configure the framework for different API endpoints and scenarios.
- **Detailed Reports**: Generate comprehensive test reports to analyze the test results.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven (for dependency management)
- Cucumber for Java
- REST-assured (for API testing)

## Getting Started

### Installation

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/lkumarra/BestBuy_API_BDD_Java.git
    cd BestBuy_API_BDD_Java
    ```

2. **Install Dependencies:**

    ```bash
    mvn install
    ```

### Configuration

1. **Configure API Endpoints:**

   Update the API endpoint configurations in `src/test/resources/api_config.properties` to match your BestBuy API settings.

2. **Configure Cucumber:**

   Edit `src/test/resources/cucumber.properties` to set up Cucumber options and hooks.

### Running Tests

Run the following command to execute your BDD tests:

```bash
mvn test
