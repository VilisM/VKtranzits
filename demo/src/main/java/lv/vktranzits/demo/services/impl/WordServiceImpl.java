package lv.vktranzits.demo.services.impl;

import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.FileOutputStream;
import java.util.ArrayList;

import lv.vktranzits.demo.models.Course;
import lv.vktranzits.demo.repos.ICourseRepo;
import lv.vktranzits.demo.services.IWordService;




@Service
public class WordServiceImpl implements IWordService{
   

    @Autowired
    private ICourseRepo courseRepo;
    
	@Override
    public void saveDataInWord() {
        XWPFDocument document = new XWPFDocument();


		ArrayList<Course> allCourses = (ArrayList<Course>) courseRepo.findAll();

        XWPFParagraph title = document.createParagraph();
        title.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun titleRun = title.createRun();
        titleRun.setText("Visi kursi");
        titleRun.setColor("009933");
        titleRun.setBold(true);
        titleRun.setFontFamily("Courier");
        titleRun.setFontSize(20);

        XWPFParagraph subTitle = document.createParagraph();
        subTitle.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun subTitleRun = subTitle.createRun();
        subTitleRun.setText("Kursu saraksts");
        subTitleRun.setColor("00CC44");
        subTitleRun.setFontFamily("Courier");
        subTitleRun.setFontSize(16);
        subTitleRun.setTextPosition(20);
        subTitleRun.setUnderline(UnderlinePatterns.DOT_DOT_DASH);

        for (Course cor: allCourses)
    {
        XWPFParagraph para1 = document.createParagraph();
        para1.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun para1Run = para1.createRun();
        para1Run.setText(cor.getTitle());
        para1Run.setBold(true);
        para1Run.setColor("00CC44");

        XWPFParagraph para2 = document.createParagraph();
        para2.setAlignment(ParagraphAlignment.BOTH);
        XWPFRun para2Run = para1.createRun();
        para2Run.setText(cor.getDescription());
        para2Run.setItalic(true);

    }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream("AllCourses.doc");
            document.write(out);
            out.close();
            document.close();
            System.out.println("AllCourses.doc written successfully on disk.");
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }


    //     XWPFParagraph image = document.createParagraph();
    //    image.setAlignment(ParagraphAlignment.CENTER);
    //     XWPFRun imageRun = image.createRun();
    //     imageRun.setTextPosition(20);
    //     Path imagePath = Paths.get(ClassLoader.getSystemResource(logo).toURI());
    //   imageRun.addPicture(Files.newInputStream(imagePath), XWPFDocument.PICTURE_TYPE_JPEG, imagePath.getFileName().toString(), Units.toEMU(50), Units.toEMU(50));

        // XWPFParagraph sectionTitle = document.createParagraph();
        // XWPFRun sectionTRun = sectionTitle.createRun();
        // sectionTRun.setText("What makes a good API?");
        // sectionTRun.setColor("00CC44");
        // sectionTRun.setBold(true);
        // sectionTRun.setFontFamily("Courier");

        // XWPFParagraph para1 = document.createParagraph();
        // para1.setAlignment(ParagraphAlignment.BOTH);
        // String string1 = convertTextFileToString(paragraph1);
        // XWPFRun para1Run = para1.createRun();
        // para1Run.setText(string1);

        // XWPFParagraph para2 = document.createParagraph();
        // para2.setAlignment(ParagraphAlignment.RIGHT);
        // String string2 = convertTextFileToString(paragraph2);
        // XWPFRun para2Run = para2.createRun();
        // para2Run.setText(string2);
        // para2Run.setItalic(true);

        // XWPFParagraph para3 = document.createParagraph();
        // para3.setAlignment(ParagraphAlignment.LEFT);
        // String string3 = convertTextFileToString(paragraph3);
        // XWPFRun para3Run = para3.createRun();
        // para3Run.setText(string3);

        // FileOutputStream out = new FileOutputStream(output);
        // document.write(out);
        // out.close();
        // document.close();
    }

    // public String convertTextFileToString(String fileName) {
    //     try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(fileName).toURI()))) {
    //         return stream.collect(Collectors.joining(" "));
    //     } catch (IOException | URISyntaxException e) {
    //         e.printStackTrace();
    //     }
    //     return null;
    // }

 

    @Override
    public void loadDataFromWord() {
        
    }






}
