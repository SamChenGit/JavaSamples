package com.codeLibrary.dao;

import java.util.List;

import com.codeLibrary.entity.CodeInfo;

public interface CodeInfoDao {
   public List<CodeInfo> getCodeInfosByCondition(int categoryId, String key, String title);
   public CodeInfo fetchByKey(int id);
   public void add(CodeInfo info);
   public void update(CodeInfo info);
   public void delete(int id);
}
