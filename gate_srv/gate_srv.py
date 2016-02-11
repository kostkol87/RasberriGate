__author__ = 'Konstantin'

from xmlrpc.server import SimpleXMLRPCServer
from xmlrpc.server import SimpleXMLRPCRequestHandler

import logging
import os
import RPIO
import time

up_pin = 17
down_pin = 22

UP = "up"
DOWN = "down"

BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
os.chdir(BASE_DIR)

logging.basicConfig(filename='xml_rpc.log', level=logging.DEBUG, format=u'%(asctime)s %(message)s')


def move(direction):
    RPIO.cleanup()
    RPIO.setup(up_pin, RPIO.OUT, initial=RPIO.HIGH)
    RPIO.setup(down_pin, RPIO.OUT, initial=RPIO.HIGH)

    if direction == UP:
        try:
            RPIO.output(up_pin, 0)
        except:
            pass
        time.sleep(1)


    if direction == DOWN:
        try:
            RPIO.output(down_pin, 0)
        except:
            pass
        time.sleep(1)


def stop_all():
    try:
        RPIO.output(up_pin, 1)
        RPIO.output(down_pin, 1)
        RPIO.cleanup()
    except:
        pass


# Restrict to a particular path.
class RequestHandler(SimpleXMLRPCRequestHandler):
    rpc_paths = ('/RPC2',)

# Create server
server = SimpleXMLRPCServer(("192.168.0.100", 44444), requestHandler=RequestHandler, allow_none=True)
server.register_introspection_functions()

#registred functions
server.register_function(move)
server.register_function(stop_all)

# Run the server's main loop
server.serve_forever()

