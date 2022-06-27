package lv.vktranzits.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lv.vktranzits.demo.repos.*;
import lv.vktranzits.demo.models.*;

@SpringBootApplication
@EnableJpaRepositories("lv.vktranzits.demo.*")
@ComponentScan(basePackages = { "lv.vktranzits.demo.*" })
@EntityScan("lv.vktranzits.demo.*")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(ICompanyRepo companyRepo, ICourseCalendarRepo courseCalRepo,
	 ICourseTypeRepo courseTypeRepo, IPositionRepo posRepo,
	 IDepartmentRepo departmentRepo, ICourseRepo courseRepo, IEmployeeRepo employeeRepo)
	{

		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				
				// // Create company
				// Company company = new Company("VK tranzits");
				// companyRepo.save(company);

				// // Create department
				// Department department = new Department("IT", "Janis", "Berzins");
				// departmentRepo.save(department);

				// // Create position
				// Position position = new Position("Junior Java developer", "Random description");
				// posRepo.save(position);
				CourseType t1 = new CourseType(true, "Vienu reizi gadā");
				courseTypeRepo.save(t1);
				
				Course c1 = new Course("Java tests", "Tests par Java pamatiem", t1);
				Course c2 = new Course("Java tests", "Tests par Java pamatiem", t1);
				Course c3 = new Course("Java tests", "Tests par Java pamatiem", t1);
				Course c4 = new Course("Java tests", "Tests par Java pamatiem", t1);
				courseRepo.save(c1);
				courseRepo.save(c2);
				courseRepo.save(c3);
				courseRepo.save(c4);
				
			}
		};
	}
	
	
	
	
	

}
