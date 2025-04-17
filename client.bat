@echo off
echo Starting Chess Client...
echo.
echo Client is running. Press Ctrl+C to stop the client.
echo.
call mvnw exec:java -Dexec.mainClass="edu.uca.swe.GUI.MainGUI" -Dexec.cleanupDaemonThreads=false
pause