package com.qianyouba.qianyouba_v1.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.qianyouba.qianyouba_v1.R;
import com.qianyouba.qianyouba_v1.base.BaseActivity;
import com.qianyouba.qianyouba_v1.common.LogUtils;
import com.qianyouba.qianyouba_v1.common.SPUtils;
import com.qianyouba.qianyouba_v1.presenter.InitPresenter;
import com.qianyouba.qianyouba_v1.util.Constants;
import com.qianyouba.qianyouba_v1.view.InitInfo;

public class IndexActivity extends BaseActivity implements InitInfo{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public int getLayoutId() {
		return R.layout.index_activity;
	}

	@Override
	public void setupViews() {

	}
private InitPresenter initPresenter;
	@Override
	public void initialized() {
		initPresenter = new InitPresenter(this);
		initPresenter.checkUpdate();
		/*new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if ((Boolean) SPUtils.get(IndexActivity.this, Constants.FIRST_START, true)) {
					// 将登录标志位设置为false，下次登录时不在显示首次登录界面
					SPUtils.put(IndexActivity.this, Constants.FIRST_START, false);
					Intent intent = new Intent();
					intent.setClass(IndexActivity.this,
							GuideActivity.class);
					IndexActivity.this.startActivity(intent);
					IndexActivity.this.finish();
				} else {
					Intent intent = new Intent();
					intent.setClass(IndexActivity.this, MainActivity.class);
					IndexActivity.this.startActivity(intent);
					IndexActivity.this.finish();

				}
			}
		}, Constants.SPLASH_DISPLAY_LENGHT);*/
	}

	@Override
	public void setInitResult(String str) {
		LogUtils.e(str);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if ((Boolean) SPUtils.get(IndexActivity.this, Constants.FIRST_START, true)) {
					// 将登录标志位设置为false，下次登录时不在显示首次登录界面
					SPUtils.put(IndexActivity.this, Constants.FIRST_START, false);
					Intent intent = new Intent();
					intent.setClass(IndexActivity.this,
							GuideActivity.class);
					IndexActivity.this.startActivity(intent);
					IndexActivity.this.finish();
				} else {
					Intent intent = new Intent();
					intent.setClass(IndexActivity.this, MainActivity.class);
					IndexActivity.this.startActivity(intent);
					IndexActivity.this.finish();

				}
			}
		}, Constants.SPLASH_DISPLAY_LENGHT);
		/*if ((Boolean) SPUtils.get(IndexActivity.this, Constants.FIRST_START, true)) {
			// 将登录标志位设置为false，下次登录时不在显示首次登录界面
			SPUtils.put(IndexActivity.this, Constants.FIRST_START, false);
			Intent intent = new Intent();
			intent.setClass(IndexActivity.this,
					GuideActivity.class);
			IndexActivity.this.startActivity(intent);
			IndexActivity.this.finish();
		} else {
			Intent intent = new Intent();
			intent.setClass(IndexActivity.this, MainActivity.class);
			IndexActivity.this.startActivity(intent);
			IndexActivity.this.finish();
		}*/
	}
}
