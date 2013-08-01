package com.library.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.library.data.StudentInfo;
import com.library.R;

@SuppressLint({ "ValidFragment", "NewApi" })
public class StudentFragment extends Fragment {
	public final String tag = this.getClass().getName();
	private TextView numberText, nameText, sumBorrowedText, telText,
			educationText, workPlaceText, sexText;
	String text = null;

	public StudentFragment() {
	}

	public StudentFragment(String text) {
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
		View view = inflater.inflate(R.layout.studentinfo, null);

		numberText = (TextView) view.findViewById(R.id.numberText);
		nameText = (TextView) view.findViewById(R.id.nameText);
		sexText = (TextView) view.findViewById(R.id.sexText);
		educationText = (TextView) view.findViewById(R.id.educationText);
		workPlaceText = (TextView) view.findViewById(R.id.workPlaceText);
		telText = (TextView) view.findViewById(R.id.cellphoneText);
		sumBorrowedText = (TextView) view.findViewById(R.id.gradeText);

		numberText.setText(StudentInfo.number);
		nameText.setText(StudentInfo.name);
		sexText.setText(StudentInfo.sex);
		telText.setText(StudentInfo.tel);
		educationText.setText(StudentInfo.education);
		workPlaceText.setText(StudentInfo.wockPlace);
		sumBorrowedText.setText(StudentInfo.sumBorrowed);
		int toExpire = Integer.parseInt(StudentInfo.toExpire);

		if (toExpire > 0) {

			Toast.makeText(getActivity(), "警告！", Toast.LENGTH_SHORT).show();
			Toast.makeText(getActivity(), "您有" + toExpire + "本书在5天内即将过期，请注意",
					Toast.LENGTH_SHORT).show();
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
