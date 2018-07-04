package com.oude.bottomnavigationbar;

import android.app.*;
import android.os.*;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity 
{
	private DiceFragment mDiceFragment;
	private BookFragment mBookFragment;
	private ShopFragment mShopFragment;
	private CharacterFragmnet mCharacterFragmnet;
	private Fragment[] mFragments;
	private BottomNavigationBar bottomNavigationBar;
	private int index;//点击的fragment的下标
    private int currentTabIndex=0;//当前的fragment的下标
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
		initBottomNavigationBar();
        initListener();
       
    }
	
	private void initView() {

        mDiceFragment=new DiceFragment();
        mCharacterFragmnet=new CharacterFragmnet();
        mShopFragment=new ShopFragment();
        mBookFragment=new BookFragment();
		bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
		mFragments=new Fragment[]{mDiceFragment,mShopFragment,mCharacterFragmnet,mBookFragment};
    }
	
	private void initBottomNavigationBar() {
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING)
			.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
         //模式跟背景的设置都要在添加tab前面，不然不会有效果。
        bottomNavigationBar
		     .setActiveColor(R.color.colorPrimary)
			.setInActiveColor(R.color.grey)//默认未选择颜色
            .setBarBackgroundColor(R.color.white);//默认背景色;//选中颜色 图标和文字

        bottomNavigationBar
			.addItem(new BottomNavigationItem(R.drawable.ico_dice, "投骰"))
            .addItem(new BottomNavigationItem(R.drawable.ico_shop, "商店"))
            .addItem(new BottomNavigationItem(R.drawable.ico_character, "人物"))
            .addItem(new BottomNavigationItem(R.drawable.ico_book, "书籍"))
			.setFirstSelectedPosition(0)//设置默认选择的按钮
			.initialise();//所有的设置需在调用该方法前完成
    }
	
	private void initListener() {
        //设置默认选择的Fragment
        FragmentTransaction init = getSupportFragmentManager().beginTransaction();
        init.add(R.id.fl, mFragments[0]);
        init.show(mFragments[0]).commit();
        
		//设置lab点击事件
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
				@Override
				public void onTabSelected(int position) {
					switch (position){
						case 0:
							index = 0;
							break;
						case 1:
							index = 1;
							break;
						case 2:
							index = 2;
							break;
						case 3:
							index = 3;
							break;
					}
					if (currentTabIndex != index) {
						FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
						trx.hide(mFragments[currentTabIndex]);
						if (!mFragments[index].isAdded()) {
							trx.add(R.id.fl, mFragments[index]);
						}
						trx.show(mFragments[index]).commit();
					}
					currentTabIndex = index;
                    Toast.makeText(MainActivity.this,"onTabSelected"+position,Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onTabUnselected(int position) {
                    //Toast.makeText(MainActivity.this,"onTabUnselected"+position,Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onTabReselected(int position) {
                    Toast.makeText(MainActivity.this,"onTabReselected"+position,Toast.LENGTH_SHORT).show();

				}
			});
    }
	
    
}
