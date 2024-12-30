@echo off
setlocal

REM Remover o diretório bin se já existir
rmdir /s /q bin

REM Compilar os arquivos Java
javac -d bin "src\com\ufg\projeto\modelos\*.java"
javac -d bin "src\com\ufg\projeto\dao\*.java"
javac -d bin "src\com\ufg\projeto\gerenciador\*.java"
javac -d bin "src\com\ufg\projeto\Main.java"

echo Compilação concluída!

pause
