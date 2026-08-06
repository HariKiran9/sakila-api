package com.sakila.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MysqlCon {

	public static Connection getConnection() {
		Connection con = null;
		try {
			// 1. Manually loading the driver class is optional, but if you want to keep it
			// for safety:
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Establish connection directly
			con = DriverManager.getConnection("jdbc:mysql://localhost/sakila", "root", "root");
		} catch (Exception e) {
			log.error("Exception: {}", e);
		}
		return con;
	}

	public static void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			log.error("Excepiont: {}", e);
		}
	}

	public static void main(String args[]) {
		Connection con = null;
		try {
			con = getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from world.city");

			while (rs.next()) {
				StringBuilder sb = new StringBuilder();
				sb.append("Id : ");
				sb.append(rs.getInt(1));
				sb.append(" Name : ");
				sb.append(rs.getString(2));
				sb.append(" Contry Code : ");
				sb.append(rs.getString(3));
				log.info(sb.toString());
			} // while

		} catch (Exception e) {
			log.error("Excepiont: {}", e);
		} finally {
			MysqlCon.closeConnection(con);
		} // finally
	}

}
