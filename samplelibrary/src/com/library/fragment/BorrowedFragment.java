package com.library.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.library.data.GlobleData;
import com.library.util.JsoupUtil;
import com.library.R;

@SuppressLint({ "ValidFragment", "NewApi" })
public class BorrowedFragment extends Fragment {
	public final String tag = this.getClass().getName();
	private ListView list;
	private Button button;
	String text = null;
	public BorrowedFragment() {
	}

	public BorrowedFragment(String text) {
		Log.e(tag, text);
		this.text = text;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		Log.e(tag, "onCreate:" + text);
	}
	/* ========================================================= */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.e(tag, "onCreateView:" + text);
		// inflater the layout
		View view = inflater.inflate(R.layout.borrowed, null);
		list = (ListView)view.findViewById(R.id.borrowed_section_list);
		BorrowedBook bb = new BorrowedBook();
		bb.execute();
		return view;
	}
	/* ========================================================= */
	class BorrowedBook extends
	AsyncTask<String, Integer, List<Map<String, Object>>> {

@Override
protected List<Map<String, Object>> doInBackground(String... params) {
	// TODO Auto-generated method stub

	return JsoupUtil.getBorrowedBook();
}

@Override
protected void onPostExecute(List<Map<String, Object>> result) {
	// TODO Auto-generated method stub
	System.out.println(result);
	if (result == null) {
		Toast.makeText(getActivity(), "您当前没有借阅，或者出错了！",
				Toast.LENGTH_LONG).show();
	} else {
		SimpleAdapter listAdapter = new SimpleAdapter(
				getActivity(), result,
				R.layout.borrowed_list, new String[] { "barcode",
						"booktitle", "borrowedDate", "paybackDate" },
				new int[] { R.id.barcodeTV, R.id.bookTitleTV,
						R.id.borrowedDateTV, R.id.paybackDateTV });
		list.setAdapter(listAdapter);
	}
	super.onPostExecute(result);
}

@Override
protected void onPreExecute() {
	super.onPreExecute();
}
}
	/* ========================================================= */
	
	@Override
	public void onDestroy() {
		Log.e(tag, "onDestroy:" + text);
		super.onDestroy();
	}

	@Override
	public void onDetach() {
		Log.e(tag, "onDetach:" + text);
		super.onDetach();
	}

	@Override
	public void onPause() {
		Log.e(tag, "onPause:" + text);
		super.onPause();
	}

	@Override
	public void onResume() {
		Log.e(tag, "onResume:" + text);
		super.onResume();
	}

	@Override
	public void onStart() {
		Log.e(tag, "onStart:" + text);
		super.onStart();
	}

	@Override
	public void onStop() {
		Log.e(tag, "onStop:" + text);
		super.onStop();
	}

}
