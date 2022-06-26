package lv.vktranzits.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.vktranzits.demo.models.Position;

public interface IPositionRepo extends CrudRepository<Position, Integer> {
    
}
