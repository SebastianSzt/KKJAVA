public class Main {
    public static void main(String[] args)
    {
        Ulamek<Integer> ulamek1 = new Ulamek<>(3, 4);
        Ulamek<Double> ulamek2 = new Ulamek<>(2.5, 3.75);

        Ulamek<Integer> suma = ulamek1.dodaj(ulamek2);
        Ulamek<Integer> roznica = ulamek1.odejmij(ulamek2);
        Ulamek<Integer> iloczyn = ulamek1.pomnoz(ulamek2);
        Ulamek<Integer> iloraz = ulamek1.podziel(ulamek2);

        System.out.println(ulamek1 + " + " + ulamek2 + " = " + suma);
        System.out.println(ulamek1 + " - " + ulamek2 + " = " + roznica);
        System.out.println(ulamek1 + " * " + ulamek2 + " = " + iloczyn);
        System.out.println(ulamek1 + " / " + ulamek2 + " = " + iloraz);

        Ulamek<Integer> ulamek3 = new Ulamek<>(8, 36);
        System.out.print("Ułamek " + ulamek3 + " skrócony to: ");
        ulamek3.skroc();
        System.out.println(ulamek3);

        Complex<Double> liczbaZespolona = new Complex<>(2.5, 3.5);
        UlamekZespolony ulamekZespolony = new UlamekZespolony(liczbaZespolona);
        System.out.println("Ułamek zespolony: " + ulamekZespolony);
    }
}