from threading import *
import redis


class Subscriber(Thread):
    def __init__(self, connection, channel):
        Thread.__init__(self)
        self.connection = connection
        self.pubsub = connection.pubsub()
        self.pubsub.subscribe(channel)

    def onSubscribe(self, item):
        print('Subscribed to', item['channel'].decode())
        print('Number of subscription is', item['data'])

    def onUnsubscribe(self, item):
        print('Unbscribed from', item['channel'].decode())

    def onMessage(self, item):
        print('Incoming message:',item['channel'].decode(), ":", item['data'].decode())

    def process(self, item):
        type = item['type']
        if type == 'subscribe':
            self.onSubscribe(item)
        elif type == 'unsubscribe':
            self.onUnsubscribe(item)
        elif type == 'message':
            self.onMessage(item)

    def run(self):
        for item in self.pubsub.listen():
            if type(item['data']) != int and item['data'].decode() == 'KILL':
                self.pubsub.unsubscribe()
                print(self, 'unsubscribed and finished')
            else:
                self.process(item)
        print("killed")

if __name__ == '__main__':
    channel = 'fenerbahce'
    subscriber = Subscriber(redis.StrictRedis(host='192.168.1.125', port=6379), channel)
    subscriber.start()

    publisher = redis.StrictRedis(host='192.168.1.125', port=6379)
    publisher.publish(channel, 'Test message for this channel 1')
    publisher.publish(channel, 'Test message for this channel 2')
    publisher.publish(channel, 'Test message for this channel 3')
    publisher.publish(channel, 'KILL')
