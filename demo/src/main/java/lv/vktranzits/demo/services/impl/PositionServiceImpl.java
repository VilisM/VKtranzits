package lv.vktranzits.demo.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.vktranzits.demo.models.Position;
import lv.vktranzits.demo.repos.IPositionRepo;
import lv.vktranzits.demo.services.IPositionService;

@Service
public class PositionServiceImpl implements IPositionService {
    
    @Autowired
    private IPositionRepo positionRepo;

    @Override
    public ArrayList<Position> selectAllPositions() {
        return (ArrayList<Position>) positionRepo.findAll();
    }

	@Override
	public ArrayList<Position> selectAllEmployeePositions(int employeeId) {
		return (ArrayList<Position>) positionRepo.findAllByEmployeesIdEm(employeeId);
	}
    
}
