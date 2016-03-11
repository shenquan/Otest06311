      /**
       * byte[]转int
       * @company MagicAlign
       * @by Monzy Zhang
       */
package com.magicalign.OrthoLink.utils;

import java.nio.ByteBuffer;

public class BytesIntConverter {

	private static ByteBuffer buffer = ByteBuffer.allocate(4);
	
	/*
	public static byte[] intToBytes(int intNumber) {
		buffer.putInt(0, intNumber);
		return buffer.array();
	}
	
	public static int bytesToInt(byte[] bytes) {
		buffer.put(bytes, 0, bytes.length);
		buffer.flip();
		return buffer.getInt();
	}
	*/
	
	public static byte[] intToBytes(int i) {   
        byte[] result = new byte[4];   
        //由高位到低位
        result[0] = (byte)((i >> 24) & 0xFF);
        result[1] = (byte)((i >> 16) & 0xFF);
        result[2] = (byte)((i >> 8) & 0xFF); 
        result[3] = (byte)(i & 0xFF);
        return result;
      }

      /**
       * byte[]转int
       * @param bytes
       * @return
       */
      public static int bytesToInt(byte[] bytes) {
             int value= 0;
             //由高位到低位
             for (int i = 0; i < 4; i++) {
                 int shift= (4 - 1 - i) * 8;
                 value +=(bytes[i] & 0x000000FF) << shift;//往高位游
             }
             return value;
       }
}
