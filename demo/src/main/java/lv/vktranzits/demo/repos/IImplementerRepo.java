package lv.vktranzits.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.vktranzits.demo.models.Implementer;

public interface IImplementerRepo extends CrudRepository<Implementer, Integer> {
    
}
