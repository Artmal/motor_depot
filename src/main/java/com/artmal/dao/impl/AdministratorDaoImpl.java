package com.artmal.dao.impl;

import com.artmal.dao.AdministratorDao;
import com.artmal.model.users.Administrator;
import com.artmal.utils.DatabaseUtils;
import lombok.Cleanup;
import org.springframework.stereotype.Repository;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AdministratorDaoImpl implements AdministratorDao {
    final private DataSource dataSource = DatabaseUtils.dataSource;

    @Override
    public Administrator findByUserId(final long id) throws SQLException, NamingException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement findAdministratorByIdStatement = con.prepareStatement("SELECT * FROM administrators WHERE user_id = ?");
        findAdministratorByIdStatement.setLong(1, id);
        
        @Cleanup ResultSet administrator = findAdministratorByIdStatement.executeQuery();
        administrator.next();
        Administrator theAdministrator = new Administrator();
        theAdministrator.setId(administrator.getInt("id"));
        theAdministrator.setName(administrator.getString("name"));
        theAdministrator.setPassportSerialNumbers(administrator.getString("passport_serial_numbers"));
        theAdministrator.setPhoneNumber(administrator.getString("phone_number"));

        return theAdministrator;
    }
}
