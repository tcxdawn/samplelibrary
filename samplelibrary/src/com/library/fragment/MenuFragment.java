package com.library.fragment;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceFragment;
import com.library.MainActivity;
import com.library.R;

/**
 * menu fragment ，主要是用于显示menu菜单
 */
@SuppressLint("NewApi")
public class MenuFragment extends PreferenceFragment implements
		OnPreferenceClickListener {
	int index = -1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		// set the preference xml to the content view
		addPreferencesFromResource(R.xml.menu);
		// add listener
		findPreference("a").setOnPreferenceClickListener(this);
		findPreference("b").setOnPreferenceClickListener(this);
		findPreference("c").setOnPreferenceClickListener(this);
		findPreference("d").setOnPreferenceClickListener(this);
		findPreference("z").setOnPreferenceClickListener(this);
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		String key = preference.getKey();
		/* ==========================主页================================== */
		if ("z".equals(key)) {
			// if the content view is that we need to show . show directly
			if (index == 9) {
				((MainActivity) getActivity()).getSlidingMenu().toggle();
				return true;
			}
			// otherwise , replace the content view via a new Content fragment
			index = 9;

			FragmentTransaction fragmentTransaction = getFragmentManager()
					.beginTransaction();
			MenuFragment menuFragment = new MenuFragment();
			fragmentTransaction.replace(R.id.menu, menuFragment);
			fragmentTransaction.replace(R.id.content, new ContentFragment(
					"Welcome"), "Welcome");
			fragmentTransaction.commit();

		}
		/* ==========================图书搜索================================== */
		else if ("a".equals(key)) {
			// if the content view is that we need to show . show directly
			if (index == 0) {
				((MainActivity) getActivity()).getSlidingMenu().toggle();
				return true;
			}
			// otherwise , replace the content view via a new Content fragment
			index = 0;
			FragmentManager fragmentManager = ((MainActivity) getActivity())
					.getFragmentManager();
			ContentFragment contentFragment = (ContentFragment) fragmentManager
					.findFragmentByTag("A");
			fragmentManager
					.beginTransaction()
					.replace(R.id.content,
					// contentFragment == null ? new ContentFragment(
					// "This is A Menu") : contentFragment, "A")
							contentFragment == null ? new SearchFragment()
									: contentFragment, "A").commit();
		}
		/* =======================读者信息=========================== */
		else if ("b".equals(key)) {
			if (index == 1) {
				((MainActivity) getActivity()).getSlidingMenu().toggle();
				return true;
			}
			index = 1;
			FragmentManager fragmentManager = ((MainActivity) getActivity())
					.getFragmentManager();
			ContentFragment contentFragment = (ContentFragment) fragmentManager
					.findFragmentByTag("B");
			fragmentManager
					.beginTransaction()
					.replace(
							R.id.content,
							contentFragment == null ? new LoginFragment(
									"student") : contentFragment, "B").commit();
		}
		/* =====================当前借阅==================================== */
		else if ("c".equals(key)) {

			if (index == 2) {
				((MainActivity) getActivity()).getSlidingMenu().toggle();
				return true;
			}
			index = 2;
			FragmentManager fragmentManager = ((MainActivity) getActivity())
					.getFragmentManager();
			ContentFragment contentFragment = (ContentFragment) fragmentManager
					.findFragmentByTag("C");
			fragmentManager
					.beginTransaction()
					.replace(
							R.id.content,
							contentFragment == null ? new LoginFragment(
									"borrowed") : contentFragment, "C")
					.commit();
		} /* ====================关于======================================= */
		else if ("d".equals(key)) {

			if (index == 3) {
				((MainActivity) getActivity()).getSlidingMenu().toggle();
				return true;
			}
			index = 3;
			FragmentManager fragmentManager = ((MainActivity) getActivity())
					.getFragmentManager();
			SearchFragment contentFragment = (SearchFragment) fragmentManager
					.findFragmentByTag("D");
			fragmentManager
					.beginTransaction()
					.replace(
							R.id.content,
							contentFragment == null ? new AboutFragment(
									"本软件目前可实现的功能:\n图书检索、个人信息查看、"
											+ "当前借阅信息查看、五天内图书到期提醒等功能。"
											+ "\n\n预备实现功能图书续借、图书具体信息查看、"
											+ "以及分享等功能，谢谢大家支持！ "
											+ "\n\n这是一个练习作品，" + "不足之处还请多多指正！ "
											+ "希望对此感兴趣的朋友可以联系我，大家共同交流，一起进步！\n"
											+ "QQ:\n新浪微博") : contentFragment,
							"D")

					.commit();
		}
		// anyway , show the sliding menu
		((MainActivity) getActivity()).getSlidingMenu().toggle();
		return false;
	}
}
