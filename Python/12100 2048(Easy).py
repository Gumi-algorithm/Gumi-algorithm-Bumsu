import sys
from copy import deepcopy
from collections import deque
input = sys.stdin.readline

N = int(input())

arr = [list(map(int, input().split())) for _ in range(N)]

res = 0
num = [0] * 5

def move():
    board = deepcopy(arr)
    for d in num:
        if(d == 0):
            for i in range(N):
                idx = 0
                for j in range(1, N):
                    if(board[j][i] != 0):
                        if(board[idx][i] == 0):
                            board[idx][i] =board[j][i]
                            board[j][i] = 0
                        elif(board[idx][i] == board[j][i]):
                            board[idx][i] *= 2
                            board[j][i] = 0
                            idx += 1
                        else:
                            idx += 1
                            if(idx != j):
                                board[idx][i] = board[j][i]
                                board[j][i] = 0
        elif(d == 1):
            for i in range(N):
                idx = N - 1
                for j in range(N - 2, -1, -1):
                    if(board[j][i] != 0):
                        if(board[idx][i] == 0):
                            board[idx][i] = board[j][i]
                            board[j][i] = 0
                        elif(board[idx][i] == board[j][i]):
                            board[idx][i] *= 2
                            board[j][i] = 0
                            idx -= 1
                        else:
                            idx -= 1
                            if(idx != j):
                                board[idx][i] = board[j][i]
                                board[j][i] = 0
        elif(d == 2):
            for i in range(N):
                idx = 0
                for j in range(1, N):
                    if(board[i][j] != 0):
                        if(board[i][idx] == 0):
                            board[i][idx] = board[i][j]
                            board[i][j] = 0
                        elif(board[i][idx] == board[i][j]):
                            board[i][idx] *= 2
                            idx += 1
                            board[i][j] = 0
                        else:
                            idx += 1
                            if(idx != j):
                                board[i][idx] = board[i][j]
                                board[i][j] = 0
        elif(d == 3):
            for i in range(N):
                idx = N - 1
                for j in range(N - 2, -1, -1):
                    if(board[i][j] != 0):
                        if(board[i][idx] == 0):
                            board[i][idx] = board[i][j]
                            board[i][j] = 0
                        elif(board[i][idx] == board[i][j]):
                            board[i][idx] *= 2
                            idx -= 1
                            board[i][j] = 0
                        else:
                            idx -= 1
                            if(idx != j):
                                board[i][idx] = board[i][j]
                                board[i][j] = 0
    val = 0
    for i in board:
        for j in i:
            val = max(val, j)
    return val

def dfs(index):
    global res
    if(index == 5):
        res = max(move(), res)
        return
    for i in range(4):
        num[index] = i
        dfs(index + 1)

dfs(0)
print(res)