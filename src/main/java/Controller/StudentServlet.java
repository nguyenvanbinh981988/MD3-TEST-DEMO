package Controller;

import Dao.CRUD;
import Dao.CRUDSTUDENT;
import Dao.ClassStDao;
import Dao.StudentDao;
import Model.ClassSt;
import Model.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "StudentServlet", urlPatterns = "/Students")
public class StudentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CRUDSTUDENT studentDao = new StudentDao();

    private CRUD classStDao = new ClassStDao();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    create(req, resp);
                    break;
                case "edit":
                    edit(req, resp);
                    break;
                case "find":
                    find(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showCreate(req, resp);
                    break;
                case "edit":
                    showEdit(req, resp);
                    break;
                case "delete":
                    showDelete(req, resp);
                    break;
                case "find":
                    showFind(req, resp);
                    break;
                default:
                    showListStudent(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    //-------------------Hien thi toan bo list students----------------------------
    private void showListStudent(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        List<Student> students = studentDao.selectAll();
        req.setAttribute("students", students);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }


    //-------------------them student----------------------------

    private void showCreate(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("classSts",classStDao.selectAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("Student/create.jsp");
        dispatcher.forward(req, resp);
    }


    private void create(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String name = req.getParameter("name");
        LocalDate birthDay = LocalDate.parse(req.getParameter("birthDay"));
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        int classId = Integer.parseInt(req.getParameter("classId"));
        ClassSt classSt = (ClassSt) classStDao.select(classId);
        Student newStudent = new Student(name, birthDay,address,phone,email, classSt);
        studentDao.creat(newStudent);
        RequestDispatcher dispatcher = req.getRequestDispatcher("Student/create.jsp");
        req.setAttribute("message", "New Student was created");
        dispatcher.forward(req, resp);
    }


    //-------------------Edit thong tin student----------------------------

    public void showEdit(HttpServletRequest req,HttpServletResponse resp) throws IOException,SQLException,ServletException{
        int id = Integer.parseInt(req.getParameter("id"));
        Student student = (Student) studentDao.select(id);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Student/edit.jsp");
        req.setAttribute("student", student);
        requestDispatcher.forward(req, resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        LocalDate birthDay = LocalDate.parse(req.getParameter("birthDay"));
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");

        String className = req.getParameter("classSt").replaceAll(" ", "");

        ClassSt classSt = (ClassSt) classStDao.selectName(className);

        Student book = new Student(id,name,birthDay,address,phone,email,classSt);
        studentDao.edit(book);
        resp.sendRedirect("/Students");
    }


    //-------------------Delete thong tin Student----------------------------

    public void showDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException,SQLException,ServletException{
        int id = Integer.parseInt(req.getParameter("id"));
        studentDao.delete(id);

        List<Student> students =  studentDao.selectAll();
        req.setAttribute("students", students);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }


    //-------------------find by Name----------------------------

    public void showFind(HttpServletRequest req, HttpServletResponse resp) throws IOException,SQLException,ServletException{
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Student/find.jsp");
        requestDispatcher.forward(req,resp);
    }

    public void find(HttpServletRequest req,HttpServletResponse resp) throws IOException,SQLException,ServletException{
        String name = req.getParameter("name");
        List<Student> findStudents = studentDao.selectName(name);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Student/find.jsp");
        req.setAttribute("findStudents", findStudents);
        requestDispatcher.forward(req, resp);
    }
}