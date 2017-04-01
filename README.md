# Spring boot with WebSocket
[another webSocket demo](https://github.com/DR-YangLong/websocket)

this demo use handler chain to filter message and process business.

## use flow
1. start application
2. open 127.0.0.1:8083/index.html in browser
3. open 127.0.0.1:8083/web/token in new tap 
4. open 127.0.0.1:8083/web/users in ne tap 
5. in the index.html tap,click Connect button to use WebSocket
6. refresh users tap,the WebSocket session is associate user
7. then in users tap,send message to signal user or kick user,the result will show in index.html tap

## code
core: spring boot WebSocket
### config package
the config package read properties to init server endpoint.
### handler package
the handler package defined handler to process event:
> MessageHandler: 

accept all event and distribute them to another handler
> MessageSelectorHandler: 

deal with OnMessage event
> OpenCloseHandler: 

deal with connection is opened and closed event

```text
you can define specify handler to process different even in MessageHandler class
```
### filter package
define the message process bean,like servlet filter.
specify filter handle specify message.
all message must be subClass of **Message**.
the message type and filters define in **application.properties**.


### process flow
1. client connect
2. server invoke MessageHandler
3. MessageHandler invoke MessageSelectorHandler
4. MessageSelectorHandler init BusinessFilterChain,
5. filter chain process message
6. result send to client
