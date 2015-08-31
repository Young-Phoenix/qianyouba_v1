package com.qianyouba.qianyouba_v1.view.activity;

import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.qianyouba.qianyouba_v1.R;
import com.qianyouba.qianyouba_v1.base.BaseActivity;
import com.qianyouba.qianyouba_v1.common.ToastUtils;
import com.qianyouba.qianyouba_v1.view.fragment.ForumFragment;
import com.qianyouba.qianyouba_v1.view.fragment.GroupFragment;
import com.qianyouba.qianyouba_v1.view.fragment.HomeFragment;
import com.qianyouba.qianyouba_v1.view.fragment.MeFragment;

public class MainActivity extends BaseActivity {
    // 定义数组来存放Fragment界面
    private Class fragmentArray[] = {HomeFragment.class, GroupFragment.class,ForumFragment.class,MeFragment.class};

    // 定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.tab_home_btn,R.drawable.tab_group_btn,R.drawable.tab_forum_btn,
            R.drawable.tab_me_btn};
    private String mTextviewArray[];
    private String mTabTag[];
    private FragmentTabHost mTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void setupViews() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.fl_fragmentContent);
        mTextviewArray = getResources().getStringArray(R.array.tab_menu);
        mTabTag = getResources().getStringArray(R.array.tab_tag);
        int count = fragmentArray.length;
        for (int i = 0; i < count; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTabTag[i]).setIndicator(
                    getTabItemView(i));
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            // mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.home_btn_bg);
        }
    }
    /**
     * 初始化tab选项卡视图
     *
     * @param index
     * @return
     */
    private View getTabItemView( int index) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item_view, null);
        /*
         * ImageView imageView = (ImageView) view.findViewById(R.id.iamge_view);
		 * imageView.setImageResource(mImageViewArray[index]);
		 */
        TextView textView = (TextView) view.findViewById(R.id.tv_tab);
        textView.setText(mTextviewArray[index]);
        Drawable drawable = getResources().getDrawable(mImageViewArray[index]);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
        return view;
    }
    @Override
    public void initialized() {

    }

    private long temp = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - temp > 2000) {
            ToastUtils.showShort(this, R.string.press_again_exit);
            temp = System.currentTimeMillis();
        } else {
            this.finish();
            System.exit(0);
        }
    }
}
