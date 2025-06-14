package com.example.demo.Model;

import lombok.Data;

@Data
public class InventoryModel {
	private int InvenID;
	private int ProductID;
	private int Stocklevel;
	private int lowStockAlert;

}