public class Ulamek<T extends Number>
{
    private int licznik;
    private int mianownik;

    public Ulamek(T licznik, T mianownik)
    {
        if (mianownik.intValue() == 0)
        {
            throw new IllegalArgumentException("Mianownik nie może być równy zero!");
        }
        this.licznik = licznik.intValue();
        this.mianownik = mianownik.intValue();
    }

    public Ulamek(T liczba)
    {
        this(liczba, (T)Integer.valueOf(1));
    }

    private int nwd(int a, int b)
    {
        return b == 0 ? a : nwd(b, a % b);
    }

    private int nww(int a, int b)
    {
        return (a * b) / nwd(a, b);
    }

    @Override
    public String toString()
    {
        return licznik + "/" + mianownik;
    }

    public Ulamek dodaj(Ulamek ulamek)
    {
        int nowyMianownik = nww(this.mianownik, ulamek.mianownik);
        int licznik1 = this.licznik * (nowyMianownik / this.mianownik);
        int licznik2 = ulamek.licznik * (nowyMianownik / ulamek.mianownik);
        int nowyLicznik = licznik1 + licznik2;

        return new Ulamek(nowyLicznik, nowyMianownik);
    }

    public Ulamek odejmij(Ulamek ulamek)
    {
        int nowyMianownik = nww(this.mianownik, ulamek.mianownik);
        int licznik1 = this.licznik * (nowyMianownik / this.mianownik);
        int licznik2 = ulamek.licznik * (nowyMianownik / ulamek.mianownik);
        int nowyLicznik = licznik1 - licznik2;
        return new Ulamek(nowyLicznik, nowyMianownik);
    }

    public Ulamek pomnoz(Ulamek ulamek)
    {
        int nowyLicznik = this.licznik * ulamek.licznik;
        int nowyMianownik = this.mianownik * ulamek.mianownik;
        return new Ulamek(nowyLicznik, nowyMianownik);
    }

    public Ulamek podziel(Ulamek ulamek)
    {
        if (ulamek.licznik == 0)
        {
            throw new IllegalArgumentException("Nie można dzielić przez zero!");
        }
        int nowyLicznik = this.licznik * ulamek.mianownik;
        int nowyMianownik = this.mianownik * ulamek.licznik;
        return new Ulamek(nowyLicznik, nowyMianownik);
    }

    public void skroc()
    {
        int dzielnik = nwd(this.licznik, this.mianownik);
        this.licznik /= dzielnik;
        this.mianownik /= dzielnik;
    }
}