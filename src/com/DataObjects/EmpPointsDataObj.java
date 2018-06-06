package com.DataObjects;

public class EmpPointsDataObj {
	
	String empid;
	Long points;
	
	public EmpPointsDataObj(String empid, Long points)
	{
		this.empid = empid;
		this.points = points;
		
	}
	public Long getPoints()
	{
		return this.points;
	}
	public String getID()
	
	{
		return this.empid;
	}

}
