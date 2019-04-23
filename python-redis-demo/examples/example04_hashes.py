import redis

cache = redis.StrictRedis(host='192.168.1.125', port=6379, db=0)

cache.hset("user#1", "name", "Peter")
cache.hset("user#1", "job", "politician")

name = cache.hget("user#1", "name")

fields = cache.hgetall("user#1")
print(type(fields))
print(fields)

job = fields.get("job".encode())
print("job:", job.decode())

for i in fields:
    print(i.decode(), fields.get(i).decode())