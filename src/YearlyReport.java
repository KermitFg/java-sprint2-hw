import java.util.ArrayList;

public class YearlyReport {

    String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

    FileReader fileReader = new FileReader();

    ArrayList<RecordY> expensesY = new ArrayList<>();
    ArrayList<RecordY> earningsY = new ArrayList<>();

    void readYearlyReports(String fileName) {
        ArrayList<String> strings = fileReader.readFileContents(fileName);
        strings.remove(0);

        for (String string : strings) {
            String[] split = string.split(",");
            if (split[2].equals("true")) {
                RecordY recordY = new RecordY(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                expensesY.add(recordY);
            } else {
                RecordY recordY = new RecordY(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                earningsY.add(recordY);
            }
        }
    }
}
