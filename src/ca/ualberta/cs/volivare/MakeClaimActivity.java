/*
 * activity that allows users to add info to claim 
 */

package ca.ualberta.cs.volivare;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MakeClaimActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_make_claim);
		
		
		//http://stackoverflow.com/questions/3913592/start-an-activity-with-a-parameter 02/02/2015
		Bundle b = getIntent().getExtras();
		int value = b.getInt("pos");
		
		//if editing a claim..... 
		if (value != -1){ 
			//issue was I could not figure out a way to load by Data or spinners 
			//saved the day month year are ints to solve it 
			ArrayList<Claim> elist = ClaimList.getClaimList();
			//set start date of claim
			DatePicker DatePick = (DatePicker) findViewById(R.id.startDatePick);
			DatePick.updateDate(elist.get(value).syear,elist.get(value).smonth,elist.get(value).sday);
			//set end date of claim
			DatePick = (DatePicker) findViewById(R.id.endDatePick);
			DatePick.updateDate(elist.get(value).eyear,elist.get(value).emonth,elist.get(value).eday);
			//set description 
			TextView description = (TextView) findViewById(R.id.DescriptionText);
			description.setText(elist.get(value).getDescription());
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.make_claim, menu);
		return true;
	}
	
	public void addExpenses(View v ) {
		ClaimList cl = new ClaimList();
		
		//set start date 
		DatePicker startDatePick = (DatePicker) findViewById(R.id.startDatePick);
		int year = startDatePick.getYear();
		int day = startDatePick.getDayOfMonth();
		int month = startDatePick.getMonth();
		cl.getLastClaim().sday = day;
		cl.getLastClaim().smonth = month;
		cl.getLastClaim().syear = year;
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, 0, 0, 0);
		cl.getLastClaim().setStartDate(calendar.getTime());

		//set end date 
		DatePicker endDatePick = (DatePicker) findViewById(R.id.endDatePick);
		year = endDatePick.getYear();
		day = endDatePick.getDayOfMonth();
		month = endDatePick.getMonth();
		cl.getLastClaim().eday = day;
		cl.getLastClaim().emonth = month;
		cl.getLastClaim().eyear = year;
		calendar.set(year,month,day,0,0,0);
		cl.getLastClaim().setEndDate(calendar.getTime());
		
		//set description 
		TextView description = (TextView) findViewById(R.id.DescriptionText);
		cl.getLastClaim().setDescription(description.getText().toString());
		
		//move to expenses activity 
		Intent intent = new Intent(MakeClaimActivity.this , MakeExpensesActivity.class);
		//http://stackoverflow.com/questions/3913592/start-an-activity-with-a-parameter 02/02/2015
		Bundle place= new Bundle();
		place.putInt("pos",-1);
		intent.putExtras(place); 
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
