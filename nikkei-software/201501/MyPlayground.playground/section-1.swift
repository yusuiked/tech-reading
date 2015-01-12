// Playground - noun: a place where people can play

import UIKit

var str = "Hello, playground"

var x = 10
var y = 20
var width = 200
var height = 50

var color = UIColor.redColor()

var rect = CGRect(x: x, y: y, width: width, height: height)

var label = UILabel(frame: rect)
label.backgroundColor = color
label.text = str
