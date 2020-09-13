# springboot-redis-cache-demo 

Using lettuce as redis driver

## running with docker-compose

You should have installed docker-compose version 1.25

### Installing docker-compose 

First remove older versions if it is installed
```sh
 sudo apt-get remove docker-compose
```

Then install with following commands
```sh
sudo curl -L https://github.com/docker/compose/releases/download/1.25.0/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
```

## Running MySQL and REDIS 
```sh
docker-compose up -d --remove-orphans
```
You can check containers if they are running with
```sh
docker ps
```

## Running Demo Application
Open the project and run from IDE

## Testing Demo Application
You can run main method of the client from IDE

## Check REDIS for the results
Open a terminal and run the redis cli  
```sh
docker exec -it springboot-redis-cache-demo_cache-demo-redis_1 redis-cli
```

Now you must be in redis-cli prompt. Check the keys and get cached values

```sh
127.0.0.1:6379> keys *
1) "articleCache::1"
2) "allArticlesCache::SimpleKey []"
127.0.0.1:6379> 
```

```sh
127.0.0.1:6379> get "articleCache::1"
"\xac\xed\x00\x05sr\x001com.akatkar.learning.rediscachedemo.model.Article\x00\x00\x00\x00\x00\x00\x00\x01\x02\x00\x03J\x00\tarticleIdL\x00\bcategoryt\x00\x12Ljava/lang/String;L\x00\x05titleq\x00~\x00\x01xp\x00\x00\x00\x00\x00\x00\x00\x01t\x00\x04Javat\x00\x10Java Concurrency"
127.0.0.1:6379> get  "allArticlesCache::SimpleKey []"
"\xac\xed\x00\x05sr\x00\x13java.util.ArrayListx\x81\xd2\x1d\x99\xc7a\x9d\x03\x00\x01I\x00\x04sizexp\x00\x00\x00\x01w\x04\x00\x00\x00\x01sr\x001com.akatkar.learning.rediscachedemo.model.Article\x00\x00\x00\x00\x00\x00\x00\x01\x02\x00\x03J\x00\tarticleIdL\x00\bcategoryt\x00\x12Ljava/lang/String;L\x00\x05titleq\x00~\x00\x03xp\x00\x00\x00\x00\x00\x00\x00\x01t\x00\x04Javat\x00\x10Java Concurrencyx"
127.0.0.1:6379> 
```
You can quit from redis-cli with quit command
```sh
127.0.0.1:6379> quit
```

## Stop the demo
Stop the application from IDE.

Stop containers with executing following command
```sh
docker-compose down 
```
