import sys
from collections import deque
input = sys.stdin.readline

dy, dx = [-1, 0, 1, 0], [0, 1, 0, -1]  # 북 동 남 서
N, M, T = map(int, input().split())
arr = []
airCleancer = []
for i in range(N):
    arr.append(list(map(int, input().split())))
    for j in range(M):
        if(arr[i][j] == -1):
            airCleancer.append([i, j])


def spread():
    dusts = deque()
    for i in range(N):
        for j in range(M):
            if (arr[i][j] > 0):
                dusts.append([i, j, arr[i][j]])
    while(dusts):
        now = dusts.popleft()

        dust = now[2] // 5
        cnt = 0
        for i in range(4):
            ny = now[0] + dy[i]
            nx = now[1] + dx[i]

            if(0 <= ny < N and 0 <= nx < M and arr[ny][nx] >= 0):
                arr[ny][nx] += dust
                cnt += 1
        arr[now[0]][now[1]] -= cnt * dust


def Clock():
    y, x = airCleancer[0]
    dire = [0, 1, 2, 3]
    end = y
    for j in dire:
        while(True):
            ny = y + dy[j]
            nx = x + dx[j]
            if(0 <= ny <= end and 0 <= nx < M):
                if(arr[y][x] >= 0 and arr[ny][nx] >= 0):
                    arr[y][x] = arr[ny][nx]
                    arr[ny][nx] = 0
                y, x = ny, nx
            else:
                break


def AntiClock():
    y, x = airCleancer[1]
    dire = [2, 1, 0, 3]
    start = y
    for j in dire:
        while(True):
            ny = y + dy[j]
            nx = x + dx[j]
            if(start <= ny < N and 0 <= nx < M):
                if(arr[y][x] >= 0 and arr[ny][nx] >= 0):
                    arr[y][x] = arr[ny][nx]
                    arr[ny][nx] = 0
                y, x = ny, nx
            else:
                break


for i in range(T):
    spread()
    Clock()
    AntiClock()
res = 2
for i in range(N):
    for j in range(M):
        res += arr[i][j]
print(res)
