# Test Script for Bookstore API
Write-Host "Testing Bookstore API Endpoints" -ForegroundColor Green

# Wait for application to start
Write-Host "Waiting for application to start..." -ForegroundColor Yellow
Start-Sleep -Seconds 5

try {
    # Test 1: Welcome endpoint
    Write-Host "Test 1: Welcome endpoint" -ForegroundColor Cyan
    $welcome = Invoke-RestMethod -Uri "http://localhost:8080/book-store/welcome" -Method Get
    Write-Host "Welcome Response: $welcome" -ForegroundColor Green

    # Test 2: Register a user
    Write-Host "Test 2: Register admin user" -ForegroundColor Cyan
    $registerBody = @{
        userName = "admin"
        password = "admin123"
        roles = "ADMIN"
    } | ConvertTo-Json
    
    $registerResponse = Invoke-RestMethod -Uri "http://localhost:8080/user-info/register" -Method Post -Body $registerBody -ContentType "application/json"
    Write-Host "Register Response: $registerResponse" -ForegroundColor Green

    # Test 3: Login to get JWT token
    Write-Host "Test 3: Login to get JWT token" -ForegroundColor Cyan
    $loginBody = @{
        userName = "admin"
        password = "admin123"
    } | ConvertTo-Json
    
    $token = Invoke-RestMethod -Uri "http://localhost:8080/user-info/login" -Method Post -Body $loginBody -ContentType "application/json"
    Write-Host "JWT Token received: $($token.Substring(0, 50))..." -ForegroundColor Green

    # Test 4: Access protected endpoint with token
    Write-Host "Test 4: Access protected books endpoint" -ForegroundColor Cyan
    $headers = @{
        "Authorization" = "Bearer $token"
    }
    $books = Invoke-RestMethod -Uri "http://localhost:8080/book-store/" -Method Get -Headers $headers
    Write-Host "Books Response: $books" -ForegroundColor Green

    Write-Host "All tests passed! Your application is working perfectly!" -ForegroundColor Green
    Write-Host "Application is running on: http://localhost:8080" -ForegroundColor Yellow
    Write-Host "Available endpoints:" -ForegroundColor Yellow
    Write-Host "   - GET  /book-store/welcome (public)" -ForegroundColor White
    Write-Host "   - POST /user-info/register (public)" -ForegroundColor White
    Write-Host "   - POST /user-info/login (public)" -ForegroundColor White
    Write-Host "   - GET  /book-store/ (ADMIN role required)" -ForegroundColor White
    Write-Host "   - GET  /book-store/{id} (USER role required)" -ForegroundColor White

} catch {
    Write-Host "Error testing API: $($_.Exception.Message)" -ForegroundColor Red
    Write-Host "Make sure the application is running on port 8080" -ForegroundColor Yellow
}
