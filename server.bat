@echo off
echo Building project...
echo.
call mvnw clean install
echo.
echo Starting Chess Server...
echo.
echo Server is running. Press Ctrl+C to stop the server.
echo.
call mvnw exec:java -Dexec.mainClass="edu.uca.swe.Game.Connection.Server" -Dexec.cleanupDaemonThreads=false
pause