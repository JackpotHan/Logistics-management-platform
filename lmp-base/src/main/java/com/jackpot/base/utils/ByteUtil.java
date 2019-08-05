package com.jackpot.base.utils;

public class ByteUtil {

	public static byte[] byteAdd(byte[] a, byte[] b){
		byte[] ret = new byte[a.length+b.length];
		for(int i=0; i<a.length; i++){
			ret[i] = a[i];
		}
		for(int j=0; j<b.length; j++){
			ret[a.length+j] = b[j];
		}
		return ret;
		
	}
	
	public static byte[] byteSpace(byte[] a, int len){
		String b = "";
		for(int k=0; k<len-a.length; k++){
			b+=" ";
		}
		byte[] ret = new byte[a.length+b.getBytes().length];
		for(int i=0; i<a.length; i++){
			ret[i] = a[i];
		}
		for(int j=0; j<b.getBytes().length; j++){
			ret[a.length+j] = b.getBytes()[j];
		}
		return ret;
	}
	
	public static byte[] subByte(byte[] a, int start, int end){
		byte[] ret = new byte[end-start];
		for(int i=0; i<end-start; i++){
			ret[i] = a[start+i];
		}
		return ret;
	}

}
