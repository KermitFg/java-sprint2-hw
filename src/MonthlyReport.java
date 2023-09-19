import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {

    String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    HashMap<Integer, ArrayList<Record>> expensesM = new HashMap<>();
    HashMap<Integer, ArrayList<Record>> earningsM = new HashMap<>();


    FileReader fileReader = new FileReader();

    void readMonthlyReports(String fileName1, int month, int monthEnd) {
        for (int i = month; i <= monthEnd; i++) {
            String fileName = fileName1 + i + ".csv";
            ArrayList<String> strings = fileReader.readFileContents(fileName);
            strings.remove(0);

            ArrayList<Record> recordsExp = new ArrayList<>();
            ArrayList<Record> recordsEarn = new ArrayList<>();

            for (String string : strings) {
                String[] split = string.split(",");
                if (split[1].equals("TRUE")) {
                    Record record = new Record(split[0],
                            Integer.parseInt(split[2]),
                            Integer.parseInt(split[3]));
                    recordsExp.add(record);
                } else {
                    Record record = new Record(split[0],
                            Integer.parseInt(split[2]),
                            Integer.parseInt(split[3]));
                    recordsEarn.add(record);
                }
            }
            expensesM.put(i, recordsExp);
            earningsM.put(i, recordsEarn);
        }
    }

    public void printStatMonthly() {

        for (Integer month : earningsM.keySet()) {
            int maxEarn = 0;
            int maxExp = 0;
            String maxEarnTitle = "";
            String maxExpTitle = "";
            System.out.println("За " + months[month - 1] + ":");
            for (Record record : earningsM.get(month)) {
                int maxEa = record.quantity * record.price;
                if (maxEa > maxEarn) {
                    maxEarn = maxEa;
                    maxEarnTitle = record.name;
                }
            }
            for (Record record : expensesM.get(month)) {
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

    public void printProfitPerMonth() {
        int year = 2021;
        System.out.println("Год отчетности: " + year);
        for (Integer month : expensesM.keySet()) {

            int sumEarn = 0;
            int sumExp = 0;

            for (Record record : expensesM.get(month)) {
                int sum = record.price * record.quantity;
                sumExp += sum;
            }
            for (Record record : earningsM.get(month)) {
                int sum = record.price * record.quantity;
                sumEarn += sum;
            }
            System.out.println("За " + months[month - 1] + " прибыль составила: " + (sumEarn - sumExp) + " рублей");
        }
    }
    public void printAverage() {
        int sumExp = 0;
        int sumEarn = 0;

        for (Integer month : expensesM.keySet()) {

            for (Record record : expensesM.get(month)) {
                int sum = record.price * record.quantity;
                sumExp += sum;
            }
            for (Record record : earningsM.get(month)) {
                int sum = record.price * record.quantity;
                sumEarn += sum;
            }
        }
        System.out.println("Среднемесячный доход: " + sumEarn / 3 + " рублей");
        System.out.println("Среднемесячный расход: " + sumExp / 3 + " рублей");
    }
}