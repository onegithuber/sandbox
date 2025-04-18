package com.hello.sandbox.fake.service;

import android.os.IInterface;
import android.view.WindowManager;
import com.hello.sandbox.SandBoxCore;
import com.hello.sandbox.fake.hook.BinderInvocationStub;
import com.hello.sandbox.fake.hook.MethodHook;
import com.hello.sandbox.fake.hook.ProxyMethod;
import java.lang.reflect.Method;

/** Created by Milk on 4/6/21. * ∧＿∧ (`･ω･∥ 丶　つ０ しーＪ 此处无Bug */
public class IWindowSessionProxy extends BinderInvocationStub {
  public static final String TAG = "WindowSessionStub";

  private IInterface mSession;

  public IWindowSessionProxy(IInterface session) {
    super(session.asBinder());
    mSession = session;
  }

  @Override
  protected Object getWho() {
    return mSession;
  }

  @Override
  protected void inject(Object baseInvocation, Object proxyInvocation) {}

  @Override
  public boolean isBadEnv() {
    return false;
  }

  @Override
  public Object getProxyInvocation() {
    return super.getProxyInvocation();
  }

  @ProxyMethod("addToDisplay")
  public static class AddToDisplay extends MethodHook {
    @Override
    protected Object hook(Object who, Method method, Object[] args) throws Throwable {
      for (Object arg : args) {
        if (arg == null) {
          continue;
        }
        if (arg instanceof WindowManager.LayoutParams) {
          ((WindowManager.LayoutParams) arg).packageName = SandBoxCore.getHostPkg();
        }
      }
      return method.invoke(who, args);
    }
  }

  @ProxyMethod("addToDisplayAsUser")
  public static class AddToDisplayAsUser extends AddToDisplay {}
}
