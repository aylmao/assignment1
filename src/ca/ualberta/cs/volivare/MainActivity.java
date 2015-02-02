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
 * activity that shows user info on claims
 * add claims also 
 */

package ca.ualberta.cs.volivare;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//upon display update listview 
		//https://www.youtube.com/watch?v=7zKCuqScaRE 02/02/2015 
		final ListView listview = (ListView) findViewById(R.id.ClaimListView);
		Collection<Claim> claims = ClaimList.getClaimList();
		ArrayList<Claim> list = new ArrayList<Claim>(claims);
		
		//sorting 
		//http://stackoverflow.com/questions/18441846/how-sort-a-arraylist-in-java 02/02/2015
		Collections.sort(list, new Comparator<Claim>() {

			@Override
			public int compare(Claim lhs, Claim rhs) {
				return lhs.startDate.compareTo(rhs.startDate);
			}
	
		});
		
		//saving order
		ClaimList.setList(list);
		ArrayAdapter<Claim> claimAdapter = new ArrayAdapter<Claim>(this, android.R.layout.simple_list_item_1, list);
		listview.setAdapter(claimAdapter);
		
		//edit a claim
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Intent intent = new Intent(MainActivity.this , MakeClaimActivity.class);
				//get claim 
				Claim claim = ClaimList.getClaimList().get(position);
				TextView name = (TextView) findViewById(R.id.addClaimNameText);
				//if name was entered make the change
				if ( !name.getText().toString().equals("")) {
					//change name of claim 
					claim.setName(name.getText().toString());
				}
				
				ClaimList.getClaimList().add(claim);
				ClaimList.getClaimList().remove(position);
				
				//http://stackoverflow.com/questions/3913592/start-an-activity-with-a-parameter 02/02/2015
				Bundle place= new Bundle();
				place.putInt("pos",position);
				intent.putExtras(place); 
				startActivity(intent);
			}
			
		} );
		
		//delete a claim 
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				ArrayList<Claim> list = ClaimList.getClaimList();
				list.remove(position);
				
				ArrayAdapter<Claim> claimAdapter = new ArrayAdapter<Claim>(MainActivity.this, android.R.layout.simple_list_item_1, list);
				listview.setAdapter(claimAdapter);
				return false;
			}
		} );
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void addClaim(View v ) {
		
		//create the claim with name user has picked as input 
		Toast.makeText(this, "making claim", Toast.LENGTH_SHORT).show();
		ClaimList cl = new ClaimList();
		EditText textview = (EditText) findViewById(R.id.addClaimNameText);
		cl.addClaim(new Claim(textview.getText().toString()));
		
		//move to claim info 
		Intent intent = new Intent(MainActivity.this , MakeClaimActivity.class);
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
		/*if (id == R.id.action_settings) {
			return true;
		}*/
		return super.onOptionsItemSelected(item);
	}
}
