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
 * this class is used represent an expense 
 * it has a name since the user will want to know what the expense is
 * also a description, date, category, spent and currency 
 * this class will be used in another class called expenseList
 */

package ca.ualberta.cs.volivare;

import java.util.Calendar;
import java.util.Date;

public class Expense {
	protected String name;
	protected Date date;
	//issue with date editing
	//save the day/month/year to solve issue 
	protected int day = 0;
	protected int month = 0;
	protected int year = 0;
	protected String category;
	protected String description;
	protected String spent;
	protected String currency;
	
	public Expense(String name) {
		this.name = name;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSpent() {
		return spent;
	}
	public void setSpent(String string) {
		this.spent = string;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//format for list view 
	public String toString() {
		String s = getName();
		s += "\n"+"Spent: "+ getSpent() +" "+ getCurrency();
		s += "\n"+"Description: " + getDescription();
		s += "\n"+ "Category: " + getCategory();
		s += "\n"+"Date: "+getDate();
		return s;
	}
}
