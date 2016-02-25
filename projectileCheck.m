clc 
clear
clear all

% inital velocity 2375 ft/s converted to meters per sec
vStart = 723.9;

% in meters/s^2
g = 9.81;

% 200 yards coverted to meters
calcDist = 182.88;

feetPerMeter = .3048;

% Theta at a zero distance of 100 yards
theta = .5*asin(897.0264/(vStart^2));

% time is seconds
time = calcDist/(vStart * cos(theta));

% height in meters
height = vStart * sin(theta) * time - .5 * g * (time^2)


heightInInches = (height / .3048) * 12


timeInMiliseconds = time * 1000

