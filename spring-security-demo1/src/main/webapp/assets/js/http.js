import axios from 'axios';

let server = axios.create({
    baseURL: "http://127.0.0.1:8081/",
    timeout: 3000,
    withCredentials: false
});

//添加一个拦截请求器
server.interceptors.request.use(function (config) {
    config.headers['Content-Type'] = 'application,text/plain';
    return config;
}, function (error) {
    //Do something with request error
    return error;
});

//添加一个响应拦截器
server.interceptors.response.use(function (res) {
    // 写一下操作，比如token过期处理
    if (res.data.statusCode === 401) {
        alert('暂无权限，请重新登录!');
        window.location.href = '/login';
        return false;
    }
    return res;
}, function (error) {
    switch (error && error.response && error.response.status) {
        case 400:
            error.message = '请求错误';
            break;
        case 401:
            error.message = '未授权，请登录';
            break;
        case 403:
            error.message = '拒绝访问';
            break;
        case 404:
            error.message = '未找到访问地址';
            break;
        case 408:
            error.message = '请求超时';
            break;
        case 500:
            error.message = '服务器内部错误';
            break;
        case 501:
            error.message = '服务未实现';
            break;
        case 502:
            error.message = '网关错误';
            break;
        case 503:
            error.message = '服务不可用';
            break;
        case 504:
            error.message = '网关超时';
            break;
        case 505:
            error.message = 'HTTP版本不受支持';
            break;
        default:
    }
    //Do something with response error
    return error;
});


