import java.util.ArrayList;

public class YearlyReport {

    FileReader fileReader = new FileReader();

    ArrayList<RecordYear> expensesYear = new ArrayList<>();
    ArrayList<RecordYear> earningsYear = new ArrayList<>();

    void readYearlyReports(String fileName) {
        ArrayList<String> strings = fileReader.readFileContents(fileName);
        strings.remove(0);

        for (String string : strings) {
            String[] split = string.split(",");
            if (split[2].equals("true")) {
                RecordYear recordY = new RecordYear(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                expensesYear.add(recordY);
            } else {
                RecordYear recordY = new RecordYear(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                earningsYear.add(recordY);
            }
        }
    }
}
