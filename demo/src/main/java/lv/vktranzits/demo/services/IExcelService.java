package lv.vktranzits.demo.services;

import lv.vktranzits.demo.models.Course;
import lv.vktranzits.demo.models.ResultView;

public interface IExcelService {
    
	public void loadDataFromExcel();
	public void saveDataInExcel();
	public boolean loadResultsFromExcel(String courseTitle, Course course);
	public boolean saveResultDataInExcel(ResultView resultView);
}





