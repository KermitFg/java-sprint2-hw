import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {

    String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    HashMap<Integer, ArrayList<RecordMonth>> expensesMonth = new HashMap<>();
    HashMap<Integer, ArrayList<RecordMonth>> earningsMonth = new HashMap<>();


    FileReader fileReader = new FileReader();

    void readMonthlyReports(String nameOfFile, int month, int monthEnd) {
        for (int i = month; i <= monthEnd; i++) {
            String fileName = nameOfFile + i + ".csv";
            ArrayList<String> strings = fileReader.readFileContents(fileName);
            strings.remove(0);

            ArrayList<RecordMonth> recordsExp = new ArrayList<>();
            ArrayList<RecordMonth> recordsEarn = new ArrayList<>();

            for (String string : strings) {
                String[] split = string.split(",");
                if (split[1].equals("TRUE")) {
                    RecordMonth record = new RecordMonth(split[0],
                            Integer.parseInt(split[2]),
                            Integer.parseInt(split[3]));
                    recordsExp.add(record);
                } else {
                    RecordMonth record = new RecordMonth(split[0],
                            Integer.parseInt(split[2]),
                            Integer.parseInt(split[3]));
                    recordsEarn.add(record);
                }
            }
            expensesMonth.put(i, recordsExp);
            earningsMonth.put(i, recordsEarn);
        }
    }

    public void printStatMonthly() {

        for (Integer month : earningsMonth.keySet()) {
            int maxEarn = 0;
            int maxExp = 0;
            String maxEarnTitle = "";
            String maxExpTitle = "";
            System.out.println("За " + months[month - 1] + ":");
            for (RecordMonth record : earningsMonth.get(month)) {
                int maxEa = record.quantity * record.price;
                if (maxEa > maxEarn) {
                    maxEarn = maxEa;
                    maxEarnTitle = record.name;
                }
            }
            for (RecordMonth record : expensesMonth.get(month)) {
                int maxEx = record.quantity * record.price;
                if (maxEx > maxExp) {
                    maxExp = maxEx;
                    maxExpTitle = record.name;
                }
            }
            System.out.println("Самый большой доход за " + maxEarnTitle + " и составил он - " + maxEarn + " рублей");
            System.out.println("Самая большая трата за " + maxExpTitle + " и составил он - " + maxExp + " рублей");
        }
    }

    public void printProfitPerMonth() { //с этим разберусь
        int year = 2021;
        System.out.println("Год отчетности: " + year);
        for (Integer month : expensesMonth.keySet()) {

            int sumEarn = 0;
            int sumExp = 0;

            for (RecordMonth record : expensesMonth.get(month)) {
                int sum = record.price * record.quantity;
                sumExp += sum;
            }
            for (RecordMonth record : earningsMonth.get(month)) {
                int sum = record.price * record.quantity;
                sumEarn += sum;
            }
            System.out.println("За " + months[month - 1] + " прибыль составила: " + (sumEarn - sumExp) + " рублей");
        }
    }
    public void printAverage() {
        int sumExp = 0;
        int sumEarn = 0;

        for (Integer month : expensesMonth.keySet()) {

            for (RecordMonth record : expensesMonth.get(month)) {
                int sum = record.price * record.quantity;
                sumExp += sum;
            }
            for (RecordMonth record : earningsMonth.get(month)) {
                int sum = record.price * record.quantity;
                sumEarn += sum;
            }
        }
        System.out.println("Среднемесячный доход: " + sumEarn / 3 + " рублей");
        System.out.println("Среднемесячный расход: " + sumExp / 3 + " рублей");
    }
}