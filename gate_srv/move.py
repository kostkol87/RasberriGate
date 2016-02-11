__author__ = 'Kostya'

import sys
import RPIO
import time

up_pin = 17
down_pin = 22

UP = "up"
DOWN = "down"

def move(direction, duration):
    RPIO.setup(up_pin, RPIO.OUT, initial=RPIO.HIGH)
    RPIO.setup(down_pin, RPIO.OUT, initial=RPIO.HIGH)
    if direction == UP:
        RPIO.output(up_pin, 0)
        time.sleep(float(duration))
        RPIO.output(up_pin, 1)
    if direction == DOWN:
        RPIO.output(down_pin, 0)
        time.sleep(float(duration))
        RPIO.output(down_pin, 1)
    RPIO.cleanup()


move(UP, 8)
move(DOWN, 3)
