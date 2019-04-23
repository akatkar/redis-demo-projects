import redis

cache = redis.StrictRedis(host='192.168.1.128', port=6379)

userOneId = "4352523"
userTwoId = "4849888"

rankings = {userOneId: 125, userTwoId: 332}

p = cache.pipeline()
p.sadd("searched#" + userOneId, "paris")
p.zadd("ranking", rankings)
p.sismember("searched#" + userOneId, "paris")
p.zrange("ranking", 0, -1)
responses = p.execute()

for resp in responses:
    print(resp)
