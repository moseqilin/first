package com.huo2;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet({"/some.do","/next.do","/pre.do"})
public class Story extends HttpServlet {
    private int i = 0;
    private int num = 0;
    private String s = "";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if("/pre.do".equals(request.getServletPath())){
            doSome(request,response);
        } else {
            readFile(request, response);
        }

//        if("/some.do".equals(request.getServletPath())){
//            doSome(request,response);
//            request.getSession().setAttribute("num","120");
//        } else{{
//            System.out.println(request.getSession(false));
//            System.out.println(request.getSession().getAttribute("num"));
//        }}
    }

    private void doSome(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        FileInputStream fis = (FileInputStream) ac.getBean("inputstream");
        InputStreamReader isr = null;
        char ch[] = new char[2000];
        HttpSession session = request.getSession();
        try {
            isr=new InputStreamReader(fis,"utf-8");

            if(num >= 200){
                num = Integer.valueOf((String) session.getAttribute("num"));
                num = num-ch.length;
                isr.skip(num);
            } else {
                response.getWriter().println("已经是第一页了。");
                return;
            }
            if((i = isr.read(ch) )!= -1) {
                System.out.println("doSome"+"-++-"+num);
                s = new String(ch,0,ch.length);
                response.getWriter().println(s);
                session.setAttribute("num",num+"");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void readFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");
        String config = "applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        FileInputStream fis = (FileInputStream) ac.getBean("inputstream");
        InputStreamReader isr = null;
        char ch[] = new char[2000];
        HttpSession session = request.getSession();
        try {
            isr=new InputStreamReader(fis,"utf-8");

            if("/next.do".equals(request.getServletPath())){
                num = Integer.valueOf((String) session.getAttribute("num"));
                isr.skip(num);
            }
            if((i = isr.read(ch) )!= -1) {
                s = new String(ch,0,ch.length);
                if("/next.do".equals(request.getServletPath())) {
                    response.getWriter().println(s);
                }
                num+=i;
                session.setAttribute("num",num+"");
                if("/some.do".equals(request.getServletPath())) {
                    request.setAttribute("data",s);
                    request.getRequestDispatcher("/WEB-INF/show.jsp").forward(request, response);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
