## Start Name Server
```
> nohup sh bin/mqnamesrv &
> tail -f ~/logs/rocketmqlogs/namesrv.log
The Name Server boot success...
```

## Start Broker
```
> nohup sh bin/mqbroker -n localhost:9876 &
> tail -f ~/logs/rocketmqlogs/broker.log
The broker[localhost, 172.20.1.51:10911] boot success...
```

## Shutdown Servers
```
> sh bin/mqshutdown broker
The mqbroker(8399) is running...
Send shutdown request to mqbroker(8399) OK

> sh bin/mqshutdown namesrv
The mqnamesrv(8369) is running...
Send shutdown request to mqnamesrv(8369) OK
```