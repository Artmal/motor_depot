package com.artmal.dao.impl;

import com.artmal.dao.DispatcherDao;
import com.artmal.model.users.Dispatcher;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class DispatcherDaoImpl implements DispatcherDao {
    @Override
    public boolean save(Dispatcher dispatcher) throws SQLException, NamingException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource =(javax.sql.DataSource)envContext.lookup("jdbc/TestDB");
        Connection con = null;

        try {
            con = dataSource.getConnection();

            // Insert to users
            PreparedStatement insertUserStatement = con.prepareStatement("INSERT INTO users" +
                    " (email, password, date_of_registration, reg_admin_id) VALUES (?, ?, NOW(), ?)");

            insertUserStatement.setString(1, dispatcher.getUserInfo().getEmail());
            insertUserStatement.setString(2, dispatcher.getUserInfo().getPassword());
            insertUserStatement.setLong(3, dispatcher.getUserInfo().getRegAdminId());
            insertUserStatement.execute();

            // Insert to user_roles
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT id FROM users ORDER BY id DESC LIMIT 1");
            rs.next();

            long idOfLastInsert = rs.getInt("id");
            PreparedStatement setRoleForUser = con.prepareStatement("INSERT INTO user_roles (user_id, role_id) " +
                    "VALUES (?, 2)");

            setRoleForUser.setLong(1, idOfLastInsert);
            setRoleForUser.execute();

            PreparedStatement insertDispatcherStatement = con.prepareStatement("INSERT INTO dispatchers (name, " +
                    "passport_serial_numbers, phone_number, salary_in_dollars, user_id) VALUES (?, ?, ?, ?, ?)");

            insertDispatcherStatement.setString(1, dispatcher.getName());
            insertDispatcherStatement.setString(2, dispatcher.getPassportSerialNumbers());
            insertDispatcherStatement.setString(3, dispatcher.getPhoneNumber());
            insertDispatcherStatement.setInt(4, dispatcher.getSalaryInDollars());
            insertDispatcherStatement.setLong(5, idOfLastInsert);
            insertDispatcherStatement.execute();

            insertUserStatement.close();
            st.close();
            rs.close();
            return true;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public Dispatcher findByUserId(long id) throws SQLException, NamingException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource =(javax.sql.DataSource)envContext.lookup("jdbc/TestDB");
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findDispatcherByUsedId = con.prepareStatement("SELECT * FROM dispatchers WHERE user_id = ?");
            findDispatcherByUsedId.setLong(1, id);
            ResultSet dispatcher = findDispatcherByUsedId.executeQuery();
            dispatcher.next();

            Dispatcher theDispatcher = new Dispatcher();
            theDispatcher.setId(dispatcher.getInt("id"));
            theDispatcher.setName(dispatcher.getString("name"));
            theDispatcher.setPassportSerialNumbers(dispatcher.getString("passport_serial_numbers"));
            theDispatcher.setPhoneNumber(dispatcher.getString("phone_number"));
            theDispatcher.setSalaryInDollars(dispatcher.getInt("salary_in_dollars"));

            findDispatcherByUsedId.close();
            dispatcher.close();
            return theDispatcher;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public Dispatcher findById(long id) throws SQLException, NamingException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource =(javax.sql.DataSource)envContext.lookup("jdbc/TestDB");
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findDispatcherByIdStatement = con.prepareStatement("SELECT * FROM dispatchers WHERE id = ?");
            findDispatcherByIdStatement.setLong(1, id);
            ResultSet dispatcher = findDispatcherByIdStatement.executeQuery();
            dispatcher.next();

            Dispatcher theDispatcher = new Dispatcher();
            theDispatcher.setId(dispatcher.getInt("id"));
            theDispatcher.setName(dispatcher.getString("name"));
            theDispatcher.setPassportSerialNumbers(dispatcher.getString("passport_serial_numbers"));
            theDispatcher.setPhoneNumber(dispatcher.getString("phone_number"));
            theDispatcher.setSalaryInDollars(dispatcher.getInt("salary_in_dollars"));

            findDispatcherByIdStatement.close();
            dispatcher.close();
            return theDispatcher;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }

    @Override
    public Set<Dispatcher> findAll() throws SQLException, NamingException {
        Context ctx = new InitialContext();
        Context envContext = (Context) ctx.lookup("java:comp/env");
        DataSource dataSource =(javax.sql.DataSource)envContext.lookup("jdbc/TestDB");
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement findAllDriversStatement = con.prepareStatement("SELECT * FROM dispatchers ORDER BY id");
            ResultSet dispatchers = findAllDriversStatement.executeQuery();

            Set<Dispatcher> dispatcherSet = new HashSet();
            while(dispatchers.next()) {
                Dispatcher dispatcher = new Dispatcher();
                dispatcher.setId(dispatchers.getInt("id"));
                dispatcher.setName(dispatchers.getString("name"));
                dispatcher.setPassportSerialNumbers(dispatchers.getString("passport_serial_numbers"));
                dispatcher.setPhoneNumber(dispatchers.getString("phone_number"));
                dispatcher.setSalaryInDollars(dispatchers.getInt("salary_in_dollars"));
                dispatcherSet.add(dispatcher);
            }

            findAllDriversStatement.close();
            dispatchers.close();

            return dispatcherSet;
        } finally {
            if (con != null) try {
                con.close();
            } catch (Exception ignore) {
            }
        }
    }
}
