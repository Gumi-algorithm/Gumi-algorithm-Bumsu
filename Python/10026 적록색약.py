import sys
from collections import deque
input = sys.stdin.readline

def bfs(y, x):
    move = deque([[y, x]])
    color = arr[y][x]
    if color == 'R' or color == 'G':
        next = 1
    elif color == 'B':
        next = 2
    else:
        next = 3
    arr[y][x] = next
    while(move):
        y, x = move.popleft()
        for i in range(4):
            ny = y + dy[i]
            nx = x + dx[i]
            if(0 <= ny < n and 0 <= nx < n):
                if(arr[ny][nx] == color):
                    arr[ny][nx] = next
                    move.append([ny, nx])

dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]
n = int(input())
arr = [list(input().rstrip()) for _ in range(n)]
cnt = [0, 0]
for i in range(n):
    for j in range(n):
        if(arr[i][j] != 1 and arr[i][j] != 2):
            bfs(i, j)
            cnt[0] += 1
for i in range(n):
    for j in range(n):
        if(arr[i][j] == 1 or arr[i][j] == 2):
            bfs(i, j)
            cnt[1] += 1
print(*cnt)