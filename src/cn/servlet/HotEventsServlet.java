package cn.servlet;

import cn.entity.Comments;
import cn.entity.HotEvents;
import cn.service.HotEventsServiceImpl;
import cn.util.PageBean;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HotEventsServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String action = request.getParameter("action");
        HotEventsServiceImpl hotEventsService = new HotEventsServiceImpl();
        if(action.equals("list")){
            String pageNoStr = request.getParameter("pageNo");
            Integer pageNo = null;
            if(pageNoStr!=null && !"".equals(pageNoStr)){
                pageNo = Integer.parseInt(pageNoStr);
            }else{
                pageNo =1;
            }
            String keyWord = request.getParameter("keyWord");
            Integer countTotal = hotEventsService.count(keyWord);

            PageBean pageBean = new PageBean(pageNo, 3, countTotal);
            List<HotEvents> list = hotEventsService.findByPageBean(pageBean,keyWord);
            request.setAttribute("list",list);
            request.setAttribute("keyWord",keyWord);
            request.setAttribute("pageBean",pageBean);
            request.getRequestDispatcher("list.jsp").forward(request,response);
        }else if(action.equals("findById")){
            String id = request.getParameter("id");
            HotEvents hotEvents = hotEventsService.findById(Integer.parseInt(id));
            List<Comments> list = hotEventsService.findByHotEventsId(Integer.parseInt(id));
            request.setAttribute("hotEvents",hotEvents);
            request.setAttribute("list",list);
            request.getRequestDispatcher("detail.jsp").forward(request,response);
        } else if(action.equals("addComments")){
            Comments comments = new Comments();
            comments.setContent(request.getParameter("content"));
            comments.setHotEventsId(Integer.parseInt(request.getParameter("id")));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = sdf.format(new Date(System.currentTimeMillis()));
            Date dateNow = null;
            try {
                dateNow = sdf.parse(dateStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
            comments.setCommentDate(dateNow);
            hotEventsService.addComment(comments);
            String json = JSON.toJSONString(comments,
                    SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteNullStringAsEmpty,
                    SerializerFeature.WriteNullNumberAsZero);
            response.getWriter().print(json);
        }
    }

}
