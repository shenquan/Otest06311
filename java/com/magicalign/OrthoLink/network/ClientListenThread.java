package com.magicalign.OrthoLink.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.net.Socket;

import android.content.Context;
import android.content.Intent;

import com.magicalign.OrthoLink.commons.GlobalMsgTypes;
import com.magicalign.OrthoLink.exam.ExamDetial;
import com.magicalign.OrthoLink.protobuf.ExaminationProtos.ExaminationList;
import com.magicalign.OrthoLink.utils.BytesIntConverter;

public class ClientListenThread extends Thread {

	private Context mContext;
	private Socket mSocket;
	private BufferedReader mBuffRder;

	public ClientListenThread(Context context, Socket s) {
		this.mContext = context;
		this.mSocket = s;

	}

	@Override
	public void run() {
		super.run();
		try {

			while (true) {
				InputStream inputStream = mSocket.getInputStream();
				int msgType = inputStream.read();

				byte[] msgSizeBytes = new byte[4];
				inputStream.read(msgSizeBytes);
				int msgSize = 0;

				try {
					msgSize = BytesIntConverter.bytesToInt(msgSizeBytes);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				if (mSocket.isClosed()) {
					System.out.println("WTF???? socket is closed????");
				} else {
					switch (msgType) {
					case GlobalMsgTypes.msgGetExamination:
						byte[] getExamBytes = new byte[msgSize];

						//读取一定数量的字节存入到缓冲区数组getExamBytes中
						inputStream.read(getExamBytes);

						//将流解析为对象
						ExaminationList eList = ExaminationList
								.parseFrom(getExamBytes);
						//在这里初始化eList
						ExamDetial.eList = eList;
						Intent intent = new Intent("EXAMLIST");
						mContext.sendBroadcast(intent);

						break;

					default:
						break;
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
