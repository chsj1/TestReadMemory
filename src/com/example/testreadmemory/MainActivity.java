package com.example.testreadmemory;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.app.Activity;
import android.os.Bundle;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tv = null;

	private String getAvailMemory() {// ��ȡandroid��ǰ�����ڴ��С

		ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		MemoryInfo mi = new MemoryInfo();
		am.getMemoryInfo(mi);
		// mi.availMem; ��ǰϵͳ�Ŀ����ڴ�

		return Formatter.formatFileSize(getBaseContext(), mi.availMem);// ����ȡ���ڴ��С���
	}

	private String getTotalMemory() {
		String str1 = "/proc/meminfo";// ϵͳ�ڴ���Ϣ�ļ�
		String str2;
		String[] arrayOfString;
		long initial_memory = 0;

		try {
			FileReader localFileReader = new FileReader(str1);
			BufferedReader localBufferedReader = new BufferedReader(
					localFileReader, 8192);
			str2 = localBufferedReader.readLine();// ��ȡmeminfo��һ�У�ϵͳ���ڴ��С

			arrayOfString = str2.split("\\s+");
			for (String num : arrayOfString) {
				Log.i(str2, num + "\t");
			}

			initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// ���ϵͳ���ڴ棬��λ��KB������1024ת��ΪByte
			localBufferedReader.close();

		} catch (IOException e) {
		}
		return Formatter.formatFileSize(getBaseContext(), initial_memory);// Byteת��ΪKB����MB���ڴ��С���
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView) findViewById(R.id.system_memory);
		tv.setText("�ֻ����ڴ�: " + this.getTotalMemory() + ", " + "�����ڴ�: "
				+ this.getAvailMemory());
	}

}
