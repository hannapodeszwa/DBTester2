import re
import csv
import sys

if len(sys.argv) > 1:
    nazwa_pliku_wyjsciowego = sys.argv[1]
else:
    nazwa_pliku_wyjsciowego = "default.csv"

# otwarcie pliku z logami Hibernate
with open('logs/hibernate-info.log', 'r') as f:
    # wczytanie całego pliku
    content = f.read()

# wyodrębnienie fragmentów między "- Session Metrics {" a "}"
fragments = re.findall('- Session Metrics {(.*?)}', content, re.DOTALL)

# otwarcie pliku CSV do zapisu
with open(nazwa_pliku_wyjsciowego, 'w', newline='') as csvfile:
    writer = csv.writer(csvfile)

    # zapisanie nagłówka
    writer.writerow(['Acquiring JDBC connections', 'Releasing JDBC connections', 'Preparing JDBC statements', 'Executing JDBC statements', 'Executing JDBC batches', 'Performing L2C puts', 'Performing L2C hits', 'Performing L2C misses', 'Executing flushes', 'Executing partial-flushes'])
    
    
    # zapisanie każdego fragmentu w osobnej linii
    for fragment in fragments:
        values = re.findall('(\d+)\s+nanoseconds spent', fragment)
        writer.writerow(values[:10])        
        
        
