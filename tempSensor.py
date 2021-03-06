# Import Raspberry Pi GPIO library
import RPi.GPIO as GPIO 
import glob, time, os

print ("Temp sensor reading started.")

# set directory for reading temp
base_dir = '/sys/bus/w1/devices/'
device_folder = glob.glob(base_dir + '28*')[0]
device_file = device_folder + '/w1_slave'
temp_file = 'temperatures.txt'

# function read temp from file
def read_temp_raw ():
    f = open (device_file, 'r')
    lines = f.readlines ()
    f.close ()
    return lines

# function to convert temp into readable form
def read_temp ():
    lines = read_temp_raw ()
    while lines [0].strip ()[-3:] != 'YES':
        time.sleep (0.2)
        lines = read_temp_raw ()
    equals_pos = lines [1].find('t=')
    
    if equals_pos != -1:
        temp_string = lines [1] [equals_pos + 2:]
        temp_c = float (temp_string) / 1000.0
        temp_f = temp_c * 9.0/5.0 + 32.0
        return temp_c, temp_f

def write_temp (temp):
    f = open (temp_file,'a')
    temp = str(round(float(temp),2))
    f.write (temp+"\n")
    f.close ()

temp = read_temp()[1]
write_temp(temp)

print ("Temp sensor reading successful.")