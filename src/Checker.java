import java.util.ArrayList;

public class Checker {

    String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    MonthlyReport monthlyReport;
    YearlyReport yearlyReport;

    public Checker(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
    }

    public void check() {
        boolean okay = true;

        if ((!monthlyReport.expensesM.isEmpty() || !monthlyReport.earningsM.isEmpty())
                && (!yearlyReport.expensesY.isEmpty() || !yearlyReport.earningsY.isEmpty())) {

            for (Integer month : monthlyReport.expensesM.keySet()) {
                int sumExp = 0;
                for (Record record : monthlyReport.expensesM.get(month)) {
                    int sum = record.quantity * record.price;
                    sumExp += sum;
                }
                for (RecordY recordY : yearlyReport.expensesY) {
                    if (month == recordY.month && sumExp != recordY.amount) {
                        System.out.println("Обнаружна несовпадение в месяце " + months[month - 1] + ". В месячном отчете - расходы: " + sumExp + ". В годовом отчете: " + recordY.amount);
                        okay = false;
                    }
                }
            }

            for (Integer month : monthlyReport.earningsM.keySet()) {
                int sumEarn = 0;
                for (Record record : monthlyReport.earningsM.get(month)) {
                    int sum = record.quantity * record.price;
                    sumEarn += sum;
                }
                for (RecordY recordY : yearlyReport.earningsY) {
                    if (month == recordY.month && sumEarn != recordY.amount) {
                        System.out.println("Обнаружна несовпадение в месяце " + months[month - 1] + ". В месячном отчете - доходы: " + sumEarn + ". В годовом отчете: " + recordY.amount);
                        okay = false;
                    }
                }
            }
        } else {
            System.out.println("Данные не обнаружены. Необходимо считать отчеты");
            okay = false;
        }
        if (okay == true) {
            System.out.println("Все отчеты верны между собой");
        }
    }
}