import sys
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split())

arr = []
res = [[0 for _ in range(M)] for _ in range(N)]
move = deque()
for i in range(N):
    arr.append(list(map(int, input().split())))
    for j in range(M):
        if(arr[i][j] == 1):
            res[i][j] = -1
        elif(arr[i][j] == 2):
            move.append([i, j])

dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]
while(move):
    y, x = move.popleft()
    for i in range(4):
        ny = y + dy[i]
        nx = x + dx[i]
        if 0 <= ny < N and 0 <= nx < M:
            if arr[ny][nx] == 1 and res[ny][nx] == -1:
                res[ny][nx] = res[y][x] + 1
                move.append([ny, nx])
for i in res:
    print(*i)