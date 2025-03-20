## Overview
The application consists of a TokenController for fetching authentication tokens and a 
TransportController for accessing transport data using these tokens. 
Additionally, a Java application, HttpTester, is utilized to test these functionalities externally.

## 1. TokenController
Endpoint: `POST /get-token`

Handles the retrieval of OAuth tokens using client credentials.

Headers: Content type set to `application/x-www-form-urlencoded`.
Body: Includes grant_type, client_id, client_secret, and scope.
Function: Sends a request to the authentication server specified by tokenUrl and returns the server's response containing the OAuth token.

## 2. TransportController
Endpoint: ` GET /transports `

Utilizes the retrieved OAuth token to access transport data from a secured endpoint.

Authorization Header: Expects an Authorization header containing the bearer token.
Function: Forwards the request to the data service URL specified by serviceUrl and returns the fetched data.

## Testing with HttpTester
A Java application designed to simulate client operations that interact with the TokenController and TransportController.

Operation: Makes a POST request to `/get-token` to retrieve an OAuth token, then uses this token in a GET request to `/transports`.
Output: Displays the OAuth token and transport data received from the server.


## Running the Application
To run and test the Spring Boot application:

### ➡️ Start the Spring Boot Application: 
Ensure the main application is running. It hosts the REST API that includes the TokenController and TransportController.

### ➡️ Run HttpTester: 
Execute HttpTester to test the end-to-end functionality. This tool makes HTTP requests to the Spring Boot application and displays responses in the console.
