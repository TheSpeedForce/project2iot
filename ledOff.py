import RPi.GPIO as GPIO 

print ("Turning off LED")

GPIO.setwarnings(False) 

# Use physical pin numbering
GPIO.setmode(GPIO.BOARD)

#0 set pins for components
ledpin = 12

GPIO.setup(ledpin, GPIO.OUT, initial=GPIO.LOW)

# Turn off LED
GPIO.output(ledpin, GPIO.LOW)

print ("LED Off")