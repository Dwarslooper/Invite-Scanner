@echo off

echo.
echo.
echo     Discord Invite scanner by Dwarslooper V 0.1
echo.
echo     ======================================
echo     REMEMBER: This program consistently
echo     pings Discord's website to scan for
echo     invites. I am not responsible for any
echo     damage that might be cause by this.
echo     ======================================
echo.
echo.
set /p delay=Delay for Scanning invites (in milliseconds): 
set /p maxScans=Max amount of URLs that should be scanned: 
set /p openOnSuccess=Open invite in browser on success (true, false): 
echo.
echo.
echo     Press any key to start!
echo     To exit, press Ctrl + C or just close the program
pause>nul
echo.
echo.
java -jar scanner.jar %delay% %maxScans% %openOnSuccess%
pause