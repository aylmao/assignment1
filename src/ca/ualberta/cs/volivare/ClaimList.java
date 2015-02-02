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
 * used by a list view to show claims 
 * only one claim list will be in use 
 * thus have a static claim list 
 */

package ca.ualberta.cs.volivare;

import java.io.Serializable;
import java.util.ArrayList;

public class ClaimList implements Serializable{
	/**
	 * id name 
	 */
	private static final long serialVersionUID = 5603523228799327439L;
	//only one claim list will be in app 
	private static ArrayList<Claim> claimList = null;
	
	static public ArrayList<Claim> getClaimList() {
		if (claimList == null){
			claimList = new ArrayList<Claim>();
		}
		return claimList;
	}
	
	public void addClaim(Claim claim) {
		getClaimList().add(claim);
	}
	
	//current claim user is place info into 
	public Claim getLastClaim() {
		return claimList.get(claimList.size()-1);
	}

	public static void setList(ArrayList<Claim> list) {
		claimList = list;
	}
	
	//need to add a remove claim method
}
