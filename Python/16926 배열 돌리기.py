import sys
input = sys.stdin.readline

arr = []
dy = [1, 0, -1, 0]
dx = [0, 1, 0, -1]
N, M, R = map(int, input().split())

for i in range(N):
    arr.append(list(map(int, input().split())))

def turnArr():
    s = min(N, M) // 2
    for i in range(s):
        vertical, horizen = N - 1 - 2 * i, M - 1 - 2 * i
        pattern = [vertical, horizen, vertical, horizen]
        y, x = i, i
        change = arr[y][x]
        for j in range(4):
            for k in range(pattern[j]):
                ny, nx = y + dy[j], x + dx[j]
                temp = arr[ny][nx]
                arr[ny][nx] = change
                change = temp
                y, x = ny, nx

for i in range(R):
    turnArr()
for i in range(N):
    print(*arr[i])