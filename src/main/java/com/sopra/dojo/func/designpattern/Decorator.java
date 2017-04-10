package com.sopra.dojo.func.designpattern;

public class Decorator {

    interface SalaryCalculator {
        double calculate(double grossAnnual);
    }

    static class DefaultSalaryCalculator implements SalaryCalculator {
        @Override
        public double calculate(double grossAnnual) {
            return grossAnnual / 12;
        }
    }

    static abstract class AbstractTaxDecorator implements SalaryCalculator {
        private final SalaryCalculator salaryCalculator;

        public AbstractTaxDecorator(SalaryCalculator salaryCalculator) {
            this.salaryCalculator = salaryCalculator;
        }

        protected abstract double applyTax(double salary);

        @Override
        public final double calculate(double gross) {
            double salary = salaryCalculator.calculate(gross);
            return applyTax(salary);
        }
    }

    static class GeneralTaxDecorator extends AbstractTaxDecorator {
        public GeneralTaxDecorator(SalaryCalculator salaryCalculator) {
            super(salaryCalculator);
        }

        @Override
        protected double applyTax(double salary) {
            return salary * 0.8;
        }
    }

    static class RegionalTaxDecorator extends AbstractTaxDecorator {
        public RegionalTaxDecorator(SalaryCalculator salaryCalculator) {
            super(salaryCalculator);
        }

        @Override
        protected double applyTax(double salary) {
            return salary * 0.95;
        }
    }

    static class HealthInsuranceDecorator extends AbstractTaxDecorator {
        public HealthInsuranceDecorator(SalaryCalculator salaryCalculator) {
            super(salaryCalculator);
        }

        @Override
        protected double applyTax(double salary) {
            return salary - 200.0;
        }
    }

    public static void main(String[] args) {
        double netSalary = new HealthInsuranceDecorator(
                new RegionalTaxDecorator(
                        new GeneralTaxDecorator(
                                new DefaultSalaryCalculator()
                        )
                )
        ).calculate(30000.00);
        System.out.println("Net Salary " + netSalary);
    }

}
