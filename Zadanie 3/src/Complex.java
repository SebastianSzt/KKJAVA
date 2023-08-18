public class Complex<T extends Number> extends Number
{
    private T real;
    private T imaginary;

    public Complex()
    {
        this.real = (T) Integer.valueOf(0);
        this.imaginary = (T) Integer.valueOf(0);
    }

    public Complex(T real, T imaginary)
    {
        this.real = real;
        this.imaginary = imaginary;
    }

    // SETTERY I GETTERY
    public void setImaginary(T imaginary)
    {
        this.imaginary = imaginary;
    }

    public Number getImaginary()
    {
        return imaginary;
    }

    public void setReal(T real)
    {
        this.real = real;
    }

    public Number getReal()
    {
        return real;
    }

    // Nadpisane METODY z klasy bazowej
    @Override
    public int intValue()
    {
        return this.real.intValue();
    }

    @Override
    public long longValue()
    {
        return this.real.longValue();
    }

    @Override
    public float floatValue()
    {
        return this.real.floatValue();
    }

    @Override
    public double doubleValue()
    {
        return this.real.doubleValue();
    }

    public String toString()
    {
        return this.real.toString() + "+" + this.imaginary.toString() + "i";
    }

    private T convertToT(double value)
    {
        if (real instanceof Double)
        {
            return (T) Double.valueOf(value);
        }
        else if (real instanceof Float)
        {
            return (T) Float.valueOf((float) value);
        }
        else if (real instanceof Long)
        {
            return (T) Long.valueOf((long) value);
        }
        else
        {
            return (T) Integer.valueOf((int) value);
        }
    }

    public T module()
    {
        double re = this.real.doubleValue();
        double im = this.imaginary.doubleValue();
        double wynik = Math.sqrt(re * re + im * im);

        return convertToT(wynik);
    }

    public Complex<T> add(Complex<?> c)
    {
        T reSuma = convertToT(this.real.doubleValue() + c.real.doubleValue());
        T imSuma = convertToT(this.imaginary.doubleValue() + c.imaginary.doubleValue());
        return new Complex<>(reSuma, imSuma);
    }

    public Complex<T> multiply(Complex<?> c)
    {
        T reWynik = convertToT(this.real.doubleValue() * c.getReal().doubleValue() - this.imaginary.doubleValue() * c.getImaginary().doubleValue());
        T imWynik = convertToT(this.real.doubleValue() * c.getImaginary().doubleValue() + this.imaginary.doubleValue() * c.getReal().doubleValue());
        return new Complex<>(reWynik, imWynik);
    }

    public <Type extends Number> void add(Type val)
    {
        real = convertToT(real.doubleValue() + val.doubleValue());
    }

    public <Type extends Number> void multiply(Type val)
    {
        real = convertToT(real.doubleValue() * val.doubleValue());
        imaginary = convertToT(imaginary.doubleValue() * val.doubleValue());
    }
}