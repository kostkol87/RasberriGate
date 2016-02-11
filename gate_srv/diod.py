import RPIO
import time


NRF_CE = 17
NRF_CE2 = 22


RPIO.setup(NRF_CE, RPIO.OUT, initial=RPIO.HIGH)
RPIO.setup(NRF_CE2, RPIO.OUT, initial=RPIO.HIGH)

for i in range(10):
    RPIO.output(NRF_CE, 0)
    time.sleep(0.3)
    RPIO.output(NRF_CE, 1)
    time.sleep(0.3)
    
    RPIO.output(NRF_CE2, 0)
    time.sleep(0.3)
    RPIO.output(NRF_CE2, 1)
    time.sleep(0.3)

RPIO.cleanup()
