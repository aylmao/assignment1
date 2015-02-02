package ca.ualberta.cs.volivare;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

public class ClaimListManager {
	Context context;
	static final String prefFile = "ClaimList";
	static final String clkey = "ClaimList";
	public ClaimListManager(Context context) {
		this.context = context;
	}
	
	public ClaimList loadClaimList() throws StreamCorruptedException, IOException, ClassNotFoundException {
		SharedPreferences settings = context.getSharedPreferences(prefFile, context.MODE_PRIVATE);
		String claimListData = settings.getString("ClaimList", "");
		if (claimListData.equals("")) {
			return new ClaimList();
		} else {
			return claimListFromString(claimListData);
		}
	}
	
	static public ClaimList claimListFromString(String claimListData) throws StreamCorruptedException, IOException, ClassNotFoundException {
		ByteArrayInputStream bo = new ByteArrayInputStream(Base64.decode(claimListData, Base64.DEFAULT));
		ObjectInputStream oi = new ObjectInputStream(bo);
		return (ClaimList)oi.readObject();
	}

	public void saveClaimList(ClaimList cl) throws IOException {
		SharedPreferences settings = context.getSharedPreferences(prefFile, context.MODE_PRIVATE);
		Editor editor = settings.edit();
		try {
			editor.putString(clkey, claimListToString(cl));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		editor.commit();
	}

	static public String claimListToString(ClaimList cl) throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(cl);
		oo.flush();
		oo.close();
		byte bytes[] = bo.toByteArray();
		return Base64.encodeToString(bytes, Base64.DEFAULT);
	}
}
