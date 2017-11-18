package funcion;

import de.congrace.exp4j.*;

public class Funcion {

    protected ExpressionBuilder parser;

    public Funcion() {

    }

    public Funcion(String funcion, boolean reporte) {

        if (reporte) {

            System.out.println("");
            System.out.println("Funcion: " + funcion);

        }

        this.parser = new ExpressionBuilder(funcion);

    }

    public Funcion(String funcion) {

        System.out.println("");
        System.out.println("Funcion: " + funcion);

        this.parser = new ExpressionBuilder(funcion);

    }

    @SuppressWarnings("UseSpecificCatch")
    public double evaluar(double valor) {

        double resultado = 0;

        try {

            resultado = this.evaluar(valor, false);

        } catch (Exception exception) {

        }

        return resultado;
    }

    @SuppressWarnings("UseSpecificCatch")
    public double evaluar(double x, double y) {

        double resultado = 0;

        try {

            resultado = this.evaluar(x, y, false);

        } catch (Exception ex) {

        }

        return resultado;
    }

    @SuppressWarnings("UseSpecificCatch")
    public double evaluar() {

        double resultado = 0;

        try {

            resultado = this.evaluar(false);

        } catch (Exception ex) {

        }

        return resultado;

    }

    public double evaluar(double x, double y, boolean error) throws UnknownFunctionException, UnparsableExpressionException {

        @SuppressWarnings("UnusedAssignment")
        double resultado = 0;

        Calculable calc = this.parser.withVariable("x", x).withVariable("y", y).build();

        resultado = calc.calculate();

        return resultado;

    }

    public double evaluar(double valor, boolean error) throws UnknownFunctionException, UnparsableExpressionException {

        @SuppressWarnings("UnusedAssignment")
        double resultado = 0;

        Calculable calc = this.parser.withVariable("x", valor).build();

        resultado = calc.calculate();

        return resultado;

    }

    public double evaluar(boolean error) throws UnknownFunctionException, UnparsableExpressionException {

        return this.evaluar(0, error);

    }

}