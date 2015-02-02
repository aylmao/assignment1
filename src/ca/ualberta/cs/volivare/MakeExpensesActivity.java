/*
 * shows users expenses for a claim 
 * lets users add expenses 
 */

package ca.ualberta.cs.volivare;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MakeExpensesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_expense);
		
		//upon display update listview of expenses 
		final ListView listview = (ListView) findViewById(R.id.AddedExpensesListView);
		final ClaimList cl = new ClaimList();
		
		//get the expenselist object of current claim then get the expense array 
		//https://www.youtube.com/watch?v=7zKCuqScaRE 02/02/2015 
		final ArrayList<Expense> list = cl.getLastClaim().getExpenses().getExpenseList();
		ArrayAdapter<Expense> claimAdapter = new ArrayAdapter<Expense>(this, android.R.layout.simple_list_item_1, list);
		listview.setAdapter(claimAdapter);
		
		//edit an expense 
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent intent = new Intent(MakeExpensesActivity.this , ExpenseInfoActivity.class);
				
				TextView name = (TextView) findViewById(R.id.ExpenseNameText);
				//if name was entered make the change
				if ( !name.getText().toString().equals("")) {
					ClaimList cl = new ClaimList();
					//get current claim's expenses list object get the array list 
					ArrayList<Expense> elist = cl.getLastClaim().getExpenses().getExpenseList();
					//set current item in list selected to name enter
					elist.get(position).setName(name.getText().toString());
				}
				
				//http://stackoverflow.com/questions/3913592/start-an-activity-with-a-parameter 02/02/2015
				Bundle place= new Bundle();
				place.putInt("pos",position);
				intent.putExtras(place); 
				startActivity(intent);
			}
			
		} );
		
		//delete an expense  
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				//already ordered remove the position 
				list.remove(position);
				
				//update list view 
				//a change was made 
				//get the current claims expenses and list them 
				ArrayList<Expense> list = cl.getLastClaim().getExpenses().getExpenseList();
				ArrayAdapter<Expense> claimAdapter = new ArrayAdapter<Expense>(MakeExpensesActivity.this, android.R.layout.simple_list_item_1, list);
				listview.setAdapter(claimAdapter);
				return false;
			}
		} );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.make_expenses, menu);
		return true;
	}

	public void addAExpense(View v) {
		
		//add  expense name 
		ClaimList cl = new ClaimList();
		ExpenseList expenses = cl.getLastClaim().getExpenses();
		TextView name = (TextView) findViewById(R.id.ExpenseNameText);
		expenses.addExpense(new Expense(name.getText().toString()));
		
		
		//set category  
		Spinner status = (Spinner) findViewById(R.id.StatusSpinner);
		cl.getLastClaim().setStatus(status.getSelectedItem().toString());

		//http://stackoverflow.com/questions/3913592/start-an-activity-with-a-parameter 02/02/2015
		Bundle place= new Bundle();
		place.putInt("pos",-1); 
		Intent intent = new Intent(MakeExpensesActivity.this , ExpenseInfoActivity.class);
		intent.putExtras(place);
		startActivity(intent);
	}
	public void submitClaim(View v) {
		//set category  
		Spinner status = (Spinner) findViewById(R.id.StatusSpinner);
		ClaimList cl = new ClaimList();
		cl.getLastClaim().setStatus(status.getSelectedItem().toString());
		
		Intent intent = new Intent(MakeExpensesActivity.this , MainActivity.class);
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
