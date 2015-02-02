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
 * ExpenseList is used by a claim to 
 * hold all expenses for a claim 
 * this class mostly is used to get
 * the array list of expenses and
 * an expense to change the data of 
 * that expense 
 */

package ca.ualberta.cs.volivare;

import java.util.ArrayList;

public class ExpenseList {
	
	protected ArrayList<Expense> expenseList;
	
	public ExpenseList() {
		expenseList = new ArrayList<Expense>();
	}

	public ArrayList<Expense> getExpenseList() {
		return expenseList;
	}
	
	public void addExpense(Expense newexpense) {
		expenseList.add(newexpense);
	}
	
	//as user is input info the last element in the array 
	//is the current expense the user is input for
	//thus a get last expense is useful for updating data 
	public Expense getLastExpense() {
		return expenseList.get(expenseList.size()-1);
	}
	
	
}
