# Clustered Data Warehouse for FX Deals

This project is a Java-based application designed to process and store foreign exchange (FX) deal data for a data warehouse. Developed as part of a technical assignment, it ensures robust data validation, unique record handling, and seamless database integration while adhering to modern development best practices.

## API Endpoint

- **Endpoint**:
   **[POST]** `http://localhost:8001/api/api/deal`
   **[GET]** `http://localhost:8001/api/api/dea/{id}`
   **[GET]** `http://localhost:8001/api/api/deals`

- **Request JSON Example**:

  ```json
  {
      "fromCurrency": "USD",
      "toCurrency": "EUR",
      "dealTimestamp": "2024-01-19T14:30:00",
      "dealAmount": 1000.50
  }


## Features

- **Database Support**: Utilized MySQL for data persistence.
- **Deployment Ready**: Configured with Docker Compose for simplified deployment and execution.
- **Error and Exception Handling**: Implemented comprehensive and professional error/exception management.
- **Validation**: Ensures input data integrity through structured validation mechanisms.
- **Duplicate Handling**: Prevents re-import of duplicate records into the database.
- **Logging**: Integrated detailed and reliable logging for monitoring and debugging.
- **Unit Testing**: Includes extensive unit testing with respectable code coverage to ensure quality and reliability.

## Deliverables

1. **Database Integration**: MySQL is used for data storage.
2. **Deployment**: Docker Compose configuration provided for quick setup.
3. **Project Structure**: Built using Maven for dependency management and project structuring.
4. **Documentation**: Comprehensive documentation provided in this README.md file.
5. **Code Quality**: Adheres to clean code principles with robust validation, logging, and exception handling.
6. **Testing**: Extensive unit tests included for all major functionalities.

## Usage

### Prerequisites
- Docker & Docker Compose installed.
- Java Development Kit (JDK 11 or later).
- Maven for building the project.

### Setup and Execution
1. Clone the repository:
   ```bash
   git clone https://github.com/mnachit/fx-deals-warehouse.git
   cd fx-deals-warehouse
