package in.sp.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.sp.dbcon.DbConnection;
import in.sp.model.User;

@WebServlet("/MonthlyReport")
public class MonthlyReport extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("session_user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        User user = (User) session.getAttribute("session_user");

        String month = request.getParameter("month");   // format: 2026-01

        try {
            Connection con = DbConnection.getConnection();

            // Total expense for month
            String totalSql = "SELECT SUM(amount) FROM expenses WHERE user_id=? AND DATE_FORMAT(expense_date,'%Y-%m')=?";
            PreparedStatement ps1 = con.prepareStatement(totalSql);
            ps1.setInt(1, user.getUserId());
            ps1.setString(2, month);

            ResultSet rs1 = ps1.executeQuery();
            double total = 0;
            if (rs1.next()) {
                total = rs1.getDouble(1);
            }

            // Category wise total
            String catSql = "SELECT category, SUM(amount) FROM expenses WHERE user_id=? AND DATE_FORMAT(expense_date,'%Y-%m')=? GROUP BY category";
            PreparedStatement ps2 = con.prepareStatement(catSql);
            ps2.setInt(1, user.getUserId());
            ps2.setString(2, month);

            ResultSet rs2 = ps2.executeQuery();
            Map<String, Double> categoryMap = new LinkedHashMap<>();

            while (rs2.next()) {
                categoryMap.put(rs2.getString(1), rs2.getDouble(2));
            }

            request.setAttribute("total", total);
            request.setAttribute("categoryData", categoryMap);
            request.setAttribute("month", month);

            RequestDispatcher rd = request.getRequestDispatcher("monthlyReport.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print("Error: " + e.getMessage());
        }
    }
}