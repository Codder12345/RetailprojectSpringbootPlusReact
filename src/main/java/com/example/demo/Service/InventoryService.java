package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.InventoryModel;
import com.example.demo.Repository.InventoryRepo;

@Service("invenservice")
public class InventoryService {
	
	@Autowired
	InventoryRepo inventotyrepo;
	public boolean createInventory(InventoryModel model)
	{
		return false;
	}
	
	public List<InventoryModel> getInventoryInfo()
	{
		return inventotyrepo.getAllInventoryInfo();
	}
	
	public InventoryModel searchInventoryById(int id)
	{
		return inventotyrepo.searchInventoryById(id);
	}
	
	public InventoryModel deleteInventoryById(int id)
	{
		return inventotyrepo.deleteInventoryByID(id);
	}

}
 