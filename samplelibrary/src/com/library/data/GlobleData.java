package com.library.data;

import java.util.List;

import org.apache.http.cookie.Cookie;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.library.R;
import com.library.fragment.SearchFragment;

public class GlobleData {

	public static final String LOGIN_URL = "http://libopac.fjnu.edu.cn/reader/redr_verify.php";
	public static final String BOOK_LST = "http://libopac.fjnu.edu.cn/reader/book_lst.php";
	public static final String MAIN_URL = "http://libopac.fjnu.edu.cn/opac/openlink.php";
	// 判断登录状态
	public static Boolean flag = false;

	public static List<Cookie> cookies;

	public static void showToast(Context c, String s) {
		Toast.makeText(c, s, Toast.LENGTH_LONG).show();

	}

	public static boolean hasInternet(Fragment fragment) {
		ConnectivityManager manager = (ConnectivityManager) ((SearchFragment) fragment)
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info == null || !info.isConnected()) {
			return false;
		}
		if (info.isRoaming()) {
			return true;
		}
		return true;
	}
}
