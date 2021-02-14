import sys
from collections import deque
input = sys.stdin.readline
board = []
dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]
for i in range(12):
    board.append(list(input().rstrip()))

def downPuyo():
    for i in range(10, -1, -1):
        for j in range(6):
            if(board[i + 1][j] == '.' and board[i][j] != '.'):
                y = i
                while(True):
                    if(y == 11 or board[y + 1][j] != '.'):
                        break
                    y += 1
                board[y][j] = board[i][j]
                board[i][j] = '.'

def removePuyo(y, x):
    check = [[0 for _ in range(6)] for _ in range(12)]
    remove = [[y, x]]
    color = board[y][x]
    check[y][x] = 1
    move = deque([[y, x]])
    while(move):
        ny, nx = move.popleft()
        for i in range(4):
            y = ny + dy[i]
            x = nx + dx[i]
            if(0 <= y < 12 and 0 <= x < 6 and check[y][x] == 0):
                if(board[y][x] == color):
                    move.append([y, x])
                    check[y][x] = 1
                    remove.append([y, x])
    if len(remove) > 3:
        for i in remove:
            board[i[0]][i[1]] = '.'
        return 1
    else:
        return 0

res = 0
while(True):
    possible = 0
    for i in range(11, -1, -1):
        for j in range(6):
            if board[i][j] != '.':
                possible += removePuyo(i, j)
    downPuyo()
    if(possible == 0):
        print(res)
        break
    else:
        res += 1