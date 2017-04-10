package com.sopra.dojo.func.designpattern;

import java.util.Arrays;
import java.util.function.DoubleUnaryOperator;

public class Decorator {

    static class SalaryCalculator {
        static double defaultSalaryCalculator(double salary) {
            return salary / 12;
        }

        static double generalTax(double salary) {
            return salary * 0.8;
        }

        static double regionalTax(double salary) {
            return salary * 0.95;
        }

        static double healthInsurance(double salary) {
            return salary - 200;
        }

    }

    public static double calculate(double grossSalary, DoubleUnaryOperator... operators) {
        return Arrays.stream(operators)
                .reduce(DoubleUnaryOperator.identity(), DoubleUnaryOperator::andThen)
                .applyAsDouble(grossSalary);
    }

    public static void main(String[] args) {
        double netSalary = calculate(30000.00,
                SalaryCalculator::defaultSalaryCalculator,
                SalaryCalculator::generalTax,
                SalaryCalculator::regionalTax,
                SalaryCalculator::healthInsurance
        );

        System.out.println("Net Salary " + netSalary);
    }

}
