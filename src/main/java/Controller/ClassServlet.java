package Controller;


import Dao.ClassStDao;
import Model.ClassSt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/ClassSt")
public class ClassServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ClassStDao classStDao = new ClassStDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "createClassSt":
                    createClassSt(req, resp);
                    break;
                case "editClassSt":
                    editClassSt(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        RequestDispatcher dispatcher = null;
        try {
            switch (action) {
                case "createClassSt":
                    showCreateClassSt(req, resp);
                    break;
                case "editClassSt":
                    showEditClassSt(req, resp);
                    break;
                case "deleteClassSt":
                    showDeleteClassSt(req, resp);
                    break;
                default:
                    showListClassSt(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    //-------------------Hien thi toan bo list students----------------------------
    private void showListClassSt(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        List<ClassSt> classSts = classStDao.selectAll();
        req.setAttribute("classSts", classSts);
        RequestDispatcher dispatcher = req.getRequestDispatcher("Class/ClassStList.jsp");
        dispatcher.forward(req, resp);
    }


    //-------------------them classSt----------------------------

    private void showCreateClassSt(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("Class/createClassSt.jsp");
        dispatcher.forward(req, resp);
    }


    private void createClassSt(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String className = req.getParameter("className");
        int number = Integer.parseInt(req.getParameter("number"));
        ClassSt classSt = new ClassSt(className,number);
        classStDao.creat(classSt);
        RequestDispatcher dispatcher = req.getRequestDispatcher("Class/createClassSt.jsp");
        req.setAttribute("message", "New Class was created");
        dispatcher.forward(req, resp);
    }

    //-------------------Edit thong tin ClassSt----------------------------

    public void showEditClassSt(HttpServletRequest req,HttpServletResponse resp) throws IOException,SQLException,ServletException{
        int id = Integer.parseInt(req.getParameter("classId"));
        ClassSt classSt = classStDao.select(id);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Class/editClassSt.jsp");
        req.setAttribute("classSt", classSt);
        requestDispatcher.forward(req, resp);
    }

    private void editClassSt(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        int classId = Integer.parseInt(req.getParameter("classId"));
        String className = req.getParameter("className");
        int number = Integer.parseInt(req.getParameter("number"));

        ClassSt book = new ClassSt(classId,className,number);
        classStDao.edit(book);
        resp.sendRedirect("/ClassSt");
    }


    //-------------------Delete thong tin ClassSt----------------------------

    public void showDeleteClassSt(HttpServletRequest req, HttpServletResponse resp) throws IOException,SQLException,ServletException{
        int id = Integer.parseInt(req.getParameter("classId"));
        classStDao.delete(id);

        List<ClassSt> classSts = classStDao.selectAll();
        req.setAttribute("classSts", classSts);
        RequestDispatcher dispatcher = req.getRequestDispatcher("Class/ClassStList.jsp");
        dispatcher.forward(req, resp);
    }
}
