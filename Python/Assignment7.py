
#Basima Zafar

from tkinter import *
import tkinter as tk
import sys
from sys import argv
import random
import rpack

#from rectpack import newPacker

#import sys


#Creates a rectangle class that expects height, width, x and y as parameters 
class Rectangle:
    def __init__(self, height,width,x,y):
        self.height = int(height)
        self.width = int(width)
        self.x = int(x)
        self.y = int(y)

#Getters that return itself
    def get_h(self):
        return self.height
    def get_w(self):
        return self.width
    def getx(self):
        return self.x
    def gety(self):
        return self.y

#CustomCanvas Class, this is what creates the background box to pop up 
class CustomCanvas:
    def __init__(self,height,width):
       # self.tempList: List[Rectangle] =[]

        self.master = Tk()
        self.height = int(height)
        self.width = int(width)
        self.canvas = Canvas (self.master, height=height , width=width, bg="red")
        self.canvas.pack()
#Draw_Rects is a method that expects a rect_list of Rectangle objects, it traverses through the list and 
#creates the rectangles by using the getters and adding the width to the x value and adding the height to the y value
#in order to get the bototm right corner of the box so they do not collide
    def draw_Rects(self, rect_list):
        for i in rect_list:
            self.canvas.create_rectangle(i.getx(), i.gety(), i.getx() + i.get_w(), i.gety() + i.get_h(), fill = "purple")
        self.master.mainloop()

#Pack method, this expects a list of Rectangle objects, within this, it creates a new tempList that only gets the
#height and width because the list sent in is sent in with four paramters height, width, 0, 0, however rpack only
#expects a list of height and width in order to come up with the correct cordinates
#return pos returns the new cordinate list to the main function in order to combine the original rectangle list and the cordinate list to make Rectangle objects that will be created in draw_rect method

def pack(rect_list):

    tempList =[]
    pos =[]

    for i in rect_list:
        tempList.append((int(i.get_h()), int(i.get_w())))

    pos = rpack.pack(tempList)

    return pos


#The main method first takes in the file and splits it based off the commas and the the "/n", it first takes in the canvas size and makes it into a tuple
#After taking in the canvas size it loops through the remaining lines of the file and parses the height and width stripping it of the "\n" and splitting it by the commas
#it then takes the height and width and makes Rectangle objects that are then added to the rect_list, this rect_list will then be sent to pack
#The pack method returns a list of cordinates (x,y), I then create a new final_cord list in order to combine the original rect_list with the cord_list
#This is accomplished by iterating through the rect_list and obtaining the specific values 
#It then sets c equal to CustomCanvas to create the canvas size and then uses c to call draw_rects to fill the screen with rectangles

def main():
    python_script, file_name = argv

    python_script = sys.argv[0]
    file_name = sys.argv[1]

    if len(file_name)<1:
        exit(1)

   

    canvas_size =[]
    rect_list =[]
 

    with open (file_name) as f:
        size =(f.readline().strip().split(","))
        canvas_size = tuple(size)


    

        for line in f:
            field = line.split(",")
            height = field[0]
            width = field[1]
            newHeight = height.strip()
            rect_list.append(Rectangle(height.strip(), width.strip(), 0, 0))

        cord_list = pack(rect_list) #this gets a new list that does not overlap, this is then used to to take those objects and add it to a rectangle list so then the right cordinates can be drawn
        final_cord = []
        for i in range (0, len(rect_list)):
        	final_cord.append(Rectangle(rect_list[i].height, rect_list[i].width, cord_list[i][0], cord_list[i][1]))
        c = CustomCanvas(*canvas_size)
        c.draw_Rects(final_cord)


if __name__ == '__main__':
    main()

