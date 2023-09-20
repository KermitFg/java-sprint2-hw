import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        YearlyReport yearlyReport = new YearlyReport();
        MonthlyReport monthlyReport = new MonthlyReport();

        while (true) {
            printMenu();

            int userInput = scanner.nextInt();
            if (userInput == 1) {
                monthlyReport.readMonthlyReports("m.20210", 1,3);
                System.out.println("Ежемесячные отчеты считаны");
            } else if (userInput == 2) {
                yearlyReport.readYearlyReports("y.2021.csv");
                System.out.println("Годовой отчет считан");
            } else if (userInput == 3) {
                Checker checker = new Checker(monthlyReport, yearlyReport);
                    checker.checkReportsCorrespondence();// сходятся ли отчеты за месяц и год
            } else if (userInput == 4) {
                monthlyReport.printStatMonthly();
                // Вывести информацию обо всех месячных отчётах — по
                // сохранённым данным вывести в консоль имеющуюся информацию.
            } else if (userInput == 5) {
                monthlyReport.printProfitPerMonth();
                monthlyReport.printAverage();
                // Выести информацию о годовом отчёте — по
                // сохранённым данным вывести в консоль имеющуюся информацию.
            } else if (userInput == 6) {
                System.out.println("Пока");
                scanner.close();
                return;
            } else {
                System.out.println("Такой команды нет");
            }
        }
    }

    static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все ежемесячные отчеты");
        System.out.println("2 - Считать годовой отчет");
        System.out.println("3 - Сверить отчеты");
        System.out.println("4 - Вывести информацию обо всех месячных отчетах");
        System.out.println("5 - Вывести информацию о годовом отчете");
        System.out.println("6 - Выйти из приложения");
    }
}

