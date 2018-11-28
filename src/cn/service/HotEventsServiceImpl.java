package cn.service;

import cn.dao.HotEventsDao;
import cn.dao.HotEventsDaoImpl;
import cn.entity.Comments;
import cn.entity.HotEvents;
import cn.util.PageBean;

import java.util.List;

public class HotEventsServiceImpl implements HotEventsService {
    private HotEventsDao hotEventsDao = new HotEventsDaoImpl();

    @Override
    public List<HotEvents> findByPageBean(PageBean pageBean, String keyWord) {
        return hotEventsDao.findByPageBean(pageBean,keyWord);
    }

    @Override
    public Integer count(String keyWord) {
        return hotEventsDao.count(keyWord);
    }

    @Override
    public HotEvents findById(Integer id) {
        return hotEventsDao.findById(id);
    }

    @Override
    public List<Comments> findByHotEventsId(Integer hotEventsId) {
        return hotEventsDao.findByHotEventsId(hotEventsId);
    }

    @Override
    public Integer addComment(Comments comments) {
        return hotEventsDao.addComment(comments);
    }
}
