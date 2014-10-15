package com.musicStore.aop;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

import com.musicStore.bean.User;
import com.opensymphony.xwork2.ActionContext;

public class BizLogAdvice {
	public Object doAround(ProceedingJoinPoint jp) throws Throwable {
		Object value = jp.proceed();
		Class clz = jp.getTarget().getClass();
		Signature st = jp.getSignature();
		User user = (User)ActionContext.getContext().getSession().get("loginUser");		
		String logLine = Calendar.getInstance().getTime()+"--"+(user==null?"匿名用户":user.getUsername())+",执行了"+clz.getName()+"类的"+st.getName()+"方法。";
		writeLogToFile(logLine);
		return value;
	}

	private void writeLogToFile(String logLine) throws FileNotFoundException,
			IOException {
		FileOutputStream fos = new FileOutputStream("c:/MusicStoreBizLog.txt",true);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write(logLine);
		bw.newLine();
		bw.flush();
		bw.close();
		osw.close();
		fos.close();
	}
}
