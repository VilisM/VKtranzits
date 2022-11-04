package lv.vktranzits.demo.services.impl;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.vktranzits.demo.models.Employee;
import lv.vktranzits.demo.repos.IEmployeeRepo;
import lv.vktranzits.demo.services.IExcelService;

@Service
public class ExcelServiceImpl implements IExcelService{
    
	@Autowired
    private IEmployeeRepo employeeRepo;

	




	// @Override
    // public ArrayList<Employee> selectAllEmployees() {
    //     return (ArrayList<Employee>) employeeRepo.findAll();
    // }



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
	                    }
	                }
	                System.out.println("");
	            }
	            file.close();
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
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
               


	        }
	}
	





}
