package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.News;

/**
 * 新闻模块Service接口
 * 
 * @author ruoyi
 * @date 2025-07-02
 */
public interface INewsService 
{
    /**
     * 查询新闻模块
     * 
     * @param id 新闻模块主键
     * @return 新闻模块
     */
    public News selectNewsById(Long id);

    /**
     * 查询新闻模块列表
     * 
     * @param news 新闻模块
     * @return 新闻模块集合
     */
    public List<News> selectNewsList(News news);

    /**
     * 新增新闻模块
     * 
     * @param news 新闻模块
     * @return 结果
     */
    public int insertNews(News news);

    /**
     * 修改新闻模块
     * 
     * @param news 新闻模块
     * @return 结果
     */
    public int updateNews(News news);

    /**
     * 批量删除新闻模块
     * 
     * @param ids 需要删除的新闻模块主键集合
     * @return 结果
     */
    public int deleteNewsByIds(Long[] ids);

    /**
     * 删除新闻模块信息
     * 
     * @param id 新闻模块主键
     * @return 结果
     */
    public int deleteNewsById(Long id);
}
