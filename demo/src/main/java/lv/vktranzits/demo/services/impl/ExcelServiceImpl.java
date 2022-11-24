package lv.vktranzits.demo.services.impl;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.vktranzits.demo.models.Course;
import lv.vktranzits.demo.models.Employee;
import lv.vktranzits.demo.models.EmployeeCourse;
import lv.vktranzits.demo.models.ResultView;
import lv.vktranzits.demo.repos.IEmployeeCourseRepo;
import lv.vktranzits.demo.repos.IEmployeeRepo;
import lv.vktranzits.demo.services.IExcelService;

@Service
public class ExcelServiceImpl implements IExcelService{
    
	@Autowired
    private IEmployeeRepo employeeRepo;

	@Autowired
	private IEmployeeCourseRepo empCourseRepo;

	private Date parseDate(String date, String format) throws ParseException
	{
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.parse(date);
	}


	// @Override
    // public ArrayList<Employee> selectAllEmployees() {
    //     return (ArrayList<Employee>) employeeRepo.findAll();
    // }

	@Override
	public boolean saveResultDataInExcel(ResultView resultView) {

		ArrayList<EmployeeCourse> allGrades = (ArrayList<EmployeeCourse>) empCourseRepo.findAll();
		 
		  	XSSFWorkbook workbook = new XSSFWorkbook(); 
	        int num = 0;
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Employee ratings for courses");
			Row firstRow = sheet.createRow(0);

			if(resultView.isFullNameEnabled == 1) {
				Cell cellFirst = firstRow.createCell(num++);
				cellFirst.setCellValue("Vārds, uzvārds");
			}
			if(resultView.isTitleEnabled == 1) {
            	Cell cellFirst2 = firstRow.createCell(num++);
            	cellFirst2.setCellValue("Nosaukums");
			}
			if(resultView.isRatingEnabled == 1) {
				Cell cellFirst3 = firstRow.createCell(num++);
				cellFirst3.setCellValue("Vērtējums");
			}
			if(resultView.isDateEnabled == 1) {
				Cell cellFirst4 = firstRow.createCell(num++);
				cellFirst4.setCellValue("Datums");
			}
	        int rownum = 1;
	        for (EmployeeCourse grade: allGrades)
	        {
	            Row row = sheet.createRow(rownum++);
	            num = 0;
				if(resultView.isFullNameEnabled == 1) {
					Cell cell = row.createCell(num++);
					cell.setCellValue(grade.getEmployee().getName() + " " + grade.getEmployee().getSurname());
				}
				if(resultView.isTitleEnabled == 1) {
					Cell cell2 = row.createCell(num++);
					cell2.setCellValue(grade.getTitle());
				}
				if(resultView.isRatingEnabled == 1) {
                	Cell cell3 = row.createCell(num++);
                	cell3.setCellValue(grade.getValuePr());
				}
				if(resultView.isDateEnabled == 1) {
                	Cell cell4 = row.createCell(num++);
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
                	cell4.setCellValue(dateFormat.format(grade.getDate()));
				}
			}
                try
                {
					Date date = new Date();
                    //Write the workbook in file system
                    FileOutputStream out = new FileOutputStream
					(new File("results_" + date.getTime() + ".xlsx"));
                    workbook.write(out);
                    out.close();
                    System.out.println("results_" + date.getTime() + ".xlsx written successfully on disk.");
					workbook.close();
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
			return true;
	}

	@Override
	public boolean loadResultsFromExcel(String courseTitle, Course course) {
		if(courseTitle.isEmpty() || courseTitle.isBlank()) {
			return false;
		}
		try
		{
			FileInputStream file = new FileInputStream(new File("resultsExcel.xlsx"));
 
			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);
	 
			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			Map<String, Integer> map = new HashMap<String,Integer>();
			Row row = sheet.getRow(0);
			short minColIx = row.getFirstCellNum();
			short maxColIx = row.getLastCellNum();
			for(short colIx=minColIx; colIx<maxColIx; colIx++) {
				Cell cell = row.getCell(colIx);
				map.put(cell.getStringCellValue(),cell.getColumnIndex());
			}

			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) 
	            {
				Row dataRow = rowIterator.next();
				if(dataRow.getRowNum() == 0) {
					continue;
				}
			   
				int idxForColumn1 = map.get("Uzvārds");
				int idxForColumn2 = map.get("Vārds"); 
				int idxForColumn3 = map.get("Pabeigts");
				int idxForColumn4 = map.get("Vērtējums/10,00"); 
			   
				Cell cell1 = dataRow.getCell(idxForColumn1);
				Cell cell2 = dataRow.getCell(idxForColumn2); 
				if (cell1.getStringCellValue() == "" && cell2.getStringCellValue() == "") {
					break;
				}
				Cell cell3 = dataRow.getCell(idxForColumn3);  
				Cell cell4 = dataRow.getCell(idxForColumn4);  
				if(employeeRepo.existsByNameAndSurname(cell2.getStringCellValue(), cell1.getStringCellValue())){
					Employee employee = employeeRepo.findByNameAndSurname(cell2.getStringCellValue(), cell1.getStringCellValue());
					float valuePr = Float.parseFloat(cell4.getStringCellValue().replace(',','.'));
					Date date = parseDate(cell3.getStringCellValue(), "yyyy'. gada' dd'.' MMM HH:mm");
					EmployeeCourse empCourse1 = new EmployeeCourse(courseTitle, valuePr, date, employee, course);
					empCourseRepo.save(empCourse1);
				}	   
			   }

			file.close();
			workbook.close();
			return true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return false;
}



    @Override
	public void loadDataFromExcel() {		
		
		 try
	        {
	            FileInputStream file = new FileInputStream(new File("products.xlsx"));
	 
	            //Create Workbook instance holding reference to .xlsx file
	            XSSFWorkbook workbook = new XSSFWorkbook(file);
	 
	            //Get first/desired sheet from the workbook
	            XSSFSheet sheet = workbook.getSheetAt(0);
	 
	            //Iterate through each rows one by one
	            Iterator<Row> rowIterator = sheet.iterator();
	            while (rowIterator.hasNext()) 
	            {
	                Row row = rowIterator.next();
	                //For each row, iterate through all the columns
	                Iterator<Cell> cellIterator = row.cellIterator();
	                 
	                while (cellIterator.hasNext()) 
	                {
	                    Cell cell = cellIterator.next();
	                    //Check the cell type and format accordingly
	                    switch (cell.getCellType()) 
	                    {
	                        case NUMERIC:
	                            System.out.print(cell.getNumericCellValue() + "t");
	                            break;
	                        case STRING:
	                            System.out.print(cell.getStringCellValue() + "t");
	                            break;
							default:
								break;
	                    }
	                }
	                System.out.println("");
	            }
	            file.close();
				workbook.close();
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	}

		//  (Arrays.asList(new Employee("Janis","Krumins",29879894,"krumins@gmail.com", "123"),
		//  new Employee("Juris","Jurevics", 21234567,"juris@gmail.com", "456"),
		//  new Employee("Leons","Jurevics", 21234567,"juris@gmail.com", "456")
		//  ));
		

	@Override
	public void saveDataInExcel() {
		
		ArrayList<Employee> allEmployees = (ArrayList<Employee>) employeeRepo.findAll();
		 
		  	XSSFWorkbook workbook = new XSSFWorkbook(); 
	         
	        //Create a blank sheet
	        XSSFSheet sheet = workbook.createSheet("Employees Data");
	        int rownum = 0;
	        for (Employee emp: allEmployees)
	        {
	            Row row = sheet.createRow(rownum++);
	            
	            Cell cell = row.createCell(1);
                cell.setCellValue(emp.getName());
                Cell cell2 = row.createCell(2);
                cell2.setCellValue(emp.getSurname());
                Cell cell3 = row.createCell(3);
                cell3.setCellValue(emp.getPhone());
                Cell cell4 = row.createCell(4);
                cell4.setCellValue(emp.getEmail());
                Cell cell5 = row.createCell(5);
                cell5.setCellValue(emp.getPassword());
                
                try
                {
                    //Write the workbook in file system
                    FileOutputStream out = new FileOutputStream
					(new File("employees.xlsx"));
                    workbook.write(out);
                    out.close();
                    System.out.println("employees.xlsx written successfully on disk.");
					workbook.close();
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
	        }
	}
	





}
