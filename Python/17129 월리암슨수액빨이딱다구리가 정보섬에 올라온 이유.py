import sys
from collections import deque
input = sys.stdin.readline

n, m = map(int, input().split())
arr = []
for i in range(n):
    arr.append(list(map(int, input().rstrip())))
    for j in range(m):
        if(arr[i][j] == 2):
            start = [i, j]

# 시작 : 2, 청국장 : 3, 스시 : 4, 맥앤치즈 : 5
dy, dx = [-1, 1, 0, 0], [0, 0, -1, 1]
move = deque([start])
res = [-1, -1]
v = [[0 for _ in range(m)] for _ in range(n)]
v[start[0]][start[1]] = 1
while(move):
    y, x = move.popleft()
    if(arr[y][x] > 2):
        res = [y, x]
        break
    for i in range(4):
        ny = y + dy[i]
        nx = x + dx[i]
        if(0 <= ny < n and 0 <= nx < m and arr[ny][nx] != 1 and v[ny][nx] == 0):
            v[ny][nx] = v[y][x] + 1
            move.append([ny, nx])

if(res[0] == -1 and res[1] == -1):
    print("NIE")
else:
    print("TAK")
    print(v[res[0]][res[1]] - 1)