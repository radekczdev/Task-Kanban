call ./runcrud.bat
if "%ERRORLEVEL%" == "0" goto runbrowser
echo.
echo Error while running runcrud.bat
goto end

:runbrowser
start chrome "http://localhost:8080/crud/v1/task/getTasks"

:end
echo.
echo Work is finished