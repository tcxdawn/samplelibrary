package com.library;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.library.fragment.ContentFragment;
import com.library.fragment.MenuFragment;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivity;

@SuppressLint("NewApi")
public class MainActivity extends SlidingActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("简单图书馆");
		setContentView(R.layout.frame_content);

		// set the Behind View
		setBehindContentView(R.layout.frame_menu);
		FragmentTransaction fragmentTransaction = getFragmentManager()
				.beginTransaction();
		MenuFragment menuFragment = new MenuFragment();
		fragmentTransaction.replace(R.id.menu, menuFragment);
		fragmentTransaction.replace(R.id.content,
				new ContentFragment("Welcome"), "Welcome");
		fragmentTransaction.commit();

		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidth(50);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffset(100);
		sm.setFadeDegree(0.35f);
		// 设置slding menu的几种手势模式
		// TOUCHMODE_FULLSCREEN 全屏模式，在content页面中，滑动，可以打开sliding menu
		// TOUCHMODE_MARGIN 边缘模式，在content页面中，如果想打开slding ,你需要在屏幕边缘滑动才可以打开slding
		// menu
		// TOUCHMODE_NONE 自然是不能通过手势打开啦
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		// 使用左上方icon可点，这样在onOptionsItemSelected里面才可以监听到R.id.home
		getActionBar().setDisplayHomeAsUpEnabled(true);// ActionBar(左上角)是否可以点击
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// toggle就是程序自动判断是打开还是关闭
			toggle();
			// getSlidingMenu().showMenu();// show menu
			// getSlidingMenu().showContent();//show content
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	protected void dialog() {
		AlertDialog.Builder builder = new Builder(this);
		builder.setMessage("确定要退出吗?");
		builder.setTitle("提示");
		builder.setPositiveButton("确认",
				new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						finish();
					}
				});
		builder.setNegativeButton("取消",
				new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		builder.create().show();

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			toggle();
		} else if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			dialog();
			return false;

		}
		return false;

	}
}
