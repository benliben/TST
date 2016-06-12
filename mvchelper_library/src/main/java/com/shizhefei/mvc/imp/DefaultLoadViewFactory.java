/*
Copyright 2015 shizhefei（LuckyJayce）

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.shizhefei.mvc.imp;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.shizhefei.mvc.ILoadViewFactory;
import com.shizhefei.view.mvc.R;
import com.shizhefei.view.vary.VaryViewHelper;

public class DefaultLoadViewFactory implements ILoadViewFactory {

	@Override
	public ILoadMoreView madeLoadMoreView() {
		return new LoadMoreHelper();
	}

	@Override
	public ILoadView madeLoadView() {
		return new LoadViewHelper();
	}

	private class LoadMoreHelper implements ILoadMoreView {

		protected TextView footView;

		protected OnClickListener onClickRefreshListener;
		protected FootViewAdder footViewHolder;
		@Override
		public void init(FootViewAdder footViewHolder, OnClickListener onClickRefreshListener) {
			footView = (TextView) footViewHolder.addFootView(R.layout.layout_listview_foot);
			this.onClickRefreshListener = onClickRefreshListener;
			this.footViewHolder = footViewHolder;
			showNormal();
		}

		@Override
		public void showNormal() {
			footView.setText("点击加载更多");
			footView.setOnClickListener(onClickRefreshListener);
		}

		@Override
		public void showLoading() {
			footView.setText("正在加载中..");
			footView.setOnClickListener(null);
		}

		@Override
		public void showFail(Exception exception) {
			footView.setText("加载失败，点击重新加载");
			footView.setOnClickListener(onClickRefreshListener);
		}

		@Override
		public void showNomore() {
			footView.setVisibility(View.GONE);
			footView.setText("已经加载完毕");
			footView.setOnClickListener(null);
			footViewHolder.removeFootView(footView);
		}

	}

	private class LoadViewHelper implements ILoadView {
		private VaryViewHelper helper;
		private OnClickListener onClickRefreshListener;
		private Context context;

		@Override
		public void init(View switchView, OnClickListener onClickRefreshListener) {
			this.context = switchView.getContext().getApplicationContext();
			this.onClickRefreshListener = onClickRefreshListener;
			helper = new VaryViewHelper(switchView);
		}

		@Override
		public void restore() {
			helper.restoreView();
		}

		@Override
		public void showLoading() {
			View layout = helper.inflate(R.layout.load_ing);
			TextView textView = (TextView) layout.findViewById(R.id.textView1);
			textView.setText("正在加载中...");
			helper.showLayout(layout);
		}

		@Override
		public void tipFail(Exception exception) {
			if (exception.getMessage()==null) {
				Toast.makeText(context, "网络加载失败", Toast.LENGTH_SHORT).show();
			} else if (exception instanceof NullPointerException) {
				Toast.makeText(context, exception.getMessage(), Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(context, "网络加载失败", Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		public void showFail(Exception exception) {
			View layout = helper.inflate(R.layout.load_error);
			TextView textView = (TextView) layout.findViewById(R.id.textView1);
			if (exception.getMessage()==null) {
				textView.setText("网络加载失败");
			} else if (exception instanceof NullPointerException){
				textView.setText(exception.getMessage());
			} else {
				textView.setText("网络加载失败");
			}
			Button button = (Button) layout.findViewById(R.id.btn_retime);
			button.setText("重试");
			button.setOnClickListener(onClickRefreshListener);
			helper.showLayout(layout);
		}

		@Override
		public void showEmpty() {
			View layout = helper.inflate(R.layout.load_empty);
			TextView textView = (TextView) layout.findViewById(R.id.textView1);
			textView.setText("暂无数据");
			Button button = (Button) layout.findViewById(R.id.btn_retime);
			button.setText("重试");
			button.setOnClickListener(onClickRefreshListener);
			helper.showLayout(layout);
		}
	}
}
