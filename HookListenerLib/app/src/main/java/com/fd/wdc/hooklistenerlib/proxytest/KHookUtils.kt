package com.fd.wdc.hooklistenerlib.proxytest

import android.content.DialogInterface
import android.view.View
import java.lang.reflect.Proxy

/**
 * Title:对比一下kotlin和java的代码差别
 * Description:
 * Created DateTime:2017/8/1
 * @author DeChao.Wang
 */
class KHookUtils{
    //伴生对象中的方法和对象可以通过类名直接调用
    companion object{
        fun hookOnClick(view : View){

            var viewClass = Class.forName("android.view.View")

            var listenerMethod = viewClass.getDeclaredMethod("getListenerInfo")

            listenerMethod.isAccessible = true

            listenerMethod.invoke(view)

            var infoClass = Class.forName("android.view.View\$ListenerInfo")

            var clickField =  infoClass.getDeclaredField("mOnClickListener")

            clickField.isAccessible = true

            var clickListener : View.OnClickListener = clickField.get(listenerMethod) as View.OnClickListener

            var reClick :View.OnClickListener  = Proxy.newProxyInstance(clickListener.javaClass.classLoader,clickListener.javaClass.interfaces,ProxyHandler(clickListener)) as View.OnClickListener

            clickField.set(listenerMethod,reClick)
        }
    }
}
