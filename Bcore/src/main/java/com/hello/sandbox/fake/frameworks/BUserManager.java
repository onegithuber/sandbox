package com.hello.sandbox.fake.frameworks;

import android.os.RemoteException;
import java.util.Collections;
import java.util.List;
import com.hello.sandbox.core.system.ServiceManager;
import com.hello.sandbox.core.system.user.BUserInfo;
import com.hello.sandbox.core.system.user.IBUserManagerService;

/** Created by Milk on 4/28/21. * ∧＿∧ (`･ω･∥ 丶　つ０ しーＪ 此处无Bug */
public class BUserManager extends BlackManager<IBUserManagerService> {
  private static final BUserManager sUserManager = new BUserManager();

  public static BUserManager get() {
    return sUserManager;
  }

  @Override
  protected String getServiceName() {
    return ServiceManager.USER_MANAGER;
  }

  public BUserInfo createUser(int userId) {
    try {
      return getService().createUser(userId);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void deleteUser(int userId) {
    try {
      getService().deleteUser(userId);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

  public List<BUserInfo> getUsers() {
    try {
      return getService().getUsers();
    } catch (RemoteException e) {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }
}
