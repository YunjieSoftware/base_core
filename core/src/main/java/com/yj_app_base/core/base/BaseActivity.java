package com.yj_app_base.core.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.yj_app_base.core.R;
import com.yj_app_base.core.interfaces.OnClickListener;
import com.yj_app_base.core.utils.ToastUtil;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　 ┏┓　  ┏┓
 * 　　┏┛┻━━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━代码无bug━━━━━━
 * <p>
 * Copyright (C), 2015-2020-01-01, 石家庄云捷软件科技有限公司
 *
 * @ProjectName: BaseActivity.java
 * @Package: com.yj_app_base.core.base
 * @ClassName: BaseActivity
 * @Description: TODO
 * @Author: Hu_Sir
 * @CreateDate: 2020-01-01 - 16:56
 * @UpdateUser: ?
 * @UpdateDate: 2020-01-01 - 16:56
 * @UpdateRemark: todo
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout rootLayout;

    private Toolbar toolbar;
    private RelativeLayout emptyLayout, errorLayout, nonetLayout, loadingLayout;
    private TextView resetTextView;
    private TextView resetButton;
    public LinearLayout toolbarBtns,emptyLayoutclick;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 这句很关键，注意是调用父类的方法
        super.setContentView(R.layout.activity_base);
        initToolbar();

    }


    @Override
    public void setContentView(int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
    }

    @Override
    public void setContentView(View view) {
        initview();
        initevent();

        rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initToolbar();
    }

    /**
     * 设置错误布局是否显示
     *
     * @param VISIBLE
     */
    public void setErrorLayoutShow(int VISIBLE) {
        errorLayout.setVisibility(VISIBLE);
        if (VISIBLE == View.VISIBLE) {
            emptyLayout.setVisibility(View.GONE);
            nonetLayout.setVisibility(View.GONE);
            loadingLayout.setVisibility(View.GONE);
        }

    }

    /**
     * 设置空布局是否显示
     *
     * @param VISIBLE
     */
    public void setEmptyLayoutShow(int VISIBLE) {
        emptyLayout.setVisibility(VISIBLE);
    }

    /**
     * 设置没有网络的布局是否显示
     *
     * @param VISIBLE
     */
    public void setNonetLayoutShow(int VISIBLE) {
        nonetLayout.setVisibility(VISIBLE);
    }

    /**
     * 设置没有网络的布局是否显示
     *
     * @param VISIBLE
     */
    public void setLoadingLayoutShow(int VISIBLE) {
        loadingLayout.setVisibility(VISIBLE);
    }

    /**
     * 设置🔙图标具体在setSupportActionBar 前面还是后面需要再测试
     * @param ids
     */
    public void setNavigationIcon(int ids) {
        toolbar.setNavigationIcon(ids);   ///这个是最左边的图标；
    }

    /**
     * 初始化toolbar
     */
    private void initToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    /**
     * 初始化view
     */
    private void initview() {
        toolbar = findViewById(R.id.toolbar);
        toolbarBtns=findViewById(R.id.layout);
        //空布局显示
        emptyLayout = findViewById(R.id.emptyLayout);
        emptyLayoutclick=findViewById(R.id.empty_layout);

        //错误布局
        errorLayout = findViewById(R.id.errorLayout);
        //无网络布局
        nonetLayout = findViewById(R.id.nonetLayout);
        resetTextView = findViewById(R.id.reset_textView);
        resetButton = findViewById(R.id.reset_button);
        //错误布局
        loadingLayout = findViewById(R.id.loadingLayout);

        //内容展示的布局
        rootLayout = findViewById(R.id.root_layout);
        if (rootLayout == null) {
            return;
        }


    }

    /**
     * 初始化监听器
     */
    private void initevent() {
        resetButton.setOnClickListener(this);
        emptyLayoutclick.setOnClickListener(new OnClickListener() {
            @Override
            public void onSingleClick(View view) {
                emptyClick();
            }
        });

    }

    public void emptyClick() {

    }


    /**
     * 从资源文件设置标题
     * @param titleId
     */
    public void setTitle(int titleId) {
        if (titleId != 0) {
            toolbar.setTitle(titleId);
        }
        toolbar.setVisibility(View.VISIBLE);
    }

    /**
     *
     * @param titleId
     */
    public void setSubtitle(int titleId) {
        if (titleId != 0) {
            toolbar.setSubtitle(titleId);
        }
        toolbar.setVisibility(View.VISIBLE);
    }

    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            getSupportActionBar().setTitle(title);
        }
        toolbar.setVisibility(View.VISIBLE);
    }

    public void setSubtitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            toolbar.setSubtitle(title);
        }
        toolbar.setVisibility(View.VISIBLE);
    }

    public void setTitleAndSubtitleWithBack(String title, String subtitle) {
        setSubtitle(subtitle);
        setTitleWithBack(title);
    }

    public void setTitleWithBack(String title) {
        setTitle(title);
        if (getSupportActionBar() != null) {
            // Enable the Up button
            if (YJBASEUI.getNavigationIcon()!=0){
                setNavigationIcon(YJBASEUI.getNavigationIcon());
            }else {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowTitleEnabled(true);
            }
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeBack();
            }
        });
    }

    /**
     * 返回按键的监听
     */
    private void homeBack() {
        finish();

    }


    @Override
    public void onClick(View v) {
        int ids = v.getId();

        if (ids == R.id.reset_button) {
            onNoNetClick();
        }


    }

    private void onNoNetClick() {
        nonetLayout.setVisibility(View.GONE);

    }

    /**
     * todo 弹toat
     * @param msg
     */
    public  void toast(String msg){
        ToastUtil.toast(msg);
    }
    /**
     * todo 弹toat
     * @param msg
     */
    public  void successToast(String msg){
        ToastUtil.successToast(msg);
    }
    /**
     * todo 弹toat
     * @param msg
     */
    public  void errorToast(String msg){
        ToastUtil.errorToast(msg);
    }
    /**
     * todo 弹toat
     * @param msg
     */
    public  void warningToast(String msg){
        ToastUtil.warningToast(msg);
    }
    /**
     * todo 弹toat
     * @param msg
     */
    public  void infoToast(String msg){
        ToastUtil.infoToast(msg);
    }

    /**
     * 检测 输入内容是否为空
     * @param editText
     * @return
     */

    public boolean checkTextViewEmpty(EditText editText){

        return TextUtils.isEmpty(editText.getText().toString().trim());
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishActivity();
    }


    public void finishActivity() {
        finish();
        overridePendingTransition(R.anim.left, R.anim.left_exit);
    }
    long mExitTime;

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            toast("再按一次退出问题系统");
            mExitTime = System.currentTimeMillis();
        } else {

            finish();
            System.exit(0);
        }
    }

}
