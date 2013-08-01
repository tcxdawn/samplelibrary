package com.library.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.UnsupportedEncodingException;
import android.app.FragmentManager;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.library.data.GlobleData;
import com.library.MainActivity;
import com.library.R;

@SuppressLint({ "ValidFragment", "NewApi" })
public class SearchFragment extends Fragment {
	public final String tag = this.getClass().getName();
	private EditText searchText;
	private String html;
	final String HTML1 = "?strSearchType=title&match_flag=forward&historyCount=1&strText=";
	final String HTML2 = "&doctype=ALL&displaypg=20&showmode=table&sort=CATA_DATE&orderby=desc&dept=ALL";
	private Button searchButton;
	private Button clearButton;
	String text = null;

	public SearchFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		Log.e(tag, "onCreate:" + text);

	}

	/* ================================================ */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.e(tag, "onCreateView:" + text);

		View view = inflater.inflate(R.layout.activity_main, null);

		// if (!GlobleData.hasInternet(this)) {
		// GlobleData.showToast(getActivity(), "网络连接失败，请检查网络！");
		// }
		searchButton = (Button) view.findViewById(R.id.searchButton);
		searchText = (EditText) view.findViewById(R.id.searchText);
		searchText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (s.length() > 0) {
					clearButton.setVisibility(View.VISIBLE);
				} else {
					clearButton.setVisibility(View.GONE);
				}
			}

			@Override
			public void afterTextChanged(Editable s) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
		clearButton = (Button) view.findViewById(R.id.clearBtn);
		clearButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				searchText.setText("");
			}
		});
		searchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					// 字符编码，尤其对中文字符
					String str = new String(searchText.getText().toString()
							.trim().getBytes(), "ISO-8859-1");
					if (str.equals("")) {
						GlobleData.showToast(getActivity(), "请输入检索内容!");
					} else {
						html = GlobleData.MAIN_URL + HTML1 + str + HTML2;
						// Intent intent = new Intent();
						// intent.setClass(this, ResultActivity.class);
						// intent.putExtra("URL", html);
						// startActivity(intent);
						FragmentManager fragmentManager = ((MainActivity) getActivity())
								.getFragmentManager();
						ContentFragment contentFragment = (ContentFragment) fragmentManager
								.findFragmentByTag("E");
						fragmentManager
								.beginTransaction()
								.replace(
										R.id.content,
										contentFragment == null ? new ResultFragment(
												html) : contentFragment, "E")
								.commit();

					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		});

		return view;

	}

	/* ================================================ */

	protected Context getApplicationContext() {
		return null;
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

	public ConnectivityManager getSystemService(String connectivityService) {
		return null;
	}

}
