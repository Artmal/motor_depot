package com.artmal.dao.impl;

import com.artmal.dao.DispatcherDao;
import com.artmal.model.users.Dispatcher;
import com.artmal.utils.DatabaseUtils;
import lombok.Cleanup;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DispatcherDaoImpl implements DispatcherDao {
    private final DataSource dataSource = DatabaseUtils.initializeDataSource();

    @Override
    public boolean save(Dispatcher dispatcher) throws SQLException, NamingException {
        @Cleanup Connection con = dataSource.getConnection();

        // Insert to users
        @Cleanup PreparedStatement insertUserStatement = con.prepareStatement("INSERT INTO users"
                    + " (email, password, date_of_registration, reg_admin_id) VALUES (?, ?, NOW(), ?)");

        insertUserStatement.setString(1, dispatcher.getUserInfo().getEmail());
        insertUserStatement.setString(2, dispatcher.getUserInfo().getPassword());
        insertUserStatement.setLong(3, dispatcher.getUserInfo().getRegAdminId());
        insertUserStatement.execute();

        // Insert to user_roles
        @Cleanup Statement st = con.createStatement();
        @Cleanup ResultSet rs = st.executeQuery("SELECT id FROM users ORDER BY id DESC LIMIT 1");
        rs.next();

        long idOfLastInsert = rs.getInt("id");
        @Cleanup PreparedStatement setRoleForUser = con.prepareStatement("INSERT INTO user_roles (user_id, role_id) "
                    + "VALUES (?, 2)");

        setRoleForUser.setLong(1, idOfLastInsert);
        setRoleForUser.execute();

        @Cleanup PreparedStatement insertDispatcherStatement = con.prepareStatement("INSERT INTO dispatchers (name, "
                + "passport_serial_numbers, phone_number, salary_in_dollars, user_id) VALUES (?, ?, ?, ?, ?)");

        insertDispatcherStatement.setString(1, dispatcher.getName());
        insertDispatcherStatement.setString(2, dispatcher.getPassportSerialNumbers());
        insertDispatcherStatement.setString(3, dispatcher.getPhoneNumber());
        insertDispatcherStatement.setInt(4, dispatcher.getSalaryInDollars());
        insertDispatcherStatement.setLong(5, idOfLastInsert);
        insertDispatcherStatement.execute();

        return true;
    }

    @Override
    public Dispatcher findByUserId(long id) throws SQLException, NamingException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement findDispatcherByUsedId = con.prepareStatement("SELECT * FROM dispatchers WHERE user_id = ?");
        findDispatcherByUsedId.setLong(1, id);
        @Cleanup ResultSet dispatcher = findDispatcherByUsedId.executeQuery();
        dispatcher.next();

        Dispatcher theDispatcher = new Dispatcher();
        theDispatcher.setId(dispatcher.getInt("id"));
        theDispatcher.setName(dispatcher.getString("name"));
        theDispatcher.setPassportSerialNumbers(dispatcher.getString("passport_serial_numbers"));
        theDispatcher.setPhoneNumber(dispatcher.getString("phone_number"));
        theDispatcher.setSalaryInDollars(dispatcher.getInt("salary_in_dollars"));

        return theDispatcher;
    }

    @Override
    public Dispatcher findById(long id) throws SQLException, NamingException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement findDispatcherByIdStatement = con.prepareStatement("SELECT * FROM dispatchers WHERE id = ?");
        findDispatcherByIdStatement.setLong(1, id);
        @Cleanup ResultSet dispatcher = findDispatcherByIdStatement.executeQuery();
        dispatcher.next();
        Dispatcher theDispatcher = new Dispatcher();
        theDispatcher.setId(dispatcher.getInt("id"));
        theDispatcher.setName(dispatcher.getString("name"));
        theDispatcher.setPassportSerialNumbers(dispatcher.getString("passport_serial_numbers"));
        theDispatcher.setPhoneNumber(dispatcher.getString("phone_number"));
        theDispatcher.setSalaryInDollars(dispatcher.getInt("salary_in_dollars"));

        return theDispatcher;
    }

    @Override
    public Set<Dispatcher> findAll() throws SQLException, NamingException {
        @Cleanup Connection con = dataSource.getConnection();

        @Cleanup PreparedStatement findAllDriversStatement = con.prepareStatement("SELECT * FROM dispatchers ORDER BY id");
        @Cleanup ResultSet dispatchers = findAllDriversStatement.executeQuery();
        Set<Dispatcher> dispatcherSet = new HashSet<>();
        while(dispatchers.next()) {
            Dispatcher dispatcher = new Dispatcher();
            dispatcher.setId(dispatchers.getInt("id"));
            dispatcher.setName(dispatchers.getString("name"));
            dispatcher.setPassportSerialNumbers(dispatchers.getString("passport_serial_numbers"));
            dispatcher.setPhoneNumber(dispatchers.getString("phone_number"));
            dispatcher.setSalaryInDollars(dispatchers.getInt("salary_in_dollars"));
            dispatcherSet.add(dispatcher);
        }

        return dispatcherSet;
    }
}
