package com.revature.utils;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.revature.exceptions.BadUtilException;

public class ConnectionUtils {
		
		final static Logger log = Logger.getLogger(ConnectionUtils.class);

		public static Connection getConnection() throws SQLException, FileNotFoundException, BadUtilException {
			try {
				InputStream dbProps = ConnectionUtils.class.getClassLoader().getResourceAsStream("connect.properties");
			    Properties prop = new Properties();
			    prop.load(dbProps);
				Class.forName(prop.getProperty("driver"));
				String url = prop.getProperty("url");
				String username = prop.getProperty("user");
				String password = prop.getProperty("pass");
				return DriverManager.getConnection(url,username,password);
			} catch (ClassNotFoundException | IOException e) {
				log.error("An exception was thrown in the ConnectionUtils class");
				log.error(e.getStackTrace());
				throw new BadUtilException();
			}
		}
		
	}
