package com.library.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import java.util.Map;
import android.os.AsyncTask;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.library.util.JsoupUtil;
import com.library.R;

@SuppressLint({ "ValidFragment", "NewApi" })
public class ResultFragment extends Fragment {
	public final String tag = this.getClass().getName();
	private ListView listView;
	private String html;
	private TextView sumNumber;
	private TextView pageNumber;
	private Button nextButton;
	private Button preButton;
	String text = null;

	public ResultFragment() {
	}

	public ResultFragment(String text) {
		Log.e(tag, text);
		this.html = text;
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
		View view = inflater.inflate(R.layout.search, null);
		listView = (ListView) view.findViewById(R.id.search_section_list);
		System.out.println(html);
		// 默认的第一次搜索
		new LoadBookInfo().execute(html);
		// 当第二次检索时，应将之前的信息清空！
		JsoupUtil.clearInfo();
		// 总页数
		sumNumber = (TextView) view.findViewById(R.id.sum_number);
		pageNumber = (TextView) view.findViewById(R.id.page_number);
		// 上一页、下一页按钮
		nextButton = (Button) view.findViewById(R.id.next);
		preButton = (Button) view.findViewById(R.id.pre);
		preButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (JsoupUtil.page <= 1) {
					Toast.makeText(getActivity(), "已经是第一页了！",
							Toast.LENGTH_SHORT).show();
				} else {
					new LoadBookInfo().execute(JsoupUtil.preUrl);
					JsoupUtil.page--;
				}
			}
		});
		nextButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (JsoupUtil.page >= Math.ceil(JsoupUtil.sumNumber / 20)) {
					Toast.makeText(getActivity(), "已经是最后一页了！",
							Toast.LENGTH_SHORT).show();
				} else {
					new LoadBookInfo().execute(JsoupUtil.nextUrl);
					JsoupUtil.page++;
				}
			}
		});
		return view;
	}

	class LoadBookInfo extends
			AsyncTask<String, ListView, List<Map<String, Object>>> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			System.out.println("onPreExecute");
			super.onPreExecute();
		}

		@Override
		protected List<Map<String, Object>> doInBackground(String... params) {
			// TODO Auto-generated method stub
			System.out.println("doInBackground");
			// System.out.println("html:" + html);
			// System.out.println(params[0]);
			return JsoupUtil.searchBook(params[0]);
		}

		@Override
		protected void onPostExecute(List<Map<String, Object>> result) {
			// TODO Auto-generated method stub
			System.out.println("onPostExecute");
			// 显示总数、页码及图书列表
			if (result == null) {
				Toast.makeText(getActivity(), "本馆没有您检索的纸本馆藏书目",
						Toast.LENGTH_LONG).show();
			} else {
				sumNumber.setText(JsoupUtil.sumNumber.toString());
				pageNumber.setText(JsoupUtil.pageNumber);
				SimpleAdapter listAdapter = new SimpleAdapter(getActivity(),
						result, R.layout.book_list, new String[] { "bookTitle",
								"bookAuthor", "bookCallno", "bookPublisher" },
						new int[] { R.id.bookTitle, R.id.bookAuthor,
								R.id.bookCallno, R.id.bookPublisher });
				listView.setAdapter(listAdapter);
				listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						System.out.println(arg0);
						System.out.println(arg1);
						System.out.println(arg2);
						System.out.println(arg3);
					}
				});
				super.onPostExecute(result);
			}
		}
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
