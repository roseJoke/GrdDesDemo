package com.grddes.config;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/*
 * ProjectName:  GrdDesDemo
 * Author:  小浪仙℡816
 * Date:  2018/4/25  20:14
 * 声明WebSocket
 */
@ServerEndpoint("/chat")
public class WebSocketServer{
    /**
     *   通道建立成功需要执行的操作
     * @param session
     */
    @OnOpen
    public void onOpen(Session session){
        System.out.println ("连接已经建立，sessionID："+session.getId());
    }

    /**
     * 接收对方的数据
     * @param message
     */
    @OnMessage
    public void onMessage(String message){
        System.out.println("开始接受数据："+message);
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void OnClose(){
        System.out.println("链接已经关闭");
    }

    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }
}
