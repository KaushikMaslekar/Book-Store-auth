# Test Authentication and Book Creation Script

$baseUrl = "http://localhost:8080"

Write-Host "Testing BookStore Authentication..." -ForegroundColor Green

# Test 1: Register a new user
Write-Host "`n1. Registering a new user..." -ForegroundColor Yellow
$registerData = @{
    userName = "testuser"
    password = "testpass"
    roles = "ADMIN"
} | ConvertTo-Json

try {
    $registerResponse = Invoke-RestMethod -Uri "$baseUrl/user-info/register" -Method POST -Body $registerData -ContentType "application/json"
    Write-Host "Register Response: $registerResponse" -ForegroundColor Green
} catch {
    Write-Host "Register Error: $($_.Exception.Message)" -ForegroundColor Red
}

# Test 2: Login and get token
Write-Host "`n2. Logging in to get JWT token..." -ForegroundColor Yellow
$loginData = @{
    userName = "testuser"
    password = "testpass"
} | ConvertTo-Json

try {
    $token = Invoke-RestMethod -Uri "$baseUrl/user-info/login" -Method POST -Body $loginData -ContentType "application/json"
    Write-Host "JWT Token: $token" -ForegroundColor Green
    
    # Test 3: Create a book with the token
    Write-Host "`n3. Creating a book with JWT token..." -ForegroundColor Yellow
    $bookData = @{
        bookId = "B001"
        author = "Robert C. Martin"
        name = "Clean Code: A Handbook of Agile Software Craftsmanship"
        price = "450"
        description = "A guide to writing clean, maintainable, and efficient code with practical examples."
    } | ConvertTo-Json
    
    $headers = @{
        "Authorization" = "Bearer $token"
        "Content-Type" = "application/json"
    }
    
    $bookResponse = Invoke-RestMethod -Uri "$baseUrl/book-store/" -Method POST -Body $bookData -Headers $headers
    Write-Host "Book Created: $($bookResponse | ConvertTo-Json)" -ForegroundColor Green
    
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
    Write-Host "Response: $($_.Exception.Response)" -ForegroundColor Red
}

Write-Host "`nTest completed!" -ForegroundColor Cyan
