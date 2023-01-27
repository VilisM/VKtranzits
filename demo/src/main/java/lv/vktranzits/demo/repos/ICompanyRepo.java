package lv.vktranzits.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.vktranzits.demo.models.Company;

public interface ICompanyRepo extends CrudRepository<Company, Integer> {

    Company findByTitle(String title);
    
}
