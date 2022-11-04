package lv.vktranzits.demo.repos;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import lv.vktranzits.demo.models.Position;

public interface IPositionRepo extends CrudRepository<Position, Integer> {

	ArrayList<Position> findAllByEmployeesIdEm(int idEm);
    
}
