package com.artmal.dao.impl;

import com.artmal.dao.AdministratorDao;
import com.artmal.model.users.Administrator;
import com.artmal.utils.DatabaseUtils;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorDaoImpl implements AdministratorDao {
    @Override
    public Administrator findByUserId(long id) throws SQLException {
        DataSource datasource = new DataSource();
        DatabaseUtils.setPoolProperties(datasource);
        Connection con = null;

        try {
            con = datasource.getConnection();

            PreparedStatement findAdministratorByIdStatement = con.prepareStatement("SELECT * FROM administrators WHERE user_id = ?");
            findAdministratorByIdStatement.setLong(1, id);
            ResultSet administrator = findAdministratorByIdStatement.executeQuery();
            administrator.next();

            Administrator theAdministrator = new Administrator();
            theAdministrator.setId(administrator.getInt("id"));
            theAdministrator.setName(administrator.getString("name"));
            theAdministrator.setPassportSerialNumbers(administrator.getString("passport_serial_numbers"));
            theAdministrator.setPhoneNumber(administrator.getString("phone_number"));

            findAdministratorByIdStatement.close();
            administrator.close();
            return theAdministrator;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }    }
}
