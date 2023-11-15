package com.hzau;

public interface HelloMBean {

  abstract String getName();

  abstract void setName(String name);

  abstract String getAge();

  abstract void setAge(String age);

  abstract void helloWorld();

  abstract void helloWorld(String str);

  abstract void getTelephone();
}
