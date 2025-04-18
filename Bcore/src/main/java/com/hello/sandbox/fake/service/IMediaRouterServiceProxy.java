package com.hello.sandbox.fake.service;

import android.content.Context;
import black.android.media.BRIMediaRouterServiceStub;
import black.android.os.BRServiceManager;
import java.lang.reflect.Method;
import com.hello.sandbox.fake.hook.BinderInvocationStub;
import com.hello.sandbox.fake.hook.MethodHook;
import com.hello.sandbox.fake.hook.ProxyMethod;
import com.hello.sandbox.utils.MethodParameterUtils;

/** Created by BlackBox on 2022/3/1. */
public class IMediaRouterServiceProxy extends BinderInvocationStub {

  public IMediaRouterServiceProxy() {
    super(BRServiceManager.get().getService(Context.MEDIA_ROUTER_SERVICE));
  }

  @Override
  protected Object getWho() {
    return BRIMediaRouterServiceStub.get()
        .asInterface(BRServiceManager.get().getService(Context.MEDIA_ROUTER_SERVICE));
  }

  @Override
  protected void inject(Object baseInvocation, Object proxyInvocation) {
    replaceSystemService(Context.MEDIA_ROUTER_SERVICE);
  }

  @Override
  public boolean isBadEnv() {
    return false;
  }

  @ProxyMethod("registerClientAsUser")
  public static class registerClientAsUser extends MethodHook {

    @Override
    protected Object hook(Object who, Method method, Object[] args) throws Throwable {
      MethodParameterUtils.replaceFirstAppPkg(args);
      return method.invoke(who, args);
    }
  }

  @ProxyMethod("registerRouter2")
  public static class registerRouter2 extends MethodHook {

    @Override
    protected Object hook(Object who, Method method, Object[] args) throws Throwable {
      MethodParameterUtils.replaceFirstAppPkg(args);
      return method.invoke(who, args);
    }
  }
}
