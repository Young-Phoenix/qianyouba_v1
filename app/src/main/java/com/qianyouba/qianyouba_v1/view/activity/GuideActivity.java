package com.qianyouba.qianyouba_v1.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.qianyouba.qianyouba_v1.R;
import com.qianyouba.qianyouba_v1.base.BaseActivity;
import com.qianyouba.qianyouba_v1.model.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends BaseActivity implements OnClickListener,OnPageChangeListener {


	private ViewPager vp;
	private ViewPagerAdapter vpAdapter;
	private List<View> views;
	private Button button;

	// 引导图片资源
	private static final int[] pics = { R.mipmap.guide1, R.mipmap.guide2,
			R.mipmap.guide3, R.mipmap.guide4 };

	// 底部小店图片
	private ImageView[] dots;

	// 记录当前选中位置
	private int currentIndex;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public int getLayoutId() {
		return R.layout.guide_activity;

	}

	@Override
	public void setupViews() {
		button = (Button) findViewById(R.id.button);
		vp = (ViewPager) findViewById(R.id.viewpager);
	}

	@Override
	public void initialized() {
		views = new ArrayList<View>();
		LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);

		// 初始化引导图片列表
		for (int i = 0; i < pics.length; i++) {
			ImageView iv = new ImageView(this);
			iv.setLayoutParams(mParams);
			iv.setImageResource(pics[i]);
			iv.setScaleType(ScaleType.FIT_XY);
			views.add(iv);
		}
		// 初始化Adapter
		vpAdapter = new ViewPagerAdapter(views);
		vp.setAdapter(vpAdapter);
		// 绑定回调
		//vp.setOnPageChangeListener(this);
		vp.addOnPageChangeListener(this);
		// button = (Button) findViewById(R.id.button);
		// 初始化底部小圆点
		initDots();
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(GuideActivity.this, MainActivity.class);
				GuideActivity.this.startActivity(intent);
				finish();
			}
		});
	}

	private void initDots() {
		LinearLayout ll = (LinearLayout) findViewById(R.id.ll_guide);
		dots = new ImageView[pics.length];
		// 循环取得小点图片
		for (int i = 0; i < pics.length; i++) {
			dots[i] = (ImageView) ll.getChildAt(i);
			dots[i].setEnabled(true);// 都设为灰色
			dots[i].setOnClickListener(this);
			dots[i].setTag(i);// 设置位置tag，方便取出与当前位置对应
		}
		currentIndex = 0;
		dots[currentIndex].setEnabled(false);// 设置为白色，即选中状态
	}

	/**
	 * 设置当前的引导页
	 */
	private void setCurView(int position) {
		if (position < 0 || position >= pics.length) {
			return;
		}
		vp.setCurrentItem(position);
	}

	/**
	 * 这只当前引导小点的位置
	 */
	private void setCurDot(int positon) {
		if (positon < 0 || positon > pics.length - 1 || currentIndex == positon) {
			return;
		}
		dots[positon].setEnabled(false);
		dots[currentIndex].setEnabled(true);
		currentIndex = positon;
	}

	// 当滑动状态改变时调用
	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	// 当当前页面被滑动时调用
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	// 当新的页面被选中时调用
	@Override
	public void onPageSelected(int arg0) {
		// 设置底部小点选中状太
		setCurDot(arg0);
		if (arg0 == 3) {
			button.setVisibility(View.VISIBLE);
		} else {
			button.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		int position = (Integer) v.getTag();
		setCurView(position);
		setCurDot(position);
	}

	@Override
	protected void onPause() {
		super.onPause();
		vp.removeOnPageChangeListener(this);
	}
}
