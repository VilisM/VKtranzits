package lv.vktranzits.demo.services;

import lv.vktranzits.demo.models.Course;
import lv.vktranzits.demo.models.ResultView;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

public interface IExcelService {
    
	void loadDataFromExcel();
	void saveDataInExcel();
	boolean loadResultsFromExcel(String courseTitle, Course course, InputStream file);
	boolean saveResultDataInExcel(ResultView resultView);
	void exportExcelResult(HttpServletResponse response);
}





