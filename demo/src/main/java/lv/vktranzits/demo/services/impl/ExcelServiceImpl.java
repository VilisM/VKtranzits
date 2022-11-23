package lv.vktranzits.demo.services.impl;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

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
	public void loadResultsFromExcel() {
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

			//Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) 
	            {
				Row dataRow = rowIterator.next(); //get row 1 to row n (rows containing data)
				if(dataRow.getRowNum() == 0) {
					continue;
				}
			   
				int idxForColumn1 = map.get("Uzvārds"); //get the column index for the column with header name = "Column1"
				int idxForColumn2 = map.get("Vārds"); //get the column index for the column with header name = "Column2"
				int idxForColumn3 = map.get("Pabeigts"); //get the column index for the column with header name = "Column3"
				int idxForColumn4 = map.get("Vērtējums/10,00"); //get the column index for the column with header name = "Column3"
			   
				Cell cell1 = dataRow.getCell(idxForColumn1); //Get the cells for each of the indexes
				Cell cell2 = dataRow.getCell(idxForColumn2); 
				if (cell1.getStringCellValue() == "" && cell2.getStringCellValue() == "") {
					break;
				}
				Cell cell3 = dataRow.getCell(idxForColumn3);  
				Cell cell4 = dataRow.getCell(idxForColumn4);  
				//NOTE THAT YOU HAVE TO KNOW THE DATA TYPES OF THE DATA YOU'RE EXTRACTING.
				//FOR EXAMPLE I DON'T THINK YOU CAN USE cell.getStringCellValue IF YOU'RE TRYING TO GET A NUMBER
				System.out.println(cell1.getStringCellValue() + " \t");
				System.out.println(cell2.getStringCellValue() + " \t");
				System.out.println(cell3.getStringCellValue() + " \t");
				System.out.println(Double.parseDouble(cell4.getStringCellValue().replace(',','.')) + " \t");
			   
			   }

			file.close();
			workbook.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
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
