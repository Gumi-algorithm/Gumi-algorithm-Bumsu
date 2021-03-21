import sys
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split())

arr = [list(map(int, input().split())) for _ in range(N)]

dy, dx = [1, 0, 1], [0, 1, 1]

move = deque([[0, 0]])
v = [[-1 for _ in range(M)] for _ in range(N)]
v[0][0] = arr[0][0]
res = 0
while(move):
    y, x = move.popleft()
    for i in range(3):
        ny = y + dy[i]
        nx = x + dx[i]
        if(0 <= ny < N and 0 <= nx < M and v[ny][nx] <= v[y][x] + arr[ny][nx]) :
            if(v[ny][nx] == -1):
                move.append([ny, nx])
            v[ny][nx] = v[y][x] + arr[ny][nx]

print(v[N - 1][M - 1])

"""
3 5
3 4 5
2 3 9
3 9 3
4 5 1
1 3 6
33
"""