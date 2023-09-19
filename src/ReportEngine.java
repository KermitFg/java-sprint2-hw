import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReportEngine {

    HashMap<Integer, MonthlyReport> monthlyReports = new HashMap<>();
    YearlyReport yearlyReports = new YearlyReport();
    // Integer = номера месяцев, MonthlyReport = обьект месячного отчета
    FileReader fileReader = new FileReader();

}
