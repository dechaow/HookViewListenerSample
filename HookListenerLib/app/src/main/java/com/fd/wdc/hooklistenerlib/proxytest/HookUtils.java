package com.fd.wdc.hooklistenerlib.proxytest;

import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Title:
 * Description:
 * Created DateTime:2017/7/31
 *
 * @author DeChao.Wang
 */

public class HookUtils {

//    private String test1 = "123333";
    private int test1 = 12;

    public static void hookOnClick(View view){
        try {
            //获得view class
            Class<?> mClassView = Class.forName("android.view.View");
            //获取getListenerInfo方法
            Method method = mClassView.getDeclaredMethod("getListenerInfo");
            //设置可以访问private方法和对象
            method.setAccessible(true);
            //调用view的方法,下面需要调用返会onclicklistener的值
            Object listenerInfoMethod = method.invoke(view);
            //找到View的内部类ListenerInfo 里面存着 onClickListener对象
            Class<?> viewClass = Class.forName("android.view.View$ListenerInfo");
            //获取mOnClickListener对象
            Field mClick = viewClass.getDeclaredField("mOnClickListener");
            mClick.setAccessible(true);
            //获取mOnClickListener的值
            View.OnClickListener clickListener = (View.OnClickListener) mClick.get(listenerInfoMethod);
            //自己代理的clickListener
            View.OnClickListener customClickListener = new CustomClickListener();
            //but i don't want this effect
            //动态代理
            View.OnClickListener clickListener1 = (View.OnClickListener) Proxy.newProxyInstance(clickListener.getClass().getClassLoader(),clickListener.getClass().getInterfaces(),new ProxyHandler(clickListener));
            //调用OnClick方法
            mClick.set(method,clickListener1);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private static class CustomClickListener implements View.OnClickListener{

        public CustomClickListener() {
        }

        @Override
        public void onClick(View v) {
            System.out.println("CustomClickListener.onClick");
        }
    }

}
