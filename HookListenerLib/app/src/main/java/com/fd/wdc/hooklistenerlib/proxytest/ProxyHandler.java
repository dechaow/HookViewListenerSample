package com.fd.wdc.hooklistenerlib.proxytest;

import android.view.View;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Title:代理hook onclick方法
 * Description:
 * Created DateTime:2017/7/31
 *
 * @author DeChao.Wang
 */

public class ProxyHandler implements InvocationHandler {



    private View.OnClickListener clickListener;

    public ProxyHandler(View.OnClickListener listener) {
        this.clickListener = listener;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //添加的逻辑
        //对onClick 方法 hook
        if (method.getName().equals("onClick")){

            System.out.println("hook toString method");

        }
//        method.invoke(clickListener,args)
        return null;
    }
}

