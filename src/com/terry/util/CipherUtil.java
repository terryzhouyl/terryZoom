package com.terry.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对密码进行加密和验证的类
 */
public final class CipherUtil {
	private static Logger logger = LoggerFactory.getLogger(CipherUtil.class);

	private CipherUtil() {
	}

	public final static String NAME = "MD5";

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * <p>
	 * 验证加密数据和数据是否匹配。
	 * </p>
	 * 
	 * @param encoded
	 *            加密后的数据
	 * @param date
	 *            需要鉴别的数据
	 * @return
	 */
	public final static boolean validate(String encoded, String data) {
		if (encoded.equals(encode(data))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * <p>
	 * 加密数据
	 * </p>
	 * 
	 * @param date
	 *            需要加密的数据
	 * @return 加密后的数据
	 */
	public final static String encode(String data) {
		if (data != null) {
			try {
				// 创建具有指定算法名称的信息摘要
				MessageDigest md = MessageDigest.getInstance(NAME);
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				byte[] results = md.digest(data.getBytes());
				// 将得到的字节数组变成字符串返回
				return byteArrayToHexString(results).toUpperCase();

			} catch (Exception e) {
				logger.error("CipherUtil encode Exception " + data, e);
			}
		}
		return data;
	}

	/**
	 * <p>
	 * 转换字节数组为十六进制字符串
	 * </p>
	 * 
	 * @param bytes
	 *            字节数组
	 * @return 十六进制字符串
	 */
	private final static String byteArrayToHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(byteToHexString(bytes[i]));
		}
		return sb.toString();
	}

	/**
	 * <p>
	 * 将一个字节转化成十六进制形式的字符串
	 * </p>
	 * 
	 * @param b
	 *            一个字节
	 * @return 转换成十六进制的字符串
	 */
	private final static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public final static String Encrypt(String strSrc) {
		MessageDigest md = null;
		String strDes = null;

		byte[] bt = strSrc.getBytes();
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(bt);
			strDes = bytes2Hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Invalid algorithm.");
			return null;
		}
		return strDes;
	}

	private final static String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	public static void main(String[] args) {
		System.out.println(CipherUtil.encode("admin"));
		System.out.println(CipherUtil.Encrypt("admin"));

	}
}
