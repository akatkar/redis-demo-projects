import redis

cache = redis.StrictRedis(host='192.168.1.125', port=6379, db=0)

cache.lpush("queue#tasks", "firstTask")
cache.lpush("queue#tasks", "secondTask")

task = cache.rpop("queue#tasks");
print(task.decode())
