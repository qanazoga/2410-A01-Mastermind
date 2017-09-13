from random import choice
from re import sub

__author__ = "Jerrad Sroufe"

class Row:
    def __init__(self):
        self.code = []
        self.colors = ['W', 'U', 'B', 'R', 'G', 'Y']

    def generate_random_row(self):
        row = []
        for i in range(4):
            row.append(choice(self.colors))
        return row


class Game:
    def __init__(self):
        self.r = Row()
        self.winning_code = self.r.generate_random_row()
        self.win = False

    def check_for_win(self, code):
        white_pins = []
        black_pins = 0

        if code == self.winning_code:
            print("Winner!")
            self.win = True

        for index, color in enumerate(code):
            if color == self.winning_code[index]:
                black_pins += 1
            elif color in self.winning_code:
                if self.winning_code.count(color) >= white_pins.count(color):
                    white_pins.append(color)

        print(f"\n{len(white_pins)} White pins. {black_pins} Black pins.")


    def accept_input(self):
        x = ""
        while len(x) != 4:
            print("What is the codemaster's code? (Available colors: W, U, B, R, G, Y)")
            x = input()
            x = sub(r'[^wubgry]', '', x.lower())

        return list(x.upper())


def main():
    game = Game()

    print(f"DEBUG LINE: WINNING CODE = {game.winning_code}")
    
    for i in range(10):
        if not game.win:
            print(f"\nGuess {i+1}/10")
            game.check_for_win(game.accept_input())

    print(f"The winning code was {game.winning_code}")


main()
