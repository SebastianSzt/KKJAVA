public class Main
{
    public static void main(String[] args) throws ClassNotFoundException
    {
        Complex<Integer> complex1 = new Complex<>(2, 3);
        System.out.println("Complex1: " + complex1);
        Integer module1 = complex1.module();
        System.out.println("Moduł liczby zespolonej complex1: " + module1);


        Complex<Double> complex2 = new Complex<>(1.5, 2.5);
        System.out.println("Complex2: " + complex2);
        Double module2 = complex2.module();
        System.out.println("Moduł liczby zespolonej complex2: " + module2);

        Complex<Integer> suma1 = complex1.add(complex2);
        Complex<Double> suma2 = complex2.add(complex1);
        System.out.println("Suma1: " + suma1);
        System.out.println("Suma2: " + suma2);

        Complex<Integer> iloczyn1 = complex1.multiply(complex2);
        Complex<Double> iloczyn2 = complex2.multiply(complex1);
        System.out.println("Iloczyn1: " + iloczyn1);
        System.out.println("Iloczyn2: " + iloczyn2);

        System.out.println("Complex1 przed doodaniem wartości: " + complex1);
        complex1.add(4.5);
        System.out.println("Complex1 po dodaniu wartości: " + complex1);

        System.out.println("Complex2 przed doodaniem wartości: " + complex2);
        complex2.add(4.55);
        System.out.println("Complex2 po dodaniu wartości: " + complex2);

        System.out.println("Complex1 przed mnożeniem przez wartość: " + complex1);
        complex1.multiply(2.55);
        System.out.println("Complex1 po mnożeniu przez wartość: " + complex1);

        System.out.println("Complex2 przed mnożeniem przez wartość: " + complex2);
        complex2.multiply(4);
        System.out.println("Complex2 po mnożeniu przez wartość: " + complex2);


        Complex<Integer> c1 = new Complex<>(2, 3);
        Complex<Double> c2 = new Complex<>(1.5, 2.5);

        Complex<?> odejmowanie = ComplexMath.sub(c1, c2);
        System.out.println("Wynik odejmowania: " + odejmowanie);
        Complex<?> mnozenie = ComplexMath.multiply(c1, c2);
        System.out.println("Wynik mnożenia: " + mnozenie);

        Complex<Double> com1 = new Complex<>(2.0, 3.0);
        Number val = 4.5;
        Complex<?> result = ComplexMath.add(com1, val);
        System.out.println("Wynik dodawania: " + result);
        Complex<Float> com2 = new Complex<>(1.5f, 2.5f);
        val = 3.2f;
        result = ComplexMath.add(com2, val);
        System.out.println("Wynik dodawania: " + result);
        Complex<Long> com3 = new Complex<>(5L, 6L);
        val = 2.5;
        result = ComplexMath.add(com3, val);
        System.out.println("Wynik dodawania: " + result);
        Complex<Integer> com4 = new Complex<>(3, 4);
        val = 1;
        result = ComplexMath.add(com4, val);
        System.out.println("Wynik dodawania: " + result);

        val = 2.5;
        Complex<?> mnozenieval = ComplexMath.multiply(c1, val);
        System.out.println("Wynik mnożenia: " + result);

        Complex<Double> complexDouble = new Complex<>(1.5, 2.5);
        Complex<?> changedDoubleToInteger = ComplexMath.changeType(complexDouble, Integer.class);
        System.out.println("Zmieniony obiekt z typu Double na typ Integer: " + changedDoubleToInteger);
        Complex<?> changedDoubleToFloat = ComplexMath.changeType(complexDouble, Float.class);
        System.out.println("Zmieniony obiekt z typu Double na typ Float: " + changedDoubleToFloat);
        Complex<?> changedDoubleToLong = ComplexMath.changeType(complexDouble, Long.class);
        System.out.println("Zmieniony obiekt z typu Double na typ Long: " + changedDoubleToLong);
    }
}