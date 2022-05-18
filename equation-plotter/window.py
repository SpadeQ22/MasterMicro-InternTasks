import tkinter.messagebox
from tkinter import *
from matplotlib.figure import Figure
import numpy as np
from matplotlib.backends.backend_tkagg import (FigureCanvasTkAgg,
                                               NavigationToolbar2Tk)



class App(Tk):
    def __init__(self, *args):
        super().__init__()
        self.title('Plotting in Tkinter')

        self.geometry("1280x800")
        self.Font_tuple = ("Poppins", 24, "normal")
        self.entry = Text(borderwidth=1, width=20, height=1, relief=SOLID, font=self.Font_tuple)
        self.entry.tag_configure("center", justify='center')
        self.entry.pack(pady=20)
        self.plot_button = Button(master=self,
                             command=self.plot,
                             height=2,
                             width=10,
                             text="Plot",
                             borderwidth=1,
                             relief=SOLID)

        self.plot_button.pack()
        self.x = np.linspace(-10, 10, 1000)
        self.fig = Figure(figsize=(5, 5),
                     dpi=100)
        self.canvas = FigureCanvasTkAgg(self.fig,
                                   master=self)
        self.canvas.get_tk_widget().pack()
        self.toolbar = NavigationToolbar2Tk(self.canvas,
                                       self)
        self.toolbar.update()
        self.canvas.get_tk_widget().pack(fill=BOTH)
        self.mainloop()


    def plot(self):
        self.fig.clear()
        exp = self.entry.get("1.0", END)
        exp = exp.replace("^", "**")
        try:
            y = self.evaluate(exp, self.x)
        except Exception:
            tkinter.messagebox.showerror("Incorrect Input", "Please Make sure that the input contains only the following symbols:\n\t\tx, *, /, +, -, ^")

        plot1 = self.fig.add_subplot(111)
        plot1.plot(y)
        self.canvas.draw()

    def evaluate(self, expression, x):
        code = compile(expression, "<string>", "eval")
        ALLOWED_NAMES = {"x": x}
        for name in code.co_names:
            if name not in ALLOWED_NAMES:
                raise NameError(f"The use of '{name}' is not allowed")
        return eval(code, {"__builtins__": {}}, ALLOWED_NAMES)
