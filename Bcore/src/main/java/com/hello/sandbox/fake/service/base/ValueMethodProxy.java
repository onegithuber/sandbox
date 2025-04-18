package com.hello.sandbox.fake.service.base;

import java.lang.reflect.Method;
import com.hello.sandbox.fake.hook.MethodHook;

public class ValueMethodProxy extends MethodHook {

  Object mValue;
  String mName;

  public ValueMethodProxy(String name, Object value) {
    mValue = value;
    mName = name;
  }

  @Override
  protected String getMethodName() {
    return mName;
  }

  @Override
  protected Object hook(Object who, Method method, Object[] args) throws Throwable {
    return mValue;
  }
}
