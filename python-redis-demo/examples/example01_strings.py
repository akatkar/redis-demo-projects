import redis

cache = redis.StrictRedis(host='192.168.1.123', port=6379, db=0)

cache.set("events/city/istanbul", "32,15,223,828")
cachedResponse = cache.get("events/city/rome").decode()
print(cachedResponse)