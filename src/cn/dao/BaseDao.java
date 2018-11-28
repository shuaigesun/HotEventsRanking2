package cn.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8";
	private static final String USER = "root";
	private static final String PWD = "sunxinshuai";
	protected Connection conn=null;
	protected PreparedStatement pstmt=null;
	protected ResultSet rs=null;
	/**
	 * ��������
	 */
	static{
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡ����
	 */
	public void getConnection(){
		try {
			conn=DriverManager.getConnection(URL, USER,PWD);
			System.out.println(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * �ر���Դ�ķ���
	 */
	public void closeAll(){
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(pstmt!=null){
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ɾ�ĵ�ͨ�÷���
	 */
	public int executeUpdate(String sql,Object[] params){
		int num=0;
		getConnection();
		try {
			pstmt=conn.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					pstmt.setObject(i+1, params[i]);
				}
			}
			num=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeAll();
		}
		return num;
	}
	/**
	 * ��ѯ��ͨ�÷���
	 */
	public ResultSet executeQuery(String sql,Object[] params){
		getConnection();
		try {
			pstmt=conn.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					pstmt.setObject(i+1, params[i]);
				}
			}
			rs=pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static void main(String[] args) {
		BaseDao baseDao = new BaseDao();
		baseDao.getConnection();
	}
}
