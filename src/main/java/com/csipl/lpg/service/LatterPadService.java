package com.csipl.lpg.service;

import java.util.List;
import java.util.Optional;

import com.csipl.lpg.model.Employee;
import com.csipl.lpg.model.LatterPad;

public interface LatterPadService {
   
	public Object generateLatterPad();

	public List<LatterPad> getAllLatterPad();

	public void addDetals(LatterPad latterpad);

	public LatterPad getLatterPad(long id);

	public List<LatterPad> findAllLatterPad();

}
