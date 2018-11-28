package cn.service;

import cn.entity.Comments;
import cn.entity.HotEvents;
import cn.util.PageBean;

import java.util.List;

public interface HotEventsService {
    //分页
    public List<HotEvents> findByPageBean(PageBean pageBean, String keyWord);

    public Integer count(String keyWord);

    //3.通过id查询HotEvents对象
    public HotEvents findById(Integer id);

    //4.通过id查询Comments对象
    public List<Comments> findByHotEventsId(Integer hotEventsId);

    public Integer addComment(Comments comments);
}
