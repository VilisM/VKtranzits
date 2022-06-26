package lv.vktranzits.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lv.vktranzits.demo.repos.*;

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
	 IDepartmentRepo departmentRepo, ICourseRepo courseRepo)
	{

		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {

			}
		};
	}

}
