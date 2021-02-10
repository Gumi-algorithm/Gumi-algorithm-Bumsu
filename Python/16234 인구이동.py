import sys
from collections import deque
dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]
N, L, R = map(int, sys.stdin.readline().split())
board = []
for i in range(N):
    board.append(list(map(int, sys.stdin.readline().split())))

check = [[0 for _ in range(N)] for _ in range(N)]

def bfs(cy, cx, cnt):
    count = 1
    people = board[cy][cx]
    move = deque([[cy, cx]])
    check[cy][cx] = cnt
    while(move):
        y, x = move.popleft()
        for i in range(4):
            ny = y + dy[i]
            nx = x + dx[i]
            if(0 <= ny < N and 0 <= nx < N and check[ny][nx] == 0):
                if(L <= abs(board[ny][nx] - board[y][x]) <= R):
                    check[ny][nx] = cnt
                    count += 1
                    people += board[ny][nx]
                    move.append([ny, nx])

    if(count == 1):
        check[cy][cx] = 0
        return False
    else:
        population.append(people / count)
    return True

def movdPeople(cnt):
    people = int(population[cnt - 1])
    for i in range(N):
        for j in range(N):
            if(check[i][j] == cnt):
                board[i][j] = people

result = 0
while(True):
    cnt = 1
    population = []
    check = [[0 for _ in range(N)] for _ in range(N)]
    for i in range(N):
        for j in range(N):
            if check[i][j] == 0:
                possible = bfs(i, j, cnt)
                if possible:
                    cnt += 1
    if(cnt == 1):
        break
    for i in range(1, cnt):
        movdPeople(i)
    result += 1
print(result)