package com.DataObjects;

public class pollDetailsDataObj {
	
	private long pollID;
	private String QuesDesc;
	
	
	public pollDetailsDataObj( long pollID, String QuesDesc)
	{
		this.QuesDesc = QuesDesc;
		this.pollID = pollID;
		
	}
	public long getPollID()
	{
		return this.pollID;
	}
	public String getQuesDescrition()
	
	{
		return this.QuesDesc;
	}

}
