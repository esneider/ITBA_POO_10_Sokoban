'''
Created on Jun 8, 2010

@author: eordano
'''
from PyQt4 import QtGui, QtCore
import sys

options = {'chabon': "1,0,0,0,0", 'hole': "5,0,0,0,0", 'wall': "4,0,0,0,0",
           'derecha': "6,3,0,0,0", 'izquierda': "6,1,0,0,0", 'arriba': "6,2,0,0,0",
           'abajo': "6,0,0,0,0", 'croja':"2,0,255,0,0", 'trojo':"3,0,255,0,0",
           'cazul':"2,0,0,0,255", 'tazul':"3,0,0,0,255",
           'cnegra':"2,0,0,0,0", 'tnegro':"3,0,0,0,0", 'save': "save", 'clear': "clear"}

class rebut(QtGui.QPushButton):
    def __init__(self, name, x, y, parent, quecosa=""):
        QtGui.QWidget.__init__(self, name)
        self.x, self.y = x, y
        self.parent = parent
        self.quecosa = quecosa
    
    def getpos(self):
        return self.x, self.y
    
    def asign(self):
        self.parent.cosas.update({(self.x, self.y): self.parent.actual})
        self.setText(self.parent.actual)
    
    def setear(self):
        if (self.quecosa == "clear"):
            if ((self.x, self.y) in self.parent.cosas.keys()):
                self.parent.cosas.pop((self.x, self.y))
        else:
            self.parent.actual = self.quecosa

class Grid(QtGui.QWidget):
    name = ""
    cosas = {}
    actual = ""
    
    def __init__(self, parent=None, width=20, height=20):
        QtGui.QWidget.__init__(self, parent)
        self.name = QtGui.QInputDialog.getText(self, "Nombre", "Ingrese el nombre del nivel")
        self.width = width
        self.height = height
        guigrid = QtGui.QGridLayout()
        
        sortop = list(options)
        sortop.sort()
        for op, i in zip(sortop, xrange(len(options))):
            button = rebut(op, 0, i, self, op)
            if op == "save":
                self.connect(button, QtCore.SIGNAL('clicked()'), self.save)
            else:
                self.connect(button, QtCore.SIGNAL('clicked()'), button.setear)        
            guigrid.addWidget(button, i, 0)
        
        for i in xrange(20):
            for j in xrange(20):
                button = rebut("", i, j, self)
                self.connect(button, QtCore.SIGNAL('clicked()'), button.asign)
                guigrid.addWidget(button, j, i+1)
        
        self.setLayout(guigrid)
    
    def save(self):
        filename = QtGui.QFileDialog.getSaveFileName(self, 'Save', '/home/eordano/Code/workspace/Sokoban/levels/')
        savefile = open(filename, 'w')
        equises = [i[0] for i in self.cosas]
        iyises = [i[1] for i in self.cosas]
        width = max(equises) - min(equises) + 1
        height = max(iyises) - min(iyises) + 1
        ret = "%s\n%d,%d\n" % (str(self.name), height, width) 
        for i in self.cosas:
            ret += "%d,%d,%s\n" % (i[1]-min(iyises), i[0]-min(equises), options[self.cosas[i]])
        savefile.write(ret)
        savefile.close

if __name__ == "__main__":
    app = QtGui.QApplication(sys.argv)
    qb = Grid()
    qb.show()
    sys.exit(app.exec_())
