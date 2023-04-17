import re

# Otwórz plik z danymi wejściowymi
with open('plik.log', 'r') as file:
    input_text = file.read()

# Wykorzystaj wyrażenie regularne do znalezienia pasujących fragmentów
matches = re.findall('- Session Metrics {(.*?)}', input_text, re.DOTALL)

# Otwórz plik wyjściowy w trybie zapisu
with open('output.txt', 'w') as file:
    # Dla każdego pasującego fragmentu zapisz go do pliku wyjściowego
    for match in matches:
        file.write(match + '\n\n')
