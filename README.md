# JWT Token Generation and Validation with Swagger

## Requirements
- JDK 17+
- Maven 3.8+
- Internet connection for Maven to download dependencies

## Run the project
1. Extract the ZIP.
2. Open the `jwt-swagger-lab` folder in IntelliJ IDEA / Eclipse / VS Code.
3. Run `JwtSwaggerLabApplication.java`, or open a terminal in the project folder and run:
   `mvn spring-boot:run`
4. Open Swagger UI: http://localhost:8080/swagger-ui/index.html

## Lab exam: generate and validate JWT using Swagger

### A. Generate a token
1. In Swagger, expand **POST /api/auth/token**.
2. Click **Try it out**.
3. Enter this sample JSON:
   ```json
   {
     "username": "akhil",
     "password": "12345"
   }
   ```
4. Click **Execute**.
5. Copy the `token` value from the response. Do not copy the quotation marks.

### B. Validate a token in Swagger's Authorize button
1. Click **Authorize** (lock icon) near the top of Swagger UI.
2. In the `bearerAuth` value field, paste the token only. Do not type `Bearer `; Swagger adds it automatically.
3. Click **Authorize**, then **Close**.
4. Expand **GET /api/hello**.
5. Click **Try it out**, then **Execute**.
6. A successful response is HTTP **200** and looks like:
   ```json
   {
     "message": "JWT validation successful!",
     "authenticatedUser": "akhil"
   }
   ```

### C. Test an invalid/missing token
1. Click **Authorize** and clear the token (or paste a deliberately changed token), then click **Authorize** / **Close**.
2. Execute **GET /api/hello** again.
3. Without a valid token, the endpoint should return **401 Unauthorized**.

### D. Optional: validate directly through the validation endpoint
1. Expand **POST /api/auth/validate**.
2. Click **Try it out**.
3. Send:
   ```json
   {
     "token": "PASTE_YOUR_TOKEN_HERE"
   }
   ```
4. Click **Execute**. A valid token returns `{"valid": true}`; an invalid or expired token returns `{"valid": false}`.

## Important lab notes
- This is a learning/demo project. The token endpoint accepts any non-empty username and password; it does **not** authenticate against a database.
- In a real application, verify the user's password securely before generating a token, keep the signing secret outside source control, and use HTTPS.
- Token expiry is set to 1 hour in `application.properties`.
