import redis

cache = redis.StrictRedis(host='192.168.1.125', port=6379, db=0)

scores = dict()

scores["PlayerOne"] = 3000.0
scores["PlayerTwo"] = 2500.0
scores["PlayerThree"] = 7200.0

for player in scores:
    #print(player, scores.get(player))
    cache.zadd("scores", scores)

players = cache.zrevrange("scores", 0, 1)
print(players)

players = cache.zrange("scores", 0, -1, withscores=True)
print(players)

rank = cache.zrevrank("scores", "PlayerOne")
print(rank)