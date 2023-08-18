public class ComplexMath
{
    private ComplexMath() {}

    public static Complex add(Complex<? extends Number> c1, Complex<? extends Number> c2)
    {
        var real1 = c1.getReal();
        var real2 = c2.getReal();
        var imag1 = c1.getImaginary();
        var imag2 = c2.getImaginary();

        if ( real1 instanceof Double || real2 instanceof Double )
        {
            return new Complex<Double>(real1.doubleValue() + real2.doubleValue(),imag1.doubleValue() + imag2.doubleValue());
        }
        else if ( real1 instanceof Float || real2 instanceof Float )
        {
            return new Complex<Float>(real1.floatValue() + real2.floatValue(),imag1.floatValue() + imag2.floatValue());
        }
        else if ( real1 instanceof Long  || real2 instanceof Long )
        {
            return new Complex<Long>(real1.longValue() + real2.longValue(),imag1.longValue() + imag2.longValue());
        }
        else
        {
            return new Complex<Integer>(real1.intValue() + real2.intValue(), imag1.intValue() + imag2.intValue());
        }
    }

    public static Complex<?> sub(Complex<? extends Number> c1, Complex<? extends Number> c2)
    {
        var real1 = c1.getReal();
        var real2 = c2.getReal();
        var imag1 = c1.getImaginary();
        var imag2 = c2.getImaginary();

        if (real1 instanceof Double || real2 instanceof Double)
        {
            return new Complex<Double>(real1.doubleValue() - real2.doubleValue(), imag1.doubleValue() - imag2.doubleValue());
        }
        else if (real1 instanceof Float || real2 instanceof Float)
        {
            return new Complex<Float>(real1.floatValue() - real2.floatValue(), imag1.floatValue() - imag2.floatValue());
        }
        else if (real1 instanceof Long || real2 instanceof Long)
        {
            return new Complex<Long>(real1.longValue() - real2.longValue(), imag1.longValue() - imag2.longValue());
        }
        else
        {
            return new Complex<Integer>(real1.intValue() - real2.intValue(), imag1.intValue() - imag2.intValue());
        }
    }

    public static Complex<?> multiply(Complex<?> c1, Complex<?> c2)
    {
        var real1 = c1.getReal();
        var real2 = c2.getReal();
        var imag1 = c1.getImaginary();
        var imag2 = c2.getImaginary();

        // Sprawdzenie typów liczb rzeczywistych i wybór odpowiedniego typu wynikowego
        if (real1 instanceof Double || real2 instanceof Double || imag1 instanceof Double || imag2 instanceof Double)
        {
            return new Complex<Double>(
                    real1.doubleValue() * real2.doubleValue() - imag1.doubleValue() * imag2.doubleValue(),
                    real1.doubleValue() * imag2.doubleValue() + imag1.doubleValue() * real2.doubleValue()
            );
        }
        else if (real1 instanceof Float || real2 instanceof Float || imag1 instanceof Float || imag2 instanceof Float)
        {
            return new Complex<Float>(
                    real1.floatValue() * real2.floatValue() - imag1.floatValue() * imag2.floatValue(),
                    real1.floatValue() * imag2.floatValue() + imag1.floatValue() * real2.floatValue()
            );
        }
        else if (real1 instanceof Long || real2 instanceof Long || imag1 instanceof Long || imag2 instanceof Long)
        {
            return new Complex<Long>(
                    real1.longValue() * real2.longValue() - imag1.longValue() * imag2.longValue(),
                    real1.longValue() * imag2.longValue() + imag1.longValue() * real2.longValue()
            );
        }
        else
        {
            return new Complex<Integer>(
                    real1.intValue() * real2.intValue() - imag1.intValue() * imag2.intValue(),
                    real1.intValue() * imag2.intValue() + imag1.intValue() * real2.intValue()
            );
        }
    }

    public static Complex<?> add(Complex<?> c1, Number val)
    {
        Number real1 = c1.getReal();
        Number imag1 = c1.getImaginary();

        if (val instanceof Double || real1 instanceof Double || imag1 instanceof Double)
        {
            return new Complex<>(real1.doubleValue() + val.doubleValue(), imag1.doubleValue());
        }
        else if (val instanceof Float || real1 instanceof Float || imag1 instanceof Float)
        {
            return new Complex<>(real1.floatValue() + val.floatValue(), imag1.floatValue());
        }
        else if (val instanceof Long || real1 instanceof Long || imag1 instanceof Long)
        {
            return new Complex<>(real1.longValue() + val.longValue(), imag1.longValue());
        }
        else if (val instanceof Integer || real1 instanceof Integer || imag1 instanceof Integer)
        {
            return new Complex<>(real1.intValue() + val.intValue(), imag1.intValue());
        }
        else
        {
            return null;
        }
    }

    public static Complex<?> multiply(Complex<?> c1, Number val)
    {
        var real1 = c1.getReal();
        var imag1 = c1.getImaginary();
        var realVal = val.doubleValue();

        if (real1 instanceof Double || val instanceof Double)
        {
            return new Complex<>(real1.doubleValue() * realVal, imag1.doubleValue() * realVal);
        }
        else if (real1 instanceof Float || val instanceof Float)
        {
            return new Complex<>(real1.floatValue() * realVal, imag1.floatValue() * realVal);
        }
        else if (real1 instanceof Long || val instanceof Long)
        {
            return new Complex<>(real1.longValue() * realVal, imag1.longValue() * realVal);
        }
        else
        {
            return new Complex<>(real1.intValue() * realVal, imag1.intValue() * realVal);
        }
    }

    public static <T extends Number> Complex<?> changeType(Complex<?> c, Class<T> x)
    {
        Number real = c.getReal();
        Number imaginary = c.getImaginary();

        if (x.equals(Double.class))
        {
            return new Complex<Double>(real.doubleValue(), imaginary.doubleValue());
        }
        else if (x.equals(Float.class))
        {
            return new Complex<Float>(real.floatValue(), imaginary.floatValue());
        }
        else if (x.equals(Long.class))
        {
            return new Complex<Long>(real.longValue(), imaginary.longValue());
        }
        else if (x.equals(Integer.class))
        {
            return new Complex<Integer>(real.intValue(), imaginary.intValue());
        }
        else if (x.equals(Short.class))
        {
            return new Complex<Short>(real.shortValue(), imaginary.shortValue());
        }
        else if (x.equals(Byte.class))
        {
            return new Complex<Byte>(real.byteValue(), imaginary.byteValue());
        }
        else
        {
            throw new IllegalArgumentException("Nieznany typ x");
        }
    }
}