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

import com.library.R;

@SuppressLint({ "ValidFragment", "NewApi" })
public class ContentFragment extends Fragment {
	public final String tag = this.getClass().getName();
	String text = null;

	public ContentFragment() {
	}

	public ContentFragment(String text) {
		Log.e(tag, text);
		this.text = text;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		Log.e(tag, "onCreate:" + text);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.e(tag, "onCreateView:" + text);
		// inflater the layout
		View view = inflater.inflate(R.layout.fragment_text, null);

		TextView textView = (TextView) view.findViewById(R.id.textView);
		if (!TextUtils.isEmpty(text)) {
			textView.setText(text);
		}
		return view;
	}

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
