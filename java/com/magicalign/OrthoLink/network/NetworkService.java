package com.magicalign.OrthoLink.network;

import java.io.OutputStream;
import java.net.Socket;

import android.content.Context;

import com.magicalign.OrthoLink.commons.GlobalMsgTypes;
import com.magicalign.OrthoLink.protobuf.GetExaminationProtos.GetExamination;
import com.magicalign.OrthoLink.utils.BytesIntConverter;
import com.magicalign.OrthoLink.utils.ConfigUtil;

public class NetworkService {

	private static NetworkService mInstance;
	private boolean mIsConnected;
	private NetConnect mNetCon;
	private Socket mSocket;
	private Context mContext;
	public static String mHostIp;
	private int mHostPort;
	private static ClientListenThread mListenThread;

	private NetworkService() {
		mIsConnected = false;
	}

	public static NetworkService getInstance() {
		if (mInstance == null) {
			mInstance = new NetworkService();
		}
		return mInstance;
	}

	public void onInit(Context context) {
		mContext = context;
		mHostIp = ConfigUtil.getProperty("Server_Ip", context);
		String strServerPort = ConfigUtil.getProperty("Server_Port", context);
		mHostPort = Integer.parseInt(strServerPort);
	}

	public Socket getCurrentSocket() {
		return mSocket;
	}

	public boolean getIsConnected() {
		return mIsConnected;
	}

	public void setupConnection() {
		mNetCon = new NetConnect(mHostIp, mHostPort);
		mNetCon.start();
		try {
			mNetCon.join();
		} catch (Exception e) {
		}

		if (mNetCon == null || !mNetCon.connectedOrNot()) {
			mIsConnected = false;
			return;
		} else {
			mSocket = mNetCon.getSocket();
			mIsConnected = true;

			startListen(mContext);
			if (mSocket != null) {
				System.out.println("socket is not null");
			} else {
				System.out.println("socket is null");
			}
		}
	}

	private void startListen(Context context0) {
		System.out.println("client start listening");
		mListenThread = new ClientListenThread(context0, mSocket);
		mListenThread.start();
		System.out.println("Server start listening[mListenThread]");
	}

	public void getExam(GetExamination getExamination) {
		try {
			OutputStream os = mSocket.getOutputStream();
			os.write(GlobalMsgTypes.msgGetExamination);
			int serializedSize = getExamination.getSerializedSize();
			os.write(BytesIntConverter.intToBytes(serializedSize));
			getExamination.writeTo(os);
			os.flush();
		} catch (Exception e) {
		}
	}

}
