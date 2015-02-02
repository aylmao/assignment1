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
 * lets users add info to an expense
 */

package ca.ualberta.cs.volivare;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class ExpenseInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.expense_info_activity);
		//http://stackoverflow.com/questions/3913592/start-an-activity-with-a-parameter 02/02/2015
		Bundle b = getIntent().getExtras();
		int value = b.getInt("pos");
		
		//if editing a item..... 
		if (value != -1){
			//keep the text fields same 
			//issue was I could not figure out a way to load by Data or spinners 
			ClaimList cl = new ClaimList();
			//get current claim's expenses list object get the array list 
			ArrayList<Expense> elist = cl.getLastClaim().getExpenses().getExpenseList();
			//set description
			TextView description = (TextView) findViewById(R.id.expenseDescrip);
			description.setText(elist.get(value).getDescription());
			//set spent 
			TextView spent = (TextView) findViewById(R.id.spentInput);
			spent.setText(elist.get(value).getSpent());
			
			//set date of expense 
			DatePicker DatePick = (DatePicker) findViewById(R.id.ExpenseDate);
			DatePick.updateDate(elist.get(value).year,elist.get(value).month,elist.get(value).day);
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_info, menu);
		return true;
	}
	  
	public void addExpense(View v) {
		
		ClaimList cl = new ClaimList();
		//last expense in array is the current expense user is adding to claim
		Expense expense = cl.getLastClaim().getExpenses().getLastExpense();
		
		Bundle b = getIntent().getExtras();
		int value = b.getInt("pos");
		
		//if edit item, change a old item data 
		if (value != -1) {
			ArrayList<Expense> temp = cl.getLastClaim().getExpenses().getExpenseList();
			expense = temp.get(value);
		}
		
		//set date of expense 
		DatePicker DatePick = (DatePicker) findViewById(R.id.ExpenseDate);
		int year = DatePick.getYear();
		int day = DatePick.getDayOfMonth();
		int month = DatePick.getMonth();
		expense.day = day;
		expense.month = month;
		expense.year = year;
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, 0, 0, 0);
		expense.setDate(calendar.getTime());
		
		
		//set description 
		TextView description = (TextView) findViewById(R.id.expenseDescrip);
		expense.setDescription(description.getText().toString());
		
		//set spent 
		TextView spent = (TextView) findViewById(R.id.spentInput);
		expense.setSpent(spent.getText().toString());
		
		//set currency 
		Spinner currencypick = (Spinner) findViewById(R.id.CurrencySpinner);
		expense.setCurrency(currencypick.getSelectedItem().toString());
		
		//set category  
		Spinner categorypick = (Spinner) findViewById(R.id.CategorySpinner);
		expense.setCategory(categorypick.getSelectedItem().toString());
		
		//back to add make expenses view to end expense adds or add another expense 
		Intent intent = new Intent(ExpenseInfoActivity.this, MakeExpensesActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
