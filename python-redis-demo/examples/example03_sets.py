import redis

cache = redis.StrictRedis(host='192.168.1.125', port=6379, db=0)

cache.sadd("nicknames", "nickname#1")
cache.sadd("nicknames", "nickname#2")
cache.sadd("nicknames", "nickname#1")

nicknames = cache.smembers("nicknames");
print(type(nicknames), ":", nicknames)

exists = cache.sismember("nicknames", "nickname#1");
print(exists)
