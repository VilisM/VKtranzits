package lv.vktranzits.demo.services;

import lv.vktranzits.demo.models.Course;
import lv.vktranzits.demo.models.ResultView;
import org.springframework.core.io.FileSystemResource;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

public interface IExcelService {
    
	public void loadDataFromExcel();
	public void saveDataInExcel();
	public boolean loadResultsFromExcel(String courseTitle, Course course, InputStream file);
	public boolean saveResultDataInExcel(ResultView resultView);
	public void exportExcelResult(HttpServletResponse response);
}





