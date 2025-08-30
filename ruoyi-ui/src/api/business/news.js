import request from '@/utils/request'

// 查询新闻模块列表
export function listNews(query) {
  return request({
    url: '/business/news/list',
    method: 'get',
    params: query
  })
}

// 查询新闻模块详细
export function getNews(id) {
  return request({
    url: '/business/news/' + id,
    method: 'get'
  })
}

// 新增新闻模块
export function addNews(data) {
  return request({
    url: '/business/news',
    method: 'post',
    data: data
  })
}

// 修改新闻模块
export function updateNews(data) {
  return request({
    url: '/business/news',
    method: 'put',
    data: data
  })
}

// 删除新闻模块
export function delNews(id) {
  return request({
    url: '/business/news/' + id,
    method: 'delete'
  })
}
