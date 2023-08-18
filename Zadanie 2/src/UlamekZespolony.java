public class UlamekZespolony<T extends Number>
{
    private Ulamek<T> czescRzeczywista;
    private Ulamek<T> czescUrojona;

    public UlamekZespolony(Complex<T> complex)
    {
        T re = complex.getRe();
        T im = complex.getIm();
        int mianownik = 1000000;

        int licznikRe = (int) (re.doubleValue() * mianownik);
        int licznikIm = (int) (im.doubleValue() * mianownik);

        int nwdRe = nwd(licznikRe, mianownik);
        int nwdIm = nwd(licznikIm, mianownik);

        this.czescRzeczywista = new Ulamek<>((T) (Object) (licznikRe / nwdRe), (T) (Object) (mianownik / nwdRe));
        this.czescUrojona = new Ulamek<>((T) (Object) (licznikIm / nwdIm), (T) (Object) (mianownik / nwdIm));
    }

    private int nwd(int a, int b)
    {
        return b == 0 ? a : nwd(b, a % b);
    }

    @Override
    public String toString()
    {
        return czescRzeczywista.toString() + " + " + czescUrojona.toString() + "i";
    }
}