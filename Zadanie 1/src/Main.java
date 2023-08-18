import java.io.*;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        System.out.print("Czy chcesz wpisać nazwę pliku? (tak/nie): ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String answer = reader.readLine();
        String fileName;
        if (answer.equals("tak"))
        {
            System.out.print("Wprowadź nazwę pliku: ");
            fileName = reader.readLine();
        }
        else
        {
            fileName = "wizytowki.csv";
        }
        fileName = "Zadanie 1/src/" + fileName;

        Scanner scanner = new Scanner(System.in);
        boolean end = false;

        while(!end)
        {
            System.out.println("\n1. Wyświetl wszystkie wizytówki");
            System.out.println("2. Dodaj nową wizytówkę");
            System.out.println("3. Wyświetl wizytówki dla osób o określonym nazwisku");
            System.out.println("4. Zakończ program");
            System.out.print("Co chcesz zrobić? (1/2/3/4): ");

            int select = scanner.nextInt();
            scanner.nextLine();

            switch (select) {
                case 1:
                    String result = readFile(fileName);

                    if (result.isEmpty())
                        break;

                    String[] lines = result.split("\r\n");

                    for (String line : lines)
                    {
                        String[] fields = line.split(";");
                        System.out.println();
                        System.out.println("Imię: " + fields[0]);
                        System.out.println("Nazwisko: " + fields[1]);
                        System.out.println("Telefon: " + fields[2]);
                        System.out.println("Email: " + fields[3]);
                    }
                    break;
                case 2:
                    System.out.println("\nDodawanie nowej wizytówki:");

                    System.out.print("Podaj imię: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Podaj nazwisko: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Podaj numer telefonu: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Podaj adres e-mail: ");
                    String email = scanner.nextLine();

                    String newLine = firstName + ";" + lastName + ";" + phoneNumber + ";" + email + "\n";
                    writeFile(fileName, newLine);

                    System.out.println("\nPomyślnie dodano nową wizytówkę!");
                    break;
                case 3:
                    String search = readFile(fileName);
                    if (search.isEmpty())
                        break;

                    System.out.print("\nPodaj nazwisko do wyszukiwania: ");
                    String check = scanner.nextLine();

                    int i = 0;

                    String[] Lines = search.split("\r\n");

                    for (String Line : Lines)
                    {
                        String[] fields = Line.split(";");
                        if (fields[1].equals(check))
                        {
                            System.out.println("\nImię: " + fields[0]);
                            System.out.println("Nazwisko: " + fields[1]);
                            System.out.println("Telefon: " + fields[2]);
                            System.out.println("Email: " + fields[3]);
                            i++;
                        }
                    }

                    if (i == 0)
                    {
                        System.out.println("\nBrak wyników, nie znaleziono wizytówek z takim nazwiskiem.");
                    }
                    break;
                case 4:
                    end = true;
                    System.out.println("\nKoniec programu.");
                    break;
                default:
                    System.out.println("\nNieprawidłowy wybór.");
            }
        }
    }

    public static String readFile(String fileName)
    {
        String result = "";
        try
        {
            File myFile = new File(fileName);
            Scanner myScanner = new Scanner(myFile);
            while ( myScanner.hasNextLine() )
            {
                result += (myScanner.nextLine() + "\r\n");
            }
            myScanner.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("\nOoops... Plik jeszcze nie istnieje. ");
            //e.printStackTrace();
        }
        return result;
    }

    public static void writeFile(String fileName, String line)
    {
        try
        {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(line);
            fw.flush();
            fw.close();
        }
        catch (IOException e)
        {
            System.out.println("Ooops... Wystąpił błąd: ");
            e.printStackTrace();
        }
    }
}