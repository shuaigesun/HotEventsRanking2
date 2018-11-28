package cn.dao;

import cn.entity.Comments;
import cn.entity.HotEvents;
import cn.util.PageBean;

import java.util.List;

public interface HotEventsDao {
    //分页
    public List<HotEvents> findByPageBean(PageBean pageBean, String keyWord);

    public Integer count(String keyWord);

    //3.通过id查询HotEvents对象
    public HotEvents findById(Integer id);

    //4.通过id查询Comments对象
    public List<Comments> findByHotEventsId(Integer hotEventsId);

    //5.添加评论信息
    public Integer addComment(Comments comments);

}
