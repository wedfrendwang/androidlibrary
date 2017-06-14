package com.welive.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;


/**
 * 创建自己的Activity父类
 *
 * AppCompatActivity是完全继承FragmentActivity，所以即使在代码中使用ViewPager的时候，也是一样的
 *
 * Created by welive on 2017/6/13.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        //对于使用注解的Activity，相应的使用布局

    }
}
