package com.xx.tools.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 响应实体
 * 
 * @author CL
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class R<T> {

	/**
	 * 响应码
	 */
	private int code;

	/**
	 * 响应消息体
	 */
	private String msg;

	/**
	 * 响应数据
	 */
	private T data;

	/**
	 * 失败相应
	 *
	 * @param code
	 * @param msg
	 * @param <T>
	 * @return
	 */
	public static <T> R<T> error(int code,String msg) {
		return new R<T>(code, msg, null);
	}

	/**
	 * 失败响应
	 * 
	 * @param msg 响应消息体
	 * @return
	 */
	public static <T> R<T> error(String msg) {
		return new R<T>(500, msg, null);
	}

	/**
	 * 成功响应
	 *
	 * @return
	 */
	public static <T> R<T> ok() {
		return new R<T>(200, null, null);
	}

	/**
	 * 成功响应
	 * 
	 * @param data 响应数据
	 * @return
	 */
	public static <T> R<T> ok(T data) {
		return new R<T>(200, null, data);
	}

	/**
	 * 成功响应
	 * 
	 * @param msg 响应消息体
	 * @return
	 */
	public static <T> R<T> ok(String msg) {
		return new R<T>(200, msg, null);
	}

	/**
	 * 成功响应
	 * 
	 * @param msg  响应消息体
	 * @param data 响应数据
	 * @return
	 */
	public static <T> R<T> ok(String msg, T data) {
		return new R<T>(200, msg, data);
	}

}