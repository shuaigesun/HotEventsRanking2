package cn.dao;

import cn.entity.Comments;
import cn.entity.HotEvents;
import cn.util.PageBean;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HotEventsDaoImpl extends BaseDao implements HotEventsDao {
    @Override
    public List<HotEvents> findByPageBean(PageBean pageBean, String keyWord) {
        List<HotEvents> list = new ArrayList<HotEvents>();
        HotEvents hotEvents = null;

        Object[] params =new Object[]{};
        List<Object> listObj = new ArrayList<Object>();
        StringBuffer sqlSB = new StringBuffer("select * from hotevent where 1 = 1  ");
        if(keyWord!=null && !"".equals(keyWord)){
            sqlSB.append("  and keyword like ?");
            listObj.add("%"+keyWord+"%");
        }
        sqlSB.append("  limit ?,?");
        listObj.add(pageBean.getStartRow());
        listObj.add(pageBean.getPageSize());
        params = listObj.toArray();
        rs = super.executeQuery(sqlSB.toString(),params);
        try {
            while(rs.next()){
                hotEvents  = new HotEvents(
                        rs.getInt("id"),
                        rs.getString("keyWord") ,
                        rs.getString("hotContent"),
                        rs.getInt("searchSum"),
                        rs.getDate("createDate"));
                list.add(hotEvents);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Integer count(String keyWord) {
        Integer count = null;
        HotEvents hotEvents = null;

        Object[] params =new Object[]{};
        List<Object> listObj = new ArrayList<Object>();
        StringBuffer sqlSB = new StringBuffer("select count(*) as count from hotevent where 1 = 1  ");
        if(keyWord!=null && !"".equals(keyWord)){
            sqlSB.append("  and keyword like ?");
            listObj.add("%"+keyWord+"%");
        }
        params = listObj.toArray();
        rs = super.executeQuery(sqlSB.toString(),params);
        try {
            while(rs.next()){
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public HotEvents findById(Integer id) {
        HotEvents hotEvents = null;
        String sql = "select * from hotevent where id = ?";
        Object[] params =new Object[]{id};
        rs = super.executeQuery(sql,params);
        try {
            while(rs.next()){
                hotEvents  = new HotEvents(
                        rs.getInt("id"),
                        rs.getString("keyWord") ,
                        rs.getString("hotContent"),
                        rs.getInt("searchSum"),
                        rs.getDate("createDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotEvents;
    }

    @Override
    public List<Comments> findByHotEventsId(Integer hotEventsId) {
        List<Comments> list = new ArrayList<Comments>();
        Comments comments = null;
        String sql = "select * from comments where hotEventsId = ?";
        Object[] params =new Object[]{hotEventsId};
        rs = super.executeQuery(sql,params);
        try {
            while(rs.next()){
                comments  = new Comments();
                comments.setId(rs.getInt("id"));
                comments.setCommentDate(rs.getTimestamp("commentDate"));
                comments.setContent(rs.getString("content"));
                comments.setHotEventsId(rs.getInt("hotEventsId"));
                list.add(comments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Integer addComment(Comments comments) {
        String sql = "insert into comments(hotEventsId,commentDate,content) values(?,?,?)";
        Object[] params =new Object[]{comments.getHotEventsId(),comments.getCommentDate(),comments.getContent()};
        return super.executeUpdate(sql,params);
    }


    public static void main(String[] args){
        HotEventsDao hotEventsDao = new HotEventsDaoImpl();
        Integer count = hotEventsDao.count("中国");
        PageBean pageBean = new PageBean(1, 3, 13);
        List<HotEvents> list = hotEventsDao.findByPageBean(pageBean, "中国");
        for (HotEvents hotEvents:list){
            System.out.println(hotEvents.getId());
        }
    }

}
