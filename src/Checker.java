import java.util.ArrayList;

public class Checker {

    String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    MonthlyReport monthlyReport;
    YearlyReport yearlyReport;

    public Checker(MonthlyReport monthlyReport, YearlyReport yearlyReport) {
        this.monthlyReport = monthlyReport;
        this.yearlyReport = yearlyReport;
    }

    public void checkReportsCorrespondence() {
        boolean isValid = true;

        if ((!monthlyReport.expensesMonth.isEmpty() || !monthlyReport.earningsMonth.isEmpty())
                && (!yearlyReport.expensesYear.isEmpty() || !yearlyReport.earningsYear.isEmpty())) {

            for (Integer month : monthlyReport.expensesMonth.keySet()) {
                int sumExp = 0;
                for (RecordMonth record : monthlyReport.expensesMonth.get(month)) {
                    int sum = record.quantity * record.price;
                    sumExp += sum;
                }
                for (RecordYear recordY : yearlyReport.expensesYear) {
                    if (month == recordY.month && sumExp != recordY.amount) {
                        System.out.println("Обнаружна несовпадение в месяце " + months[month - 1] + ". В месячном отчете - расходы: " + sumExp + ". В годовом отчете: " + recordY.amount);
                        isValid = false;
                    }
                }
            }

            for (Integer month : monthlyReport.earningsMonth.keySet()) {
                int sumEarn = 0;
                for (RecordMonth record : monthlyReport.earningsMonth.get(month)) {
                    int sum = record.quantity * record.price;
                    sumEarn += sum;
                }
                for (RecordYear recordY : yearlyReport.earningsYear) {
                    if (month == recordY.month && sumEarn != recordY.amount) {
                        System.out.println("Обнаружна несовпадение в месяце " + months[month - 1] + ". В месячном отчете - доходы: " + sumEarn + ". В годовом отчете: " + recordY.amount);
                        isValid = false;
                    }
                }
            }
        } else {
            System.out.println("Данные не обнаружены. Необходимо считать отчеты");
            isValid = false;
        }
        if (isValid == true) {
            System.out.println("Все отчеты верны между собой");
        }
    }
}