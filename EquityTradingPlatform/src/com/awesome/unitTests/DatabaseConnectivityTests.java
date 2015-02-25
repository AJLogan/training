package com.awesome.unitTests;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.awesome.dataAccess.DatabaseUtils;

public class DatabaseConnectivityTests {

	@Test
	public void testSetupDB() {
		Connection cn = null;
		assertEquals(true, cn == null);
		cn = DatabaseUtils.setupDB();
		assertEquals(false, cn == null);
	}

}
