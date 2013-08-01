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
import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.library.data.GlobleData;
import com.library.util.JsoupUtil;
import com.library.MainActivity;
import com.library.R;

@SuppressLint({ "ValidFragment", "NewApi" })
public class LoginFragment extends Fragment {
	private static final String MODE_APPEND = null;

	public final String tag = this.getClass().getName();

	private Button loginButton;
	private EditText loginNumber;
	private EditText loginKey;
	private CheckBox recd;
	private ProgressDialog mypDialog;
	private String info;
	// private Class<?> cls = StudentInfoActivity.class;
	String text = null;

	public LoginFragment() {
	}

	public LoginFragment(String text) {
		Log.e(tag, text);
		this.info = text;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		if(GlobleData.flag){
			Login login = new Login();
			login.onPostExecute(true);
		};
		Log.e(tag, "onCreate:" + text);
	}

	/* ========================================================= */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.e(tag, "onCreateView:" + text);
		// inflater the layout
		View view = inflater.inflate(R.layout.login, null);
		loginNumber = (EditText) view.findViewById(R.id.loginNumber);
		loginKey = (EditText) view.findViewById(R.id.loginKey);
		loginNumber.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				loginKey.setText("");
			}
		});

		recd = (CheckBox) view.findViewById(R.id.recd);
		// 记住密码
		SharedPreferences pre = getActivity().getSharedPreferences("lib", 0);
		String user = pre.getString("number", "");
		String pass = pre.getString("passwd", "");
		if (user != "") {
			loginNumber.setText(user);
			loginKey.setText(pass);
			recd.setChecked(true);
		}

		loginButton = (Button) view.findViewById(R.id.loginButton);
		// 登录
		loginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String number = loginNumber.getText().toString();
				String passwd = loginKey.getText().toString();
				if (recd.isChecked()) {
					SharedPreferences pre = getActivity().getSharedPreferences(
							"lib", 0);
					Editor edit = pre.edit();
					edit.putString("number", number);
					edit.putString("passwd", passwd);
					// edit.putString("cookie", l.getCookie());
					edit.commit();
				} else {
					SharedPreferences sp = getActivity().getSharedPreferences(
							"lib", 0);
					sp.edit().clear();
				}
				Login login = new Login();
				login.execute(number, passwd);

			}
		});
		
		
		return view;
	}

	/* ========================================================= */
	class Login extends AsyncTask<String, Integer, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
		}

		@Override
		protected Boolean doInBackground(String... params) {
			return JsoupUtil.loginUrl(params[0], params[1]);
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (result) {
				// 已经登陆
				GlobleData.flag = true;
				FragmentManager fragmentManager = ((MainActivity) getActivity())
						.getFragmentManager();
				ContentFragment contentFragment = (ContentFragment) fragmentManager
						.findFragmentByTag("F");

				if (info.equals("borrowed")) {

					fragmentManager
							.beginTransaction()
							.replace(
									R.id.content,
									contentFragment == null ? new BorrowedFragment()
											: contentFragment, "G")
							.commit();
				} else if (info.equals("student")) {

					fragmentManager
							.beginTransaction()
							.replace(
									R.id.content,
									contentFragment == null ? new StudentFragment()
											: contentFragment, "H")
							.commit();
				}
			
			} else {
				GlobleData.showToast(getActivity(), "登录失败，请检查账号和密码！");
			}
			super.onPostExecute(result);
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
