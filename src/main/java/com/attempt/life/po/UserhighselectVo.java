package com.attempt.life.po;

import java.util.List;

public class UserhighselectVo {
private String position;
private String minsalary;
private String maxsalary;
public String getPosition() {
	return position;
}
public void setPosition(String position) {
	this.position = position;
}



@Override
public String toString() {
	String positioninfo = "";
    String minsalaryinfo="";
    String maxsalaryinfo="";
    String cityinfo="";
	if (position !="" )
	{
		 positioninfo = "position=" + position ;
	}
	if (minsalary !="" )
	{
		 minsalaryinfo = "minsalary=" + minsalary ;
	}
	if (maxsalary !="" )
	{
		 maxsalaryinfo = "maxsalary=" + maxsalary ;
	}
	if (city !="" ||city !=null )
	{
		 cityinfo = "city=" + city ;
	}
	System.out.println(city+"cittittititi");
	return positioninfo+minsalaryinfo+maxsalaryinfo+cityinfo;
}
public boolean isnull() {
      if (position==null&&minsalary==null&&maxsalary==null&&city==null) {
    	  return true;
      }
      return false;
}
public boolean onlycity() {
	System.out.println(position);
	System.out.println(minsalary);
	System.out.println(maxsalary);
	System.out.println(city);
    if (position==""&&minsalary==""&&maxsalary=="") {
  	  if(city!=null&&city!="")
  	  {
  		return true;
  		  
  	  }
    	
    	return false;
    }
    return false;
}

public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getMinsalary() {
	return minsalary;
}
public void setMinsalary(String minsalary) {
	this.minsalary = minsalary;
}
public String getMaxsalary() {
	return maxsalary;
}
public void setMaxsalary(String maxsalary) {
	this.maxsalary = maxsalary;
}
private String city ;  
}
