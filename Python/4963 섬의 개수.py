import sys
from collections import deque
input = sys.stdin.readline
dy = [-1, -1, 0, 1, 1, 1, 0, -1]
dx = [0, -1, -1, -1, 0, 1, 1, 1]
res = []
while(True):
    count = 0
    W, H = map(int, input().split())
    if W == 0 and H == 0:
        for i in res:
            print(i)
        break
    board = []
    check = [[0 for _ in range(W)] for _ in range(H)]
    for i in range(H):
        board.append(list(map(int, input().split())))
    for i in range(H):
        for j in range(W):
            if(check[i][j] == 0 and board[i][j] == 1):
                count += 1
                move = deque([[i, j]])
                check[i][j] = 1
                while(move):
                    ny, nx = move.popleft()
                    for k in range(8):
                        y = ny + dy[k]
                        x = nx + dx[k]
                        if 0 <= y < H and 0 <= x < W and check[y][x] == 0:
                            if board[y][x] == 1:
                                check[y][x] = 1
                                move.append([y, x])
    res.append(count)