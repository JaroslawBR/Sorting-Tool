## PL: Sorting-Tool  to program napisany w języku Kotlin, który umożliwia sortowanie danych wprowadzonych przez użytkownika. Program obsługuje różne typy danych, takie jak liczby całkowite, słowa i linie tekstu. Użytkownik może określić sposób sortowania (naturalne lub według liczby wystąpień) oraz typ danych do sortowania.

### Instrukcje użycia
### Program można uruchomić z linii poleceń, przekazując odpowiednie argumenty. Poniżej przedstawiono dostępne argumenty:

**`-sortingType:`** Określa sposób sortowania. Możliwe wartości to `natural` (sortowanie naturalne) lub `byCount` (sortowanie według liczby wystąpień).

**`-dataType:`** Określa typ danych do sortowania. Możliwe wartości to `long` (liczby całkowite), `line` (linie tekstu) lub `word` (słowa).

**`-inputFile:`** Określa nazwę pliku wejściowego zawierającego dane do sortowania.

**`-outputFile:`** Określa nazwę pliku wyjściowego, do którego zostaną zapisane posortowane dane.

#### Przykład użycia programu:
```shell
java SortingTool  -sortingType natural -dataType word -inputFile input.txt -outputFile output.txt
```
Wprzpadku nie zastowania lini polecenia prgram ustawi `-sortingType natural`, `-dataType long` a liczby do sorotwoani użtkownik bedzie musiał popdaćw konsoli. W celu zakończyć wprowadzanie danych, użytkownik powinien wpisać symbol końca pliku `Ctrl+D` lub `Cmd+D` albo `Ctrl+Z`.


## ENG: Sorting-Tool is a program written in Kotlin that allows sorting of user-entered data. The program supports various data types, such as integers, words, and text lines. The user can specify the sorting method (natural or by count) and the data type to be sorted.

### Usage Instructions

The program can be run from the command line by passing the appropriate arguments. Below are the available arguments:

- `-sortingType`: Specifies the sorting method. Possible values are `natural` (natural sorting) or `byCount` (sorting by count).

- `-dataType`: Specifies the data type to be sorted. Possible values are `long` (integers), `line` (text lines), or `word` (words).

- `-inputFile`: Specifies the input file name containing the data to be sorted.

- `-outputFile`: Specifies the output file name to which the sorted data will be saved.

### Example Usage:

```shell
java SortingTool -sortingType natural -dataType word -inputFile input.txt -outputFile output.txt
```

If no command line arguments are provided, the program will default to `-sortingType natural`, `-dataType long`, and prompt the user to enter numbers in the console. To finish entering data, the user should input the end-of-file symbol `Ctrl+D` or `Cmd+D` or `Ctrl+Z`.
