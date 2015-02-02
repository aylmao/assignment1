/*Copyright 2015 victor olivares
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 * used to represent a claim 
 */

package ca.ualberta.cs.volivare;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Claim implements Serializable{ 
	/**
	 * id 
	 */
	private static final long serialVersionUID = 5243385321125364973L;
	protected String name = null;
	protected Date startDate = new Date(0);
	//problems with date picker, 
	//this solves issue with changing picker date 
	//when editing 
	protected int sday = 0;
	protected int smonth = 0;
	protected int syear = 0;
	protected Date endDate = new Date(0);
	protected int eday = 0;
	protected int emonth = 0;
	protected int eyear = 0;
	
	protected String description = null;
	protected ExpenseList expenses = null;
	protected String status = null;
	
	public Claim() {
		expenses = new ExpenseList();
	}

	//when a new claim is made the claim name will be known 
	//set on create, not later 
	public Claim(String name) {
		this.name = name;
		expenses = new ExpenseList();
	}
	
	public ExpenseList getExpenses() {
		return expenses;
	}

	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {		
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	//format on how claims must look in list view 
	public String toString() {
		String s = getName();
		s += "\n\"" + getStartDate().toString();
		s += "\" to \"" + getEndDate().toString() +"\" \n";
		s += "Description: "+ getDescription();
		s += "\nStatus: "+ getStatus();
		return s;
	}

	public boolean lessThan(Claim claim) {
		if (this.syear <= claim.syear){
			if (this.smonth <= claim.smonth) {
				if (this.sday <= claim.sday) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} 
		return false;
	}
	
}
