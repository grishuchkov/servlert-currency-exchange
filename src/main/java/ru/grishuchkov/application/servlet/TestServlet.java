package ru.grishuchkov.application.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.grishuchkov.application.contextListener.DatabaseConnectionManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/")
public class TestServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = ((DatabaseConnectionManager) getServletContext().getAttribute("connectionManager")).getConnection();
        String result;


        try {
            String SQL = "SELECT * FROM currencies";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            int id = resultSet.getInt("id");
            String code = resultSet.getString("code");
            String fullName = resultSet.getString("full_name");
            String sign = resultSet.getString("sign");

            result = String.format("Id: %s, code: %s, fullName: %s, sign: %s", id, code, fullName, sign);

        } catch (SQLException e) {
            throw new RuntimeException("Bad, always bad =(");
        }

        PrintWriter printWriter = resp.getWriter();
        printWriter.println(result);
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}
