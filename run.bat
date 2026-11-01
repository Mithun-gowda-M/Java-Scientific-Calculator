@echo off
setlocal
cd /d "%~dp0"
echo ===================================================
echo   Compiling and Launching Java Scientific Calculator
echo ===================================================

if not exist bin (
    mkdir bin
)

javac -d bin src/calculator/*.java src/calculator/advanced/*.java
if %errorlevel% neq 0 (
    echo.
    echo [ERROR] Compilation failed. Please ensure JDK (javac/java) is on your PATH.
    pause
    exit /b %errorlevel%
)

echo [SUCCESS] Compilation complete. Starting calculator...
echo.
java -cp bin calculator.Calc

pause

